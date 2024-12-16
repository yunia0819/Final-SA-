package com.example.final_project.controller;

import com.example.final_project.entity.Furniture;
import com.example.final_project.entity.dto.FurnitureDto;
import com.example.final_project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.final_project.entity.ReponseMessage;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.StreamSupport;


@RestController //接口方法返回對象 轉換成JSON
@RequestMapping("/furniture") // localhost:8080/furniture/**

public class FurnitureController {

    @Autowired
    IUserService userService;


    //REST
    //增加
    @PostMapping // URL:localhost:8080/furniture  method:post
    public ReponseMessage add(@RequestBody FurnitureDto furniture){
          Furniture furnitureNew = userService.add (furniture);
         return ReponseMessage.success(furnitureNew);
    }
    @PostMapping("/addToCart") // URL: localhost:8080/furniture/addToCart  method:post
    public ReponseMessage<Furniture> addToCart() {
        // 創建 FurnitureDto 並設置固定數據
        FurnitureDto furnitureDto = new FurnitureDto();
        furnitureDto.setFurnitureName("冷氣機");
        furnitureDto.setImgurl("https://www.kasite.com/upload/save_image/04251134_626608be49f27.jpg     ");
        furnitureDto.setPrice(500);
        furnitureDto.setQuantity(1);

        // 呼叫服務層保存數據
        Furniture savedFurniture = userService.add(furnitureDto);

        // 返回成功響應
        return ReponseMessage.success(savedFurniture);

    }
    @PostMapping("/addTestFurniture") // URL: localhost:8080/furniture/addTestFurniture
    public ReponseMessage<Furniture> addTestFurniture() {
        // 創建 FurnitureDto 並設置固定測試數據
        FurnitureDto furnitureDto = new FurnitureDto();
        furnitureDto.setFurnitureName("冰箱");
        furnitureDto.setImgurl("https://www.kasite.com/upload/save_image/04251119_626605178ec0d.jpg");
        furnitureDto.setPrice(4000);
        furnitureDto.setQuantity(1);

        // 調用服務層保存數據
        Furniture savedFurniture = userService.add(furnitureDto);

        // 返回響應
        return ReponseMessage.success(savedFurniture);
    }


    @GetMapping("/cart")
    public ReponseMessage<Map<String, Object>> getCartItems() {
        // 获取合并后的购物车商品
        Iterable<Furniture> mergedCartItems = userService.listCartItems();


        // 计算总数和总金额
        int totalItems = 0;
        int totalAmount = 0;
        for (Furniture item : mergedCartItems) {
            totalItems += item.getQuantity();
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // 封装响应数据
        Map<String, Object> response = new HashMap<>();
        response.put("cartItems", mergedCartItems);
        response.put("totalItems", totalItems);
        response.put("totalAmount", totalAmount);

        // 返回响应
        return ReponseMessage.success(response);
    }



    //查詢
    //@GetMapping
    //修改
    //@PutMapping
    //刪除
    //@DeleteMapping
}
