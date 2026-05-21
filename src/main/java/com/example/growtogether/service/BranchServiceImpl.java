package com.example.growtogether.service;

import com.example.growtogether.constants.ExceptionMessages;
import com.example.growtogether.dto.branch.request.AddNewBranchRequestDto;
import com.example.growtogether.dto.branch.request.UpdateManagerSalaryRequestDto;
import com.example.growtogether.dto.branch.request.UpdateStaffSalaryRequestDto;
import com.example.growtogether.dto.branch.response.AddNewBranchResponseDto;
import com.example.growtogether.dto.branch.response.UpdateManagerSalaryResponseDto;
import com.example.growtogether.dto.branch.response.UpdateStaffSalaryResponseDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.dtomapping.branch.BranchMapping;
import com.example.growtogether.dtomapping.restaurant.RestaurantMapping;
import com.example.growtogether.entity.*;
import com.example.growtogether.exception.RestaurantBranchNotFoundException;
import com.example.growtogether.exception.RestaurantNotFoundException;
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
public class BranchServiceImpl  implements BranchService{

    private final RestaurantBranchRepository restaurantBranchRepository;
    private final RestaurantRepository restaurantRepository;
    private final BranchMapping branchMapping;
    private final RestaurantMapping restaurantMapping;
    private final OwnerRepository ownerRepository;
    private final StaffRepository staffRepository;
    private final ManagerRepository managerRepository;

    @Override
    public AddNewBranchResponseDto addBranch(AddNewBranchRequestDto request, Users user) {

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        AddRestaurantResponseDto restaurantDto = restaurantMapping.entityToDto(restaurant);

        RestaurantBranch branch = restaurantBranchRepository.save(branchMapping.dtoToEntity(request, restaurant));

        return branchMapping.entityToDto(branch,restaurantDto);
    }

    @Override
    public List<AddNewBranchResponseDto> getAllBranches(Long restaurantId, int page, Users user) {

        Owner owner = ownerRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        boolean belongsToOwner = ownerRestaurants.stream()
                .anyMatch(ownerRestaurant -> ownerRestaurant.getRestaurant().getRestaurantId().equals(restaurant.getRestaurantId()));

        if(!belongsToOwner){
            throw new RuntimeException("Restaurant does not belong to this owner");
        }

        Pageable pageable = PageRequest.of(page, 8);

        Page<RestaurantBranch> branchList = restaurantBranchRepository.findAllByRestaurant(restaurant, pageable);

        AddRestaurantResponseDto restaurantResponseDto = restaurantMapping.entityToDto(restaurant);

        return branchMapping.listEntityToDto(branchList, restaurantResponseDto);

    }

    @Override
    public UpdateStaffSalaryResponseDto updateStaffSalary(UpdateStaffSalaryRequestDto request, Users user) {

        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

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

        staff.setSalary(request.getSalary());

        Staff savedStaff = staffRepository.save(staff);

        Users staffUser = savedStaff.getUser();

        return branchMapping.updateStaffSalaryToDto(savedStaff, staffUser);
    }

    @Override
    public UpdateManagerSalaryResponseDto updateManagerSalary(UpdateManagerSalaryRequestDto request, Users user) {
        Manager manager = managerRepository.findById(request.getManagerId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND));

        Owner owner = null;

        if(ownerRepository.findById(user.getUserId()).isPresent()){
            owner = ownerRepository.findById(user.getUserId()).get();
            List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();
            boolean belongsToOwner = ownerRestaurants.stream()
                    .anyMatch(ownerRestaurant -> ownerRestaurant.getRestaurant().getRestaurantId().equals(manager.getBranch().getRestaurant().getRestaurantId()));
            if(!belongsToOwner){
                throw new RuntimeException("Restaurant Does Not Belong to Owner");
            }
        }

        manager.setSalary(request.getSalary());

        Manager savedManager = managerRepository.save(manager);

        Users staffUser = savedManager.getUser();

        return branchMapping.updateManagerSalaryToDto(savedManager, staffUser);
    }

    @Override
    public String deleteBranch(Long branchId, Users user) {

        RestaurantBranch branch = restaurantBranchRepository.findById(branchId)
                .orElseThrow(() -> new RestaurantBranchNotFoundException(ExceptionMessages.RESTAURANT_BRANCH_NOT_FOUND));

        Owner owner = null;

        if(ownerRepository.findById(user.getUserId()).isPresent()){
            owner = ownerRepository.findById(user.getUserId()).get();
            List<OwnerRestaurant> ownerRestaurants = owner.getOwnerRestaurants();
            boolean belongsToOwner = ownerRestaurants.stream()
                    .anyMatch(ownerRestaurant -> ownerRestaurant.getRestaurant().getRestaurantId().equals(branch.getRestaurant().getRestaurantId()));
            if(!belongsToOwner){
                throw new RuntimeException("Restaurant Does Not Belong to Owner");
            }
        }

        restaurantBranchRepository.delete(branch);

        return "Deleted Successfully";
    }
}
