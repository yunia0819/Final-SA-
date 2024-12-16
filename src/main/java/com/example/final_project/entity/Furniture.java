package com.example.final_project.entity;


import jakarta.persistence.*;

@Table(name = "tb_furniture ")
@Entity
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Furniture_id")
    private  Integer furnitureid;
    @Column(name = "Furniture_name")
    private  String furnitureName;
    @Column(name = "imgurl")
    private  String imgurl;
    @Column(name = "price")
    private  Integer price;
    @Column(name = "quantity")
    private  Integer quantity;

    public Furniture(Integer furnitureid, String furnitureName, String imgurl, Integer price, Integer quantity) {
        this.furnitureid = furnitureid;
        this.furnitureName = furnitureName;
        this.imgurl = imgurl;
        this.price = price;
        this.quantity = quantity;
    }
    public Furniture() {}

    public Integer getFurnitureid() {
        return furnitureid;
    }

    public void setFurnitureid(Integer furnitureid) {
        this.furnitureid = furnitureid;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "Furnitureid=" + furnitureid +
                ", FurnitureName='" + furnitureName + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
