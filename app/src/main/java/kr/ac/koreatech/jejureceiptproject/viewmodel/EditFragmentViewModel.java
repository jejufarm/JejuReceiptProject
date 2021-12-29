package kr.ac.koreatech.jejureceiptproject.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

import kr.ac.koreatech.jejureceiptproject.SQLite.SQLiteControl;

public class EditFragmentViewModel {
    private static EditFragmentViewModel instance;

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

    public void addButton(View v) {
        try {
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
        System.out.println("dd");
    }

    public void deleteButton(View v) {

    }

    public void cancelButton(View v) {
        if (isEdit().get()) {
            isEdit.set(false);

        }
    }
}
