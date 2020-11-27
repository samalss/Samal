package sample;


import java.io.Serializable;
import java.util.Date;

public class Products implements Serializable {
    private Integer idproducts;
    private String name;
    private String quality;
    private Date date;
    private int price;
    private int count;

    public Products() {
    }

    public Products(Integer idproducts, String name, String quality, Date date, int price, int count) {
        this.idproducts = idproducts;
        this.name = name;
        this.quality = quality;
        this.date = date;
        this.price = price;
        this.count = count;
    }

    public Products(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Products(String name, String quality, Date date, int price, int count) {
        this.name = name;
        this.quality = quality;
        this.date = date;
        this.price = price;
        this.count = count;
    }


    public Integer getIdproducts() {
        return idproducts;
    }

    public void setIdproducts(Integer idproducts) {
        this.idproducts = idproducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(double weight) {
        this.count = count;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}