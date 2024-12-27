package com.example.final_project.entity.dto;

public class productDto {
    private Long productId;
    private String name;
    private String description;
    private Double pricePerDay;
    private String imageLink;
    private Double ranks;

    public productDto(Long productId,String name, String description, Double pricePerDay, String imageLink, Double ranks) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.imageLink = imageLink;
        this.ranks = ranks;
    }
    public productDto() {
    }


    // Getters and Setters
    public void setproductId(Long productId) {
        this.productId = productId;
    }

    public Long getproductId() {
        return productId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Double getRanks() {
        return ranks;
    }

    public void setRanks(Double ranks) {
        this.ranks = ranks;
    }
}