package kr.ac.koreatech.jejureceiptproject.viewmodel;

import kr.ac.koreatech.jejureceiptproject.view.fragment.MoreFragment;

public class MoreFragmentViewModel {
    private static MoreFragmentViewModel instance;

    public static MoreFragmentViewModel getInstance() {
        if (instance == null)
            instance = new MoreFragmentViewModel();
        return instance;
    }

    public MoreFragmentViewModel() {

    }
}
