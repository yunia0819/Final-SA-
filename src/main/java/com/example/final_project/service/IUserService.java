package com.example.final_project.service;


import com.example.final_project.entity.Furniture;
import com.example.final_project.entity.dto.FurnitureDto;

public interface IUserService {
    /**
     * 插入用戶
     *
     * @return
     */
    Furniture add(FurnitureDto furniture);
    Iterable<Furniture> listAll();
    Iterable<Furniture> listCartItems();


}
