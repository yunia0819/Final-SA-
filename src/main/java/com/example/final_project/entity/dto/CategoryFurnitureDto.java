package com.example.final_project.entity.dto;

public class CategoryFurnitureDto {
    private String name;
    private Double pricePerDay;
    private String imageLink;
    private Double ranks;

    public CategoryFurnitureDto(String name, Double pricePerDay, String imageLink, Double ranks) {
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.imageLink = imageLink;
        this.ranks = ranks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
