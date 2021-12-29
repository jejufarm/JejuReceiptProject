package kr.ac.koreatech.jejureceiptproject.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.ac.koreatech.jejureceiptproject.databinding.ControllCountFormBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CountRecyclerViewModel;

public class CountRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CountRecyclerViewModel viewModel;

    public CountRecyclerAdapter(CountRecyclerViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ControllCountFormBinding binding = ControllCountFormBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CountRecyclerAdapter.ItemViewHolder) {
            ((CountRecyclerAdapter.ItemViewHolder) holder).bind(viewModel, position);
        }
    }

    @Override
    public int getItemCount() {
        return viewModel.getItems() == null ? 0 : viewModel.getItems().size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ControllCountFormBinding binding;

        public ItemViewHolder(ControllCountFormBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CountRecyclerViewModel viewModel, int pos) {
            binding.setViewModel(viewModel);
            binding.setPos(pos);
            binding.executePendingBindings();
        }
    }
}
