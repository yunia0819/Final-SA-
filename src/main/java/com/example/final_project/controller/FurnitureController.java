package com.example.final_project.controller;

import com.example.final_project.entity.Furniture;
import com.example.final_project.entity.dto.productDto; // 修正導入路徑
import com.example.final_project.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.final_project.entity.ReponseMessage;
import java.util.Map;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.final_project.entity.dto.FurnitureDto;



// 如果使用原始類別名稱


@RestController
@RequestMapping("/api/cart")

public class FurnitureController {
    @Autowired
    private FurnitureService furnitureService;


    @GetMapping("/all")
    public ResponseEntity<List<FurnitureDto>> getAllFurnitures() {
        List<FurnitureDto> furnitures = furnitureService.getAllFurnitures();
        return ResponseEntity.ok(furnitures);
    }



    @PostMapping("/add")
    public ResponseEntity<ReponseMessage> addToCart(@RequestBody Map<String, Long> requestBody) {
        Long productId = requestBody.get("id");

        if (productId == null) {
            return ResponseEntity.badRequest().body(new ReponseMessage<>(400, "商品ID缺失", null));
        }

        try {
            furnitureService.addToCart(productId);
            return ResponseEntity.ok(new ReponseMessage<>(200, "加入購物車成功", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReponseMessage<>(500, "加入購物車失敗：" + e.getMessage(), null));
        }
    }
    @PostMapping("/remove")
    public ResponseEntity<ReponseMessage> removeFurnitureByName(@RequestBody Map<String, String> requestBody) {
        String furnitureName = requestBody.get("name");

        if (furnitureName == null || furnitureName.isEmpty()) {
            return ResponseEntity.badRequest().body(new ReponseMessage<>(400, "家具名稱缺失", null));
        }

        try {
            furnitureService.removeFurnitureByName(furnitureName);
            return ResponseEntity.ok(new ReponseMessage<>(200, "家具已成功刪除", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ReponseMessage<>(500, "刪除失敗：" + e.getMessage(), null));
        }
    }
}

