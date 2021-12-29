package kr.ac.koreatech.jejureceiptproject.viewmodel;

import android.view.View;

import androidx.databinding.ObservableField;

public class EditFragmentViewModel {
    private static EditFragmentViewModel instance;

    public static EditFragmentViewModel getInstance() {
        if (instance == null)
            instance = new EditFragmentViewModel();
        return instance;
    }

    private ObservableField<Boolean> isEdit = new ObservableField<>();

    public EditFragmentViewModel() {
        isEdit.set(false);
    }

    public ObservableField<Boolean> isEdit() {
        return isEdit;
    }

    public void addButton(View v) {

    }

    public void updateButton(View v) {

    }

    public void deleteButton(View v) {

    }

    public void cancelButton(View v) {
        if (isEdit().get()) {
            isEdit.set(false);

        }
    }
}
