package com.example.final_project.entity.dto;

import jakarta.persistence.Column;

public class FurnitureDto {
    private  String furnitureName;
    private  String imgurl;
    private  Integer price;
    private  Integer quantity;

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "FurnitureDto{" +
                "FurnitureName='" + furnitureName + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
