package com.example.final_project.service;


import com.example.final_project.entity.Furniture;
import com.example.final_project.entity.dto.FurnitureDto;
import com.example.final_project.repository.FurnitureRepoistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap; // 如果你使用 HashMap
import java.util.ArrayList;


import com.example.final_project.entity.Furniture;



@Service
public class FurnitureService implements IUserService {

    @Autowired
    FurnitureRepoistory furnitureRepoistory;

    @Override
    public Furniture add(FurnitureDto furniture) {

        Furniture furniture1 = new Furniture();
        BeanUtils.copyProperties(furniture,furniture1);

        return furnitureRepoistory.save(furniture1);
        //調用數據訪問
    }
    @Override
    public Iterable<Furniture> listAll() {
        // 返回所有家具項目
        return furnitureRepoistory.findAll();
    }
    /**
     * 合并购物车中同名称的商品数量
     *
     * @return 合并后的商品列表
     */
    @Override
    public List<Furniture> listCartItems() {
        Iterable<Furniture> items = furnitureRepoistory.findAllByQuantityGreaterThan(0); // 查询所有购物车商品
        Map<String, Furniture> mergedItems = new HashMap<>();

        for (Furniture item : items) {
            String name = item.getFurnitureName();
            if (mergedItems.containsKey(name)) {
                // 如果商品名称已存在，合并数量
                Furniture existingItem = mergedItems.get(name);
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            } else {
                // 不存在则新增到 Map 中
                mergedItems.put(name, new Furniture(
                        item.getFurnitureid(),
                        item.getFurnitureName(),
                        item.getImgurl(),
                        item.getPrice(),
                        item.getQuantity()
                ));
            }
        }

        return new ArrayList<>(mergedItems.values());
    }


}
