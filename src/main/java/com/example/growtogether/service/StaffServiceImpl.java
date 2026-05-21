package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.constants.Role;
import com.example.growtogether.dto.staff.request.DeleteStaffRequestDto;
import com.example.growtogether.dto.staff.response.GetAllGenericResponse;
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
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final OwnerRepository ownerRepository;
    private final ManagerRepository managerRepository;
    private final StaffMapping staffMapping;
    private final RestaurantBranchRepository restaurantBranchRepository;
    private final OwnerRestaurantRepository ownerRestaurantRepository;


    @Override
    public List<? extends GetAllGenericResponse> getAllStaff(Long branchId, int page, Users user) {

        Pageable pageable = PageRequest.of(page, 8);

        if (!user.getRole().contains(Role.OWNER)) {
            Manager manager = managerRepository.findByUser(user).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

            if (!manager.getBranch().getBranchId().equals(branchId)) {
                throw new RuntimeException("Manager does not belong to this branch");
            }

            RestaurantBranch branch = manager.getBranch();
            Restaurant restaurant = branch.getRestaurant();

            Page<Staff> staffList = staffRepository.findAllByBranch(branch, pageable);

            return staffMapping.listStaffDetailsToDto(staffList, restaurant, branch);

        }

        if (branchId == null) {

            Owner owner = ownerRepository.findByUser(user).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

            List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();

            List<Restaurant> restaurants = ownerRestaurants.stream().map(OwnerRestaurant::getRestaurant).toList();

            return staffMapping.restaurantAndStaffResponseDto(restaurants);

        }

        Owner owner = ownerRepository.findByUser(user).orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        RestaurantBranch branch = restaurantBranchRepository.findById(branchId).orElseThrow(() -> new RestaurantBranchNotFoundException(ExceptionMessages.RESTAURANT_BRANCH_NOT_FOUND));

        Restaurant restaurant = branch.getRestaurant();

        Page<Staff> staffList = staffRepository.findAllByBranch(branch, pageable);

        if (!ownerRestaurantRepository.existsByOwnerAndRestaurant(owner, restaurant)) {
            throw new RuntimeException("Branch Does not belong to owner");
        }

        return staffMapping.listStaffDetailsToDto(staffList, restaurant, branch);
    }

    @Override
    public String deleteStaff(DeleteStaffRequestDto request, Users user) {

        RestaurantBranch branch = restaurantBranchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RestaurantBranchNotFoundException(ExceptionMessages.RESTAURANT_BRANCH_NOT_FOUND));

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        if(!branch.getBranchId().equals(staff.getBranch().getBranchId())){
            throw new RuntimeException("Staff does not belong to this branch");
        }

        if (!user.getRole().contains(Role.OWNER)) {

            Manager manager = managerRepository.findByUser(user)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

            if(!manager.getBranch().equals(staff.getBranch())){
                throw new RuntimeException("Manager and Staff do not belong to same branch");
            }

            staffRepository.delete(staff);

            return "Deleted Successfully";

        }

        Owner owner = ownerRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();

        List<Restaurant> restaurants = ownerRestaurants
                .stream().map(OwnerRestaurant::getRestaurant).toList();

        boolean containsBranch = restaurants.stream()
                .anyMatch(restaurant -> restaurant.getBranches().stream()
                        .anyMatch(ownerBranch -> Objects.equals(ownerBranch.getBranchId(), request.getBranchId())));

        if(!containsBranch){
            throw new RuntimeException("Branch does not belong to owner");
        }

        staffRepository.delete(staff);

        return "Staff Deleted Successfully";
    }
}
