package com.example.final_project.entity.dto;

import jakarta.persistence.Column;

public class FurnitureDto {
    private String furnitureName;
    private String imgurl;
    private Double price;
    private Integer quantity;

    public FurnitureDto(String furnitureName, String imgurl, Double price, Integer quantity) {
        this.furnitureName = furnitureName;
        this.imgurl = imgurl;
        this.price = price;
        this.quantity = quantity;
    }


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
        return "FurnitureDto{" +
                "FurnitureName='" + furnitureName + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
