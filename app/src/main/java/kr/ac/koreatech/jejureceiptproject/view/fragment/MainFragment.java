package kr.ac.koreatech.jejureceiptproject.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.BasketRecyclerViewAdatper;
import kr.ac.koreatech.jejureceiptproject.adapter.recyclerview.RecyclerItemClickListener;
import kr.ac.koreatech.jejureceiptproject.databinding.FragmentMainBinding;
import kr.ac.koreatech.jejureceiptproject.domain.BasketDTO;
import kr.ac.koreatech.jejureceiptproject.viewmodel.BasketRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CactusRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.CountRecyclerViewModel;
import kr.ac.koreatech.jejureceiptproject.viewmodel.MainFragmentViewModel;


public class MainFragment extends Fragment {
    //    private CountRecyclerViewModel countRecyclerViewModel;
    private FragmentMainBinding binding;
    private DrawerLayout drawerLayout;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setViewModel(MainFragmentViewModel.getInstance());
        setHasOptionsMenu(true);
        initCactusList();
        initBasket();
//        initCountList();
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fragment_menu, menu);
        drawerLayout = getActivity().findViewById(R.id.mainFragmentDrawerLayout);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.drawerLayout:
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    int cnt = BasketRecyclerViewModel.getInstance().getAdatper().getItemCount();
                    if (cnt > 1) {
                        binding.basketRecyclerView.smoothScrollToPosition(cnt - 1);
                        BasketRecyclerViewModel.getInstance().getAdatper().notifyDataSetChanged();
                    }
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initCactusList() {
        binding.cactusRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.cactusRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MainFragmentViewModel.getInstance().setSelectCactus(
                                CactusRecyclerViewModel.getInstance().getItem(position)
                        );
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        return;
                    }
                }));
        binding.setCactusRecyclerViewModel(CactusRecyclerViewModel.getInstance());
    }

    private void initBasket() {
        binding.basketRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.basketRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        return;
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), "[" + BasketRecyclerViewModel.getInstance().getItem(position).toString() + "]가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        BasketRecyclerViewModel.getInstance().remove(position);
                        Integer count = 0, total = 0;
                        for (BasketDTO cactus : BasketRecyclerViewModel.getInstance().getItems()) {
                            count += cactus.getCount();
                            total += cactus.getTotal();
                        }
                        MainFragmentViewModel.getInstance().setSumTotal(total.toString());
                        MainFragmentViewModel.getInstance().setSumCount(count.toString());
                    }
                }));
        binding.setBasketRecyclerViewModel(BasketRecyclerViewModel.getInstance());
    }

}