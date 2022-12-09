package kr.ac.koreatech.jejureceiptproject.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.ObservableField;

import java.text.DecimalFormat;

import kr.ac.koreatech.jejureceiptproject.R;
import kr.ac.koreatech.jejureceiptproject.domain.BasketDTO;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;
import kr.ac.koreatech.jejureceiptproject.view.activity.PrintFormActivity;


public class MainFragmentViewModel {
    private static MainFragmentViewModel instance;
    private CactusDTO selectCactusDTO;


    public static MainFragmentViewModel getInstance() {
        if (instance == null) {
            instance = new MainFragmentViewModel();
        }
        return instance;
    }

    private ObservableField<String> selectCactus = new ObservableField<>();
    private ObservableField<String> selectCount = new ObservableField<>();
    private ObservableField<String> sumCount = new ObservableField<>();
    private ObservableField<String> sumTotal = new ObservableField<>();

    public MainFragmentViewModel() {
        selectCactus.set("");
        selectCount.set("");
        setSumCount("0");
        setSumTotal("0");
    }

    public ObservableField<String> getSelectCactus() {
        return selectCactus;
    }

    public void setSelectCactus(CactusDTO selectCactus) {
        if (selectCactus == null) {
            this.selectCactus.set("");
        } else {

            selectCactusDTO = selectCactus;
            this.selectCactus.set(String.format("%s %3s %-1s",
                    selectCactus.getName(),
                    "",
                    new DecimalFormat("###,###").format(selectCactus.getPrice())
            ));
        }
    }

    public ObservableField<String> getSelectCount() {
        return selectCount;
    }

    public void removeSelectCount() {
        if (getSelectCount().get() != null && getSelectCount().get().length() > 0) {
            this.selectCount.set(getSelectCount().get().substring(0, getSelectCount().get().length() - 1));
            Integer count = 0, total = 0;
            for (BasketDTO cactus : BasketRecyclerViewModel.getInstance().getItems()) {
                count += cactus.getCount();
                total += cactus.getPrice();
            }
            setSumTotal(total.toString());
            setSumCount(count.toString());
        }
    }

    public void setSelectCount(String selectCount) {
        if (getSelectCount().get() != null && getSelectCount().get().length() < 3) {
            if (getSelectCount().get().length() == 0 && selectCount.equals("0"))
                return; // 맨 처음은 0이 올 수 없음.
            this.selectCount.set(getSelectCount().get() + selectCount);
        } else {
            if (!selectCount.equals("0"))
                this.selectCount.set(selectCount);

        }
    }

    public ObservableField<String> getSumCount() {
        return sumCount;
    }

    public void setSumCount(String sumCount) {
        this.sumCount.set(sumCount);
    }

    public ObservableField<String> getSumTotal() {
        try {
            sumTotal.set(new DecimalFormat("###,###").format(Integer.parseInt(sumTotal.get())));
        } catch (NumberFormatException ex) {

        }
        return sumTotal;
    }

    public void setSumTotal(String sumTotal) {
        this.sumTotal.set(sumTotal);
    }

    public boolean dialogBtn_onLongClick(View view) {
        if (view.getId() == R.id.dialog_button_delete) {
            selectCount.set("");
            return true;
        }

        return false;
    }

    public void dialogBtn_onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_button_0: {
                setSelectCount("0");
                break;
            }
            case R.id.dialog_button_1: {
                setSelectCount("1");
                break;
            }
            case R.id.dialog_button_2: {
                setSelectCount("2");
                break;
            }
            case R.id.dialog_button_3: {
                setSelectCount("3");
                break;
            }
            case R.id.dialog_button_4: {
                setSelectCount("4");
                break;
            }
            case R.id.dialog_button_5: {
                setSelectCount("5");
                break;
            }
            case R.id.dialog_button_6: {
                setSelectCount("6");
                break;
            }
            case R.id.dialog_button_7: {
                setSelectCount("7");
                break;
            }
            case R.id.dialog_button_8: {
                setSelectCount("8");
                break;
            }
            case R.id.dialog_button_9: {
                setSelectCount("9");
                break;
            }
            case R.id.dialog_button_delete: {
                removeSelectCount();
                break;
            }
            case R.id.dialog_button_enter: {
                if (selectCactusDTO != null && !getSelectCount().get().equals("")) {
                    if (BasketRecyclerViewModel.getInstance().getItems().size() < 24) {
                        BasketRecyclerViewModel.getInstance().append(new BasketDTO(selectCactusDTO.getUid(), selectCactusDTO.getName(), selectCactusDTO.getCount(), selectCactusDTO.getPrice(), Integer.parseInt(getSelectCount().get())));

                        selectCactusDTO = null;
                        this.setSelectCactus(null);
                        this.selectCount.set("");

                        Integer count = 0;
                        for (BasketDTO cactus : BasketRecyclerViewModel.getInstance().getItems()) {
                            count += cactus.getSelect();
                        }
                        setSumCount(count.toString());
                    }
                }
                break;
            }
        }
    }

    public void clearBtn_onClick(View view) {
        BasketRecyclerViewModel.getInstance().clear();
        setSumCount("0");
        setSumTotal("0");
    }

    public void printBtn_onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, PrintFormActivity.class);
        context.startActivity(intent);
    }
}