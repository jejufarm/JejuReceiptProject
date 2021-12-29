package kr.ac.koreatech.jejureceiptproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.databinding.FragmentEditBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.EditFragmentViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainFragmentViewModel;

public class EditFragment extends Fragment {
    private FragmentEditBinding binding;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit, container, false);

        binding.setViewModel(EditFragmentViewModel.getInstance());
        binding.setCactusRecyclerViewModel(CactusRecyclerViewModel.getInstance());
        return binding.getRoot();
    }
}