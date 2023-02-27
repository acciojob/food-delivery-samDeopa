package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public  class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = FoodEntity.builder()
                .foodId(food.getFoodId())
                .foodCategory(food.getFoodCategory())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .build();
        foodRepository.save(foodEntity);

        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = FoodDto.builder()
                .id(food.getId())
                .foodId(food.getFoodId())
                .foodCategory(food.getFoodCategory())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .build();
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity food = foodRepository.findByFoodId(foodId);
        food.setFoodId(foodDetails.getFoodId());
        food.setFoodCategory(foodDetails.getFoodCategory());
        food.setFoodName(foodDetails.getFoodName());
        food.setFoodPrice(foodDetails.getFoodPrice());

        foodRepository.save(food);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        foodRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(FoodEntity food : foodRepository.findAll()){
            FoodDto foodDto = FoodDto.builder()
                    .id(food.getId())
                    .foodId(food.getFoodId())
                    .foodCategory(food.getFoodCategory())
                    .foodName(food.getFoodName())
                    .foodPrice(food.getFoodPrice())
                    .build();
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}