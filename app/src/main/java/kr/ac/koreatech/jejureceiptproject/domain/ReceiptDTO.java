package kr.ac.koreatech.jejureceiptproject.domain;

public class ReceiptDTO {
    private Integer order;
    private String name;
    private Integer count;
    private Integer price;
    private Integer total;
    private String extra;

    public ReceiptDTO(Integer order, String name, Integer count, Integer price, Integer total, String extra) {
        this.order = order;
        this.name = name;
        this.count = count;
        this.price = price;
        this.total = total;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
