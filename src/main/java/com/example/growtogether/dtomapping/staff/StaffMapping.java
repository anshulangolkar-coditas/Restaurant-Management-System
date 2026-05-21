package com.example.growtogether.dtomapping.staff;

import com.example.growtogether.dto.staff.response.*;
import com.example.growtogether.dtomapping.restaurant.RestaurantMapping;
import com.example.growtogether.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StaffMapping {

    private final RestaurantMapping restaurantMapping;

    public GetAllStaffResponseDto staffDetailsToDto(Staff staff, Users user, Restaurant restaurant, RestaurantBranch branch){

        return GetAllStaffResponseDto.builder()
                .staffId(staff.getStaffId())
                .fistName(user.getFistName())
                .lastName(user.getLastName())
                .salary(staff.getSalary())
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .build();
    }

    public List<GetAllStaffResponseDto> listStaffDetailsToDto(Page<Staff> staff/*, List<Users> user*/, Restaurant restaurant, RestaurantBranch branch){

        return staff.stream()
                .map(s -> staffDetailsToDto(s, s.getUser(), restaurant, branch))
                .toList();
    }

    public GetManagerDetailsResponseDto managerDetailsResponseDto(Manager manager){

        if (manager == null){
            return null;
        }

        return GetManagerDetailsResponseDto.builder()
                .managerId(manager.getManagerId())
                .managerName(manager.getUser().getFistName()+" "+manager.getUser().getLastName())
                .email(manager.getUser().getEmailId())
                .salary(manager.getSalary())
                .joinedDate(manager.getUser().getJoinedDate())
                .build();

    }

    public GetStaffDetailsResponseDto staffDetailsResponseDto(Staff staff){

        if (staff == null){
            return null;
        }

        return GetStaffDetailsResponseDto.builder()
                .staffId(staff.getStaffId())
                .staffName(staff.getUser().getFistName()+" "+staff.getUser().getLastName())
                .email(staff.getUser().getEmailId())
                .salary(staff.getSalary())
                .joinedDate(staff.getUser().getJoinedDate())
                .build();

    }

    public GetAllBranchDetailsResponse branchDetailsResponse(RestaurantBranch branch){

        return GetAllBranchDetailsResponse.builder()
                .branchId(branch.getBranchId())
                .branchName(branch.getBranchName())
                .branchAddress(branch.getBranchAddress())
                .contactNumber(branch.getContactNumber())
                .numberOfTables(branch.getNumberOfTables())

                .manager(
                        managerDetailsResponseDto(branch.getManager())
                )

                .staffList(
                        branch.getStaffList()
                                .stream()
                                .map(this::staffDetailsResponseDto)
                                .toList()
                )

                .build();
    }

    public List<GetAllRestaurantDetailsResponse> restaurantAndStaffResponseDto(List<Restaurant> restaurants){

        return restaurants.stream()
                .map(restaurant ->

                        GetAllRestaurantDetailsResponse.builder()
                                .restaurantId(restaurant.getRestaurantId())
                                .restaurantName(restaurant.getRestaurantName())
                                .type(restaurant.getRestaurantType())

                                .branches(
                                        restaurant.getBranches()
                                                .stream()
                                                .map(this::branchDetailsResponse)
                                                .toList()
                                )

                                .build()

                ).toList();
    }



}
