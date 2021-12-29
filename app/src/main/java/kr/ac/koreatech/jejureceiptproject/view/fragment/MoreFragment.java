package kr.ac.koreatech.jejureceiptproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.databinding.FragmentMoreBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainFragmentViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MoreFragmentViewModel;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding;

    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false);
        binding.setViewModel(MoreFragmentViewModel.getInstance());
        return binding.getRoot();
    }
}