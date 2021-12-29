package kr.ac.koreatech.jejureceiptproject.adapter.recyclerview;

public interface ItemTouchHelperListener {
    boolean onItemMove(int form_position, int to_position);

    void onItemSwipe(int position);
}
