package com.example.final_project.controller;


import com.example.final_project.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/add")
    public ResponseEntity<?> addRental(@RequestBody Map<String, Object> requestBody) {
        try {
            // 日志：打印接收到的请求数据
            System.out.println("接收到的數據：" + requestBody);

            // 解析 items 列表
            List<Map<String, Object>> items = (List<Map<String, Object>>) requestBody.get("items");
            if (items == null || items.isEmpty()) {
                throw new IllegalArgumentException("項目列表不得為空！");
            }



            // 遍历 items 列表并处理每个租赁项目
            for (Map<String, Object> item : items) {
                // 安全解析数据
                String furnitureName = (String) item.getOrDefault("furnitureName", "未知家具");
                Integer quantity = parseInteger(item.get("quantity"));
                Double totalPrice = parseDouble(item.get("totalPrice"));
                String date = (String) item.getOrDefault("date", "");



                if (furnitureName.isBlank()) {
                    throw new IllegalArgumentException("家具名稱不得為空！");
                }
                if (quantity == null || quantity <= 0) {
                    throw new IllegalArgumentException("數量必須為正整數！");
                }
                if (totalPrice == null || totalPrice <= 0) {
                    throw new IllegalArgumentException("總價必須為正整數！");
                }

                // 调用服务层逻辑
                rentalService.createRental(furnitureName, quantity, date, totalPrice);
            }

            return ResponseEntity.ok("租賃記錄已成功新增");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無法新增租賃記錄：" + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無法新增租賃記錄：" + e.getMessage());
        }
    }

    // 工具方法：安全解析 Object 为 Integer
    private Integer parseInteger(Object obj) {
        try {
            if (obj instanceof Number) {
                return ((Number) obj).intValue();
            }
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }
    // 工具方法：安全解析 Object 為 Double
    private Double parseDouble(Object obj) {
        try {
            if (obj instanceof Number) {
                return ((Number) obj).doubleValue();
            }
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }

}





