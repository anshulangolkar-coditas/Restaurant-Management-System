package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.constants.Role;
import com.example.growtogether.dto.staff.response.GetAllStaffResponseDto;
import com.example.growtogether.dtomapping.staff.StaffMapping;
import com.example.growtogether.entity.*;
import com.example.growtogether.exception.RestaurantBranchNotFoundException;
import com.example.growtogether.exception.UserNotFoundException;
import com.example.growtogether.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService{

    private final StaffRepository staffRepository;
    private final OwnerRepository ownerRepository;
    private final ManagerRepository managerRepository;
    private final StaffMapping staffMapping;
    private final RestaurantBranchRepository restaurantBranchRepository;
    private final OwnerRestaurantRepository ownerRestaurantRepository;


    @Override
    public List<GetAllStaffResponseDto> getAllStaff(Long branchId, int page, Users user) {

        Pageable pageable = PageRequest.of(page, 8);

        if(!user.getRole().contains(Role.OWNER)){
            Manager manager = managerRepository.findByUser(user)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

            if (!manager.getBranch().getBranchId().equals(branchId)){
                throw new RuntimeException("Manager does not belong to this branch");
            }

            RestaurantBranch branch = manager.getBranch();
            Restaurant restaurant = branch.getRestaurant();

            Page<Staff> staffList = staffRepository.findAllByBranch(branch, pageable);

            return  staffMapping.listStaffDetailsToDto(staffList, restaurant, branch);

        }

/*        if(branchId == null){

            Owner owner = ownerRepository.findByUser(user)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

            List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();



            Page<Staff> staffList = staffRepository.findAllByBranch(branch, pageable);

            if(!ownerRestaurantRepository.exitsByOwnerAndRestaurant(owner, restaurant)){
                throw new RuntimeException("Branch Does not belong to owner");
            }

            return staffMapping.listStaffDetailsToDto(staffList, restaurant, branch);

        }*/

        Owner owner = ownerRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        RestaurantBranch branch = restaurantBranchRepository.findById(branchId)
                .orElseThrow(() -> new RestaurantBranchNotFoundException(ExceptionMessages.RESTAURANT_BRANCH_NOT_FOUND));

        Restaurant restaurant = branch.getRestaurant();

        Page<Staff> staffList = staffRepository.findAllByBranch(branch, pageable);

        if(!ownerRestaurantRepository.existsByOwnerAndRestaurant(owner, restaurant)){
            throw new RuntimeException("Branch Does not belong to owner");
        }

        return staffMapping.listStaffDetailsToDto(staffList, restaurant, branch);
    }
}
