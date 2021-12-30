package kr.ac.koreatech.jejureceiptproject.domain;

public class ReceiptDTO {
    private Integer number;
    private String name;
    private Integer count;
    private Integer price;
    private Integer total;
    private String extra;

    public ReceiptDTO(Integer number, String name, Integer count, Integer price, Integer total, String extra) {
        this.number = number;
        this.name = name;
        this.count = count;
        this.price = price;
        this.total = total;
        this.extra = extra;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
