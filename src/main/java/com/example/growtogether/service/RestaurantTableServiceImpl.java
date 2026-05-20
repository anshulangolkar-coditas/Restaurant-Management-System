package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.dto.restaurantTable.request.AddRestaurantTableRequestDto;
import com.example.growtogether.dto.restaurantTable.request.AssignStaffToTableRequestDto;
import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.dto.restaurantTable.response.AssignStaffToTableResponseDto;
import com.example.growtogether.dtomapping.table.RestaurantTableMapping;
import com.example.growtogether.entity.Restaurant;
import com.example.growtogether.entity.RestaurantBranch;
import com.example.growtogether.entity.RestaurantTable;
import com.example.growtogether.entity.Users;
import com.example.growtogether.exception.RestaurantBranchNotFoundException;
import com.example.growtogether.repository.RestaurantBranchRepository;
import com.example.growtogether.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantBranchRepository restaurantBranchRepository;
    private final RestaurantTableMapping restaurantTableMapping;


    @Override
    public AddTableResponseDto addTable(AddRestaurantTableRequestDto request, Users user) {

        RestaurantBranch branch = restaurantBranchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RestaurantBranchNotFoundException(ExceptionMessages.RESTAURANT_BRANCH_NOT_FOUND));

        RestaurantTable table = new RestaurantTable();
        table.setTableName(request.getTableName());
        table.setBranch(branch);

        RestaurantTable savedTable =  restaurantTableRepository.save(table);
        Restaurant restaurant = branch.getRestaurant();

        return restaurantTableMapping.entityToDto(restaurant, branch, savedTable);
    }

    @Override
    public AssignStaffToTableResponseDto assignStaffToTable(AssignStaffToTableRequestDto request, Users user) {




        return null;
    }
}
