package kr.ac.koreatech.jejureceiptproject.viewmodel;

import java.util.ArrayList;
import java.util.List;

import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.CountRecyclerAdapter;

public class CountRecyclerViewModel {
    private static CountRecyclerViewModel instance;

    public static CountRecyclerViewModel getInstance() {
        if (instance == null)
            instance = new CountRecyclerViewModel();
        return instance;
    }

    private List<Integer> items;
    private CountRecyclerAdapter adapter;

    public CountRecyclerViewModel() {
        if (items == null)
            items = new ArrayList<>();

        if (adapter == null)
            adapter = new CountRecyclerAdapter(this);

        test();
    }

    private void test() {
        for (int i = 0; i < 10; i++) {
            items.add(i);
        }
    }

    public void onCreate() {
        adapter.notifyDataSetChanged();
    }

    public CountRecyclerAdapter getAdatper() {
        return adapter;
    }

    public List<Integer> getItems() {
        return items;
    }

    public Integer getInteger(int pos) {
        return items.get(pos);
    }
}