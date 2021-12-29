package kr.ac.koreatech.jejureceiptproject.viewmodel;

import java.util.ArrayList;
import java.util.List;

import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.BasketRecyclerViewAdatper;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.CactusRecyclerViewAdapter;
import kr.ac.koreatech.jejureceiptproject.domain.BasketDTO;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;

public class BasketRecyclerViewModel {
    private static BasketRecyclerViewModel instance;

    public static BasketRecyclerViewModel getInstance() {
        if (instance == null) {
            instance = new BasketRecyclerViewModel();
        }
        return instance;
    }

    private List<BasketDTO> items;
    private BasketRecyclerViewAdatper adapter;


    public BasketRecyclerViewModel() {
        if (items == null)
            items = new ArrayList<>();

        if (adapter == null)
            adapter = new BasketRecyclerViewAdatper(this);

    }

    public void onCreate() {
        adapter.notifyDataSetChanged();
    }

    public BasketRecyclerViewAdatper getAdatper() {
        return adapter;
    }

    public List<BasketDTO> getItems() {
        return items;
    }

    public BasketDTO getItem(int pos) {
        return items.get(pos);
    }

    public String getName(int pos) {
        return items.get(pos).getName();
    }

    public Integer getPrice(int pos) {
        return items.get(pos).getPrice();
    }

    public Integer getCount(int pos) {
        return items.get(pos).getCount();
    }

    public Integer getTotal(int pos) {
        return items.get(pos).getTotal();
    }

    public void append(BasketDTO basketDTO) {
        items.add(basketDTO);
        adapter.notifyDataSetChanged();
    }

    public void remove(int pos) {
        items.remove(pos);
        adapter.notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        adapter.notifyDataSetChanged();
    }
}
