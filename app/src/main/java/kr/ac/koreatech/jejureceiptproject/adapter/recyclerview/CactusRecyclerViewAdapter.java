package kr.ac.koreatech.jejureceiptproject.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.databinding.ControllCactusFormBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;

public class CactusRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CactusRecyclerViewModel viewModel;

    public CactusRecyclerViewAdapter(CactusRecyclerViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ControllCactusFormBinding binding = ControllCactusFormBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CactusRecyclerViewAdapter.ItemViewHolder) {
            ((CactusRecyclerViewAdapter.ItemViewHolder) holder).bind(viewModel, position);
        }
    }

    @Override
    public int getItemCount() {
        return viewModel.getItems() == null ? 0 : viewModel.getItems().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ControllCactusFormBinding binding;

        public ItemViewHolder(ControllCactusFormBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(CactusRecyclerViewModel viewModel, int pos) {
            binding.setViewModel(viewModel);
            binding.setDecimal(new Transaction(viewModel.getPrice(pos)));
            binding.setPos(pos);
            binding.executePendingBindings();
        }

        public class Transaction {
            private int price;

            public Transaction(int price) {
                this.price = price;
            }

            public String getPrice() {
                return new DecimalFormat("###,###").format(price);
            }
        }
    }
}
