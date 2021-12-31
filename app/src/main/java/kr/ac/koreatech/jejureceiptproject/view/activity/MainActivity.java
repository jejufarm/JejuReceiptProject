package kr.ac.koreatech.jejureceiptproject.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.SQLite.SQLiteControl;
import kr.ac.koreatech.jejureceiptproject.SQLite.SQLiteHelper;
import kr.ac.koreatech.jejureceiptproject.databinding.ActivityMainBinding;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static InputMethodManager imm;

    public static InputMethodManager getImm() {
        return imm;
    }

    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로 가기 버튼을 누를 때 표시
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 없앰(전체화면)
        MainActivityViewModel.mainActivity = this;
        binding.setViewModel(MainActivityViewModel.getInstance());
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        SQLiteHelper.setInstance(MainActivity.this,
                "data.db",
                null,
                1);
        CactusRecyclerViewModel.getInstance().getCacutList();
        setTitle("제주농원 영수증 앱");
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
            android.os.Process.killProcess(android.os.Process.myPid());
            toast.cancel();
            toast = Toast.makeText(this, "이용해 주셔서 감사합니다.", Toast.LENGTH_LONG);
            toast.show();

        }
    }
}