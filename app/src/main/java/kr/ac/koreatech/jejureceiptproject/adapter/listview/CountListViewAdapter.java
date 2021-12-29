//package kr.ac.koreatech.jejureceiptproject.adapter.listview;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import androidx.databinding.DataBindingUtil;
//
//import java.util.List;
//
//import kr.ac.koreatech.jejureceiptproject.R;
//import kr.ac.koreatech.jejureceiptproject.databinding.ControllCountFormBinding;
//
//public class CountListViewAdapter extends BaseAdapter {
//    private List<Integer> items;
//    private LayoutInflater inflater;
//
//    public CountListViewAdapter(List<Integer> items, LayoutInflater inflater) {
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
//        ControllCountFormBinding binding;
//        if (view == null) {
//            binding = DataBindingUtil.inflate(inflater, R.layout.controll_count_form, viewGroup, false);
//            view = binding.getRoot();
//        } else {
//            binding = (ControllCountFormBinding) view.getTag();
//        }
//
//        view.setTag(binding);
//        Integer count = items.get(i);
//
//        CountListViewAdapter.Items items = new CountListViewAdapter.Items(count.toString());
//        binding.setItems(items);
//
//        return view;
//    }
//
//    public class Items {
//        private String count;
//
//        public Items(String count) {
//            this.count = count;
//        }
//
//        public String getCount() {
//            return count;
//        }
//
//    }
//}
