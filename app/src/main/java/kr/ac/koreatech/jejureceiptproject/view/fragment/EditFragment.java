package kr.ac.koreatech.jejureceiptproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.CactusRecyclerViewAdapter;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.ItemTouchHelperCallback;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.RecyclerItemClickListener;
import kr.ac.koreatech.jejureceiptproject.databinding.FragmentEditBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.EditFragmentViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainFragmentViewModel;

public class EditFragment extends Fragment {
    private FragmentEditBinding binding;
    private ItemTouchHelper mItemTouchHelper;

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

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback((CactusRecyclerViewModel.getInstance().getAdatper())));
        mItemTouchHelper.attachToRecyclerView(binding.cactusRecyclerView);
        binding.cactusRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.cactusRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        EditFragmentViewModel.getInstance().setSelectedPos(position);
                        EditFragmentViewModel.getInstance().setEdit(true);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        return;
                    }
                }));
        return binding.getRoot();
    }
}