package kr.ac.koreatech.jejureceiptproject.domain;

public class CactusDTO {
    private Integer uid;
    private String name;
    private Integer count;
    private Integer price;
    private Integer order;

    public CactusDTO() {

    }

    public CactusDTO(Integer uid, String name, Integer count, Integer price, Integer order) {
        this.uid = uid;
        this.name = name;
        this.count = count;
        this.price = price;
        this.order = order;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
