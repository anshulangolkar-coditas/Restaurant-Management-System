package com.example.growtogether.dtomapping.table;

import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import com.example.growtogether.entity.RestaurantTable;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableMapping {

    public AddTableResponseDto entityToDto(Restaurant restaurant, RestaurantBranch branch, RestaurantTable table){

        return AddTableResponseDto.builder()
                .tableId(table.getTableId())
                .tableName(table.getTableName())
                .restaurantName(restaurant.getRestaurantName())
                .branchName(branch.getBranchName())
                .build();
    }


}
