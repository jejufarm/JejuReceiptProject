//package kr.ac.koreatech.jejureceiptproject.adapter.listview;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import androidx.databinding.DataBindingUtil;
//
//import java.text.DecimalFormat;
//import java.util.List;
//
//import kr.ac.koreatech.jejureceiptproject.R;
//import kr.ac.koreatech.jejureceiptproject.databinding.ControllCactusFormBinding;
//import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;
//
//public class CactusListViewAdapter extends BaseAdapter {
//    private List<CactusDTO> items;
//    private LayoutInflater inflater;
//
//    public CactusListViewAdapter(List<CactusDTO> items, LayoutInflater inflater) {
//        this.items = items;
//        this.inflater = inflater;
//    }
//
//    @Override
//    public int getCount() {
//        return items.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return items.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ControllCactusFormBinding binding;
//        if (view == null) {
//            binding = DataBindingUtil.inflate(inflater, R.layout.controll_cactus_form, viewGroup, false);
//            view = binding.getRoot();
//        } else {
//            binding = (ControllCactusFormBinding) view.getTag();
//        }
//
//        view.setTag(binding);
//        String name = items.get(i).getName();
//        Integer price = items.get(i).getPrice();
//
//        Items items = new Items(name, new DecimalFormat("###,###").format(price));
//        binding.setItem(items);
//
//        return view;
//    }
//
//    public class Items {
//        private String name;
//        private String price;
//
//        public Items(String name, String price) {
//            this.name = name;
//            this.price = price;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//    }
//}
