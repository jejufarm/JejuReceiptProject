package kr.ac.koreatech.jejureceiptproject.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

import kr.ac.koreatech.jejureceiptproject.databinding.ControllBasketFormBinding;
import kr.ac.koreatech.jejureceiptproject.databinding.ControllCactusFormBinding;
import kr.ac.koreatech.jejureceiptproject.viewmodel.BasketRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;

public class BasketRecyclerViewAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private BasketRecyclerViewModel viewModel;

    public BasketRecyclerViewAdatper(BasketRecyclerViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ControllBasketFormBinding binding = ControllBasketFormBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BasketRecyclerViewAdatper.ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BasketRecyclerViewAdatper.ItemViewHolder) {
            ((BasketRecyclerViewAdatper.ItemViewHolder) holder).bind(viewModel, position);
        }
    }

    @Override
    public int getItemCount() {
        return viewModel.getItems() == null ? 0 : viewModel.getItems().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ControllBasketFormBinding binding;

        public ItemViewHolder(ControllBasketFormBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(BasketRecyclerViewModel viewModel, int pos) {
            binding.setViewModel(viewModel);
            binding.setDecimal(new BasketRecyclerViewAdatper.ItemViewHolder.Transaction(viewModel.getPrice(pos)));
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