package kr.ac.koreatech.jejureceiptproject.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.databinding.ActivityMainBinding;
import kr.ac.koreatech.jejureceiptproject.view.fragment.MainFragment;
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

        setTitle("제주농원 영수증 앱");
    }
}