package kr.ac.koreatech.jejureceiptproject.viewmodel;

import android.view.View;
import android.widget.FrameLayout;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.view.activity.MainActivity;
import kr.ac.koreatech.jejureceiptproject.view.fragment.EditFragment;
import kr.ac.koreatech.jejureceiptproject.view.fragment.MainFragment;
import kr.ac.koreatech.jejureceiptproject.view.fragment.MoreFragment;

public class MainActivityViewModel {
    private static MainActivityViewModel instance;

    public static MainActivityViewModel getInstance() {
        if (instance == null)
            instance = new MainActivityViewModel();
        return instance;
    }

    public static MainActivity mainActivity;

    // MainActivity의 현재 프래그먼트
    private ObservableField<Fragment> currFragment = new ObservableField<>();
    // 활성화 여부
    private ObservableField<Boolean> isMainActive = new ObservableField<>();
    private ObservableField<Boolean> isEditActive = new ObservableField<>();
    private ObservableField<Boolean> isMoreActive = new ObservableField<>();

    //fragment
    public static Fragment[] myFragment = new Fragment[]{new MainFragment(), new EditFragment(), new MoreFragment()};

    public MainActivityViewModel() {
        isMainActive.set(true);
        FragmentManager fm = MainActivityViewModel.mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment1, myFragment[0]);
        fragmentTransaction.add(R.id.fragment1, myFragment[1]);
        fragmentTransaction.add(R.id.fragment1, myFragment[2]);
        fragmentTransaction.commit();
    }

    //region getter setter
    public ObservableField<Boolean> isMainActive() {
        return isMainActive;
    }

    public void setMainActive(boolean mainActive) {
        isMainActive.set(mainActive);
    }

    public ObservableField<Boolean> isEditActive() {
        return isEditActive;
    }

    public void setEditActive(boolean editActive) {
        isEditActive.set(editActive);
    }

    public ObservableField<Boolean> isMoreActive() {
        return isMoreActive;
    }

    public void setMoreActive(boolean moreActive) {
        isMoreActive.set(moreActive);
    }

    public void setCurrFragment(Fragment fragment) {
        currFragment.set(fragment);
    }

    public ObservableField<Fragment> getCurrFragment() {
        return currFragment;
    }
    //endregion

    //region event
    public void navigation_onClick(View v) {
        // 키패드를 강제로 내려줌.
        if (MainActivity.getImm() != null) {
            MainActivity.getImm().hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        switch (v.getId()) {
            case R.id.main_imageView_main: {
                currFragment.set(myFragment[0]);
                setMainActive(true);
                setEditActive(false);
                setMoreActive(false);
                break;
            }
            case R.id.main_imageView_edit: {
                currFragment.set(myFragment[1]);
                setMainActive(false);
                setEditActive(true);
                setMoreActive(false);
                break;
            }

            case R.id.main_imageView_more: {
                currFragment.set(myFragment[2]);
                setMainActive(false);
                setEditActive(false);
                setMoreActive(true);
                break;
            }
        }
    }


    //endregion

    //region BindingAdapter

    //endregion
}
