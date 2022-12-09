package kr.ac.koreatech.jejureceiptproject.domain;

public class ReceiptDTO {
    private Integer order;
    private String name;
    private Integer boxCount;
    private Integer count;
    private Integer price;
    private String extra;

    public ReceiptDTO(Integer order, String name, Integer boxCount, Integer count, Integer price, String extra) {
        this.order = order;
        this.name = name;
        this.boxCount = boxCount;
        this.count = count;
        this.price = price;
        this.extra = extra;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer number) {
        this.order = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoxCount() {
        return boxCount;
    }

    public void setBoxCount(Integer boxCount) {
        this.boxCount = boxCount;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
