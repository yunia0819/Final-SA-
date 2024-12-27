package com.example.final_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_furniture") // 对应数据库表名
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成主键
    @Column(name = "furniture_id")
    private Long id; // 主键

    @Column(name = "furniture_name", nullable = false)
    private String name; // 商品名称

    @Column(name = "imgurl", nullable = false)
    private String imageLink; // 图片 URL

    @Column(name = "price", nullable = false)
    private Double price; // 价格

    @Column(name = "quantity", nullable = false)
    private Integer quantity; // 数量

    @Column(name = "ranks", nullable = false)
    private Double ranks; // 评分

    // Getters and Setters 略


    // Constructors
    public Furniture() {}

    public Furniture(Long id, String name, String imageLink, Double price, Double ranks, Integer quantity) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.price = price;
        this.ranks = ranks;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double    getRanks() {
        return ranks;
    }

    public void setRanks(Double ranks) {
        this.ranks = ranks;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", price=" + price +
                ", ranks=" + ranks +
                ", quantity=" + quantity +
                '}';
    }
}
