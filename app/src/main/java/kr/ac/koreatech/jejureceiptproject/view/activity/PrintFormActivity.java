package kr.ac.koreatech.jejureceiptproject.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.print.PrintHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.databinding.ActivityPrintFormBinding;
import kr.ac.koreatech.jejureceiptproject.util.MediaScanner;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainActivityViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.PrintFormViewModel;

public class PrintFormActivity extends AppCompatActivity {
    private ActivityPrintFormBinding binding;
    private boolean one = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_print_form);
        binding.setViewModel(PrintFormViewModel.getInstance());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 상태바 없앰(전체화면)
        getSupportActionBar().hide(); // 액션바 숨기기
        one = false;
    }

    public String saveBitmapToJpg(Bitmap bitmap, String name) {
        /**
         * 캐시 디렉토리에 비트맵을 이미지파일로 저장하는 코드입니다.
         *
         * @version target API 28 ★ API29이상은 테스트 하지않았습니다.★
         * @param Bitmap bitmap - 저장하고자 하는 이미지의 비트맵
         * @param String fileName - 저장하고자 하는 이미지의 비트맵
         *
         * File storage = 저장이 될 저장소 위치
         *
         * return = 저장된 이미지의 경로
         *
         * 비트맵에 사용될 스토리지와 이름을 지정하고 이미지파일을 생성합니다.
         * FileOutputStream으로 이미지파일에 비트맵을 추가해줍니다.
         */

        File storage = getCacheDir(); //  path = /data/user/0/YOUR_PACKAGE_NAME/cache
        String fileName = name + ".jpg";
        File imgFile = new File(storage, fileName);
        try {
            imgFile.createNewFile();
            FileOutputStream out = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, out); //썸네일로 사용하므로 퀄리티를 낮게설정
            out.close();
        } catch (FileNotFoundException e) {
            Log.e("saveBitmapToJpg", "FileNotFoundException : " + e.getMessage());
        } catch (IOException e) {
            Log.e("saveBitmapToJpg", "IOException : " + e.getMessage());
        }
        Log.d("imgPath", getCacheDir() + "/" + fileName);
        return getCacheDir() + "/" + fileName;
    }


    public Bitmap createViewToBitmap(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((PrintFormActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        view.measure(view.getWidth(), view.getHeight());
        view.layout(0, 0, view.getWidth(), view.getHeight());
        view.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        saveBitmapToJpg(bitmap, "newimg");
        return bitmap;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!one) {
            one = true;
            PrintHelper photoPrinter = new PrintHelper(this);
            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

            FrameLayout frameLayout = binding.linear;
            frameLayout.setDrawingCacheEnabled(true);
            frameLayout.buildDrawingCache();
            Bitmap bm = frameLayout.getDrawingCache();
            saveBitmapToJpg(bm, "newimg");
            photoPrinter.printBitmap("droids.jpg - test print", bm);
        }

    }
}