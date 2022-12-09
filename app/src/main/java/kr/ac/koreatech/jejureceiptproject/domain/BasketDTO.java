package kr.ac.koreatech.jejureceiptproject.domain;

import androidx.annotation.NonNull;

public class BasketDTO extends CactusDTO {
    private Integer count;
    private Integer price;
    private Integer select;

    public BasketDTO() {

    }

    public BasketDTO(Integer uid, String name, Integer count, Integer price, Integer select) {
        super(uid, name, count, price, -1);
        this.count = count;
        this.price = price;
        this.select = select;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSelect() {
        return select;
    }

    public void setSelect(Integer select) {
        this.select = select;
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
