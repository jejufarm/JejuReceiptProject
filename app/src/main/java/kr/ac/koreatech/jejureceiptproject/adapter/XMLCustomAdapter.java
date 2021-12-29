package kr.ac.koreatech.jejureceiptproject.adapter;

import android.widget.FrameLayout;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.view.activity.MainActivity;
import kr.ac.koreatech.jejureceiptproject.view.fragment.MainFragment;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainActivityViewModel;

public class XMLCustomAdapter {
    @BindingAdapter("verAdapter")
    public static void verAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("horAdapter")
    public static void horAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"fragmentUrl"})
    public static void changeFragment(FrameLayout frameLayout, Fragment fragment) {
        if (fragment == null)
            fragment = MainActivityViewModel.myFragment[0];

        FragmentManager fm = MainActivityViewModel.mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        for (Fragment frag : MainActivityViewModel.myFragment) {
            if (frag == fragment)
                fragmentTransaction.show(frag);
            else
                fragmentTransaction.hide(frag);

        }
        fragmentTransaction.commit();
    }
}
