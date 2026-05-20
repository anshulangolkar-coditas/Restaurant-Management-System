package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.constants.Role;
import com.example.growtogether.dto.restaurantTable.request.AddRestaurantTableRequestDto;
import com.example.growtogether.dto.restaurantTable.request.AssignStaffToTableRequestDto;
import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.dto.restaurantTable.response.AssignStaffToTableResponseDto;
import com.example.growtogether.dtomapping.table.RestaurantTableMapping;
import com.example.growtogether.entity.*;
import com.example.growtogether.exception.BranchTableNotFoundException;
import com.example.growtogether.exception.RestaurantBranchNotFoundException;
import com.example.growtogether.exception.UserNotFoundException;
import com.example.growtogether.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantBranchRepository restaurantBranchRepository;
    private final RestaurantTableMapping restaurantTableMapping;
    private final StaffRepository staffRepository;
    private final OwnerRepository ownerRepository;
    private final ManagerRepository managerRepository;
    private final WaiterTableRepository waiterTableRepository;


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

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        if(!staff.getRole().equals(Role.STAFF_WAITER)){
            throw new RuntimeException("Can Assign table to only the waiter staff");
        }

        Users staffUser = staff.getUser();

        RestaurantTable table = restaurantTableRepository.findById(request.getTableId())
                .orElseThrow(() -> new BranchTableNotFoundException(ExceptionMessages.BRANCH_TABLE_NOT_FOUND));

        RestaurantBranch branch = table.getBranch();
        Restaurant restaurant = branch.getRestaurant();


        Owner owner = null;
        Manager manager = null;

        if(ownerRepository.findById(user.getUserId()).isPresent()){
            owner = ownerRepository.findById(user.getUserId()).get();
            List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();
            boolean belongsToOwner = ownerRestaurants.stream()
                    .anyMatch(ownerRestaurant -> ownerRestaurant.getRestaurant().getRestaurantId().equals(staff.getBranch().getRestaurant().getRestaurantId()));
            if(!belongsToOwner){
                throw new RuntimeException("Restaurant Does Not Belong to Owner");
            }
        }else if(managerRepository.findById(user.getUserId()).isPresent()){
            manager = managerRepository.findById(user.getUserId()).get();
            if(!manager.getBranch().getBranchId().equals(staff.getBranch().getBranchId())){
                throw new RuntimeException("Restaurant Does Not Belong to Owner");
            }
        }

        WaiterTable waiterTable = waiterTableRepository.save(restaurantTableMapping.dtoToEntity(staff,table));

        return restaurantTableMapping.assignStaffResponse(staff, staffUser, restaurant, branch, table);
    }
}
