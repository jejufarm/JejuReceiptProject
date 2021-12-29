package kr.ac.koreatech.jejureceiptproject.viewmodel;

import java.util.ArrayList;
import java.util.List;

import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.CactusRecyclerViewAdapter;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;

public class CactusRecyclerViewModel {
    private static CactusRecyclerViewModel instance;

    public static CactusRecyclerViewModel getInstance() {
        if (instance == null) {
            instance = new CactusRecyclerViewModel();
        }
        return instance;
    }

    private List<CactusDTO> items;
    private CactusRecyclerViewAdapter adapter;


    public CactusRecyclerViewModel() {
        if (items == null)
            items = new ArrayList<>();

        if (adapter == null)
            adapter = new CactusRecyclerViewAdapter(this);

//        test();
    }


    public void onCreate() {
        adapter.notifyDataSetChanged();
    }

    public CactusRecyclerViewAdapter getAdatper() {
        return adapter;
    }

    public CactusDTO getItem(int pos){
        return items.get(pos);
    }

    public List<CactusDTO> getItems() {
        return items;
    }

    public String getName(int pos) {
        return items.get(pos).getName();
    }

    public Integer getPrice(int pos) {
        return items.get(pos).getPrice();
    }

}
