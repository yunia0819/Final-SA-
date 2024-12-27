package com.example.final_project.service;


import com.example.final_project.entity.Furniture;
import com.example.final_project.entity.product; // 正確導入 Product 類
import com.example.final_project.repository.FurnitureRepository;
import com.example.final_project.repository.ProductRepository; // 導入 ProductRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.final_project.entity.dto.FurnitureDto;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;



@Service
public class FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<FurnitureDto> getAllFurnitures() {
        List<Furniture> furnitures = furnitureRepository.findAll();
        return furnitures.stream()
                .map(furniture -> new FurnitureDto(
                        furniture.getName(),
                        furniture.getImageLink(),
                        furniture.getPrice(),
                        furniture.getQuantity()))
                .collect(Collectors.toList());
    }

    public void addToCart(Long productId) {
        // 從 productRepository 中查找商品
        product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品未找到"));

        // 檢查家具表是否已存在此商品
        Optional<Furniture> existingFurniture = furnitureRepository.findByName(product.getName());

        if (existingFurniture.isPresent()) {
            // 如果存在，則數量加 1
            Furniture furniture = existingFurniture.get();
            furniture.setQuantity(furniture.getQuantity() + 1);
            furnitureRepository.save(furniture);
        } else {
            // 如果不存在，則新增一個條目
            Furniture newFurniture = new Furniture();
            newFurniture.setName(product.getName());
            newFurniture.setImageLink(product.getImageLink());
            newFurniture.setPrice(product.getPricePerDay());
            newFurniture.setRanks(product.getRanks());
            newFurniture.setQuantity(1); // 設置初始數量
            furnitureRepository.save(newFurniture);
        }
    }
    @Transactional
    public void removeFurnitureByName(String furnitureName) {
        // 找到符合名稱的家具並刪除
        furnitureRepository.deleteByName(furnitureName);
    }
}

