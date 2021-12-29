package kr.ac.koreatech.jejureceiptproject.view.activity;

import android.os.Bundle;
import android.view.WindowManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 없앰(전체화면)
        MainActivityViewModel.mainActivity = this;
        binding.setViewModel(MainActivityViewModel.getInstance());
        SQLiteHelper.setInstance(MainActivity.this,
                "data.db",
                null,
                1);
        CactusRecyclerViewModel.getInstance().getCacutList();
        setTitle("제주농원 영수증 앱");
    }
}