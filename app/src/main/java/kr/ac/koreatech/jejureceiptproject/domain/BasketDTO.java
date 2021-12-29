package kr.ac.koreatech.jejureceiptproject.domain;

import androidx.annotation.NonNull;

public class BasketDTO extends CactusDTO {
    private Integer count;
    private Integer total;

    public BasketDTO() {

    }

    public BasketDTO(Integer uid, String name, Integer price, Integer count) {
        super(uid, name, price, -1);
        this.count = count;
        this.total = price * count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @NonNull
    @Override
    public String toString() {
        return getName() + " " + getPrice() + " " + getCount();
    }
}
