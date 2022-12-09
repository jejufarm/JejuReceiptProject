package kr.ac.koreatech.jejureceiptproject.domain;

import androidx.annotation.NonNull;

public class BasketDTO extends CactusDTO {
    private Integer count;
    private Integer price;

    public BasketDTO() {

    }

    public BasketDTO(Integer uid, String name, Integer price, Integer count) {
        super(uid, name, count, price, -1);
        this.count = count;
        this.price = price * count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return getName() + " " + getPrice() + " " + getCount();
    }
}
