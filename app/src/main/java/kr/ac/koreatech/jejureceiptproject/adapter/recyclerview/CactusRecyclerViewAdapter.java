package kr.ac.koreatech.jejureceiptproject.adapter.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.SQLite.SQLiteControl;
import kr.ac.koreatech.jejureceiptproject.databinding.ControllCactusFormBinding;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.EditFragmentViewModel;

public class CactusRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperListener {
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

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        if (Math.abs(from_position - to_position) != 1) {
            System.out.println("거절");
            return false;
        }

        List<CactusDTO> item = viewModel.getItems();
        CactusDTO from_cactus = item.get(from_position);
        CactusDTO to_cactus = item.get(to_position);
        try {
            from_cactus.setOrder(to_position);
            to_cactus.setOrder(from_position);
            if (SQLiteControl.getInstance().swipe(from_cactus, to_cactus)) {
                item.remove(from_position);
                item.add(to_position, from_cactus);

                notifyItemMoved(from_position, to_position);
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public void onItemSwipe(int position) {
        List<CactusDTO> item = viewModel.getItems();
        try {
            if (SQLiteControl.getInstance().delete(item.get(position))) {
                item.remove(position);
                notifyItemRemoved(position);
                EditFragmentViewModel.getInstance().cancelButton(null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
