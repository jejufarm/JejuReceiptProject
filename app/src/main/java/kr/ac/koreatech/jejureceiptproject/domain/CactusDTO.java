package kr.ac.koreatech.jejureceiptproject.domain;

public class CactusDTO {
    private Long uid;
    private String name;
    private Integer price;

    public CactusDTO() {

    }

    public CactusDTO(Long uid, String name, Integer price) {
        this.uid = uid;
        this.name = name;
        this.price = price;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
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
}
