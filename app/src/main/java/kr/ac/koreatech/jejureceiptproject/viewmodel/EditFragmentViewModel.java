package kr.ac.koreatech.jejureceiptproject.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import kr.ac.koreatech.jejureceiptproject.SQLite.SQLiteControl;
import kr.ac.koreatech.jejureceiptproject.domain.CactusDTO;

public class EditFragmentViewModel {
    private static EditFragmentViewModel instance;
    private CactusDTO selected_cactus = null;

    public static EditFragmentViewModel getInstance() {
        if (instance == null)
            instance = new EditFragmentViewModel();
        return instance;
    }

    private ObservableField<Boolean> isEdit = new ObservableField<>();
    private ObservableField<String> cactusName = new ObservableField<>();
    private ObservableField<String> cactusPrice = new ObservableField<>();

    public EditFragmentViewModel() {
        isEdit.set(false);
    }

    public ObservableField<Boolean> isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        this.isEdit.set(edit);
    }

    public ObservableField<String> getCactusName() {
        return cactusName;
    }

    public void setCactusName(String cactusName) {
        this.cactusName.set(cactusName);
    }

    public ObservableField<String> getCactusPrice() {
        return cactusPrice;
    }

    public void setCactusPrice(String cactusPrice) {
        this.cactusPrice.set(cactusPrice);
    }

    public void setSelectedPos(int pos) {
        this.selected_cactus = CactusRecyclerViewModel.getInstance().getItem(pos);
        setCactusName(selected_cactus.getName());
        setCactusPrice(selected_cactus.getPrice() + "");
    }


    public void addButton(View v) {
        try {
            if (getCactusName().get().equals("") || Integer.parseInt(getCactusPrice().get()) < 0)
                return;

            if (SQLiteControl.getInstance().insert(-1, getCactusName().get(), Integer.parseInt(getCactusPrice().get()))) {
                cactusName.set("");
                cactusPrice.set("");
                CactusRecyclerViewModel.getInstance().getCacutList();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateButton(View v) {
        try {
            if (getCactusName().get().equals("") || Integer.parseInt(getCactusPrice().get()) < 0)
                return;
            selected_cactus.setName(getCactusName().get());
            selected_cactus.setPrice(Integer.parseInt(getCactusPrice().get()));
            if (SQLiteControl.getInstance().update(selected_cactus)) {
                cancelButton(v);
                CactusRecyclerViewModel.getInstance().getCacutList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void cancelButton(View v) {
//        CactusRecyclerViewModel.getInstance().getCacutList();
        if (isEdit().get()) {
            isEdit.set(false);
            selected_cactus = null;
            setCactusPrice("");
            setCactusName("");
        }
    }
}
