package com.example.growtogether.controller;

import com.example.growtogether.dto.restaurantTable.request.AddRestaurantTableRequestDto;
import com.example.growtogether.dto.restaurantTable.request.AssignStaffToTableRequestDto;
import com.example.growtogether.dto.restaurantTable.response.AddTableResponseDto;
import com.example.growtogether.dto.restaurantTable.response.AssignStaffToTableResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.RestaurantTableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branch/table")
@RequiredArgsConstructor
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER')")
    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<AddTableResponseDto>> addTable(@Valid @RequestBody AddRestaurantTableRequestDto request, @AuthenticationPrincipal Users user){

        AddTableResponseDto details = restaurantTableService.addTable(request, user);

        ApplicationResponse<AddTableResponseDto> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "Table Added Successfully",
                details
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','OWNER','MANAGER')")
    @PostMapping("/assign-staf-table")
    public ResponseEntity<ApplicationResponse<AssignStaffToTableResponseDto>> assignStaffToTable(@Valid @RequestBody AssignStaffToTableRequestDto request, @AuthenticationPrincipal Users user){

        AssignStaffToTableResponseDto details = restaurantTableService.assignStaffToTable(request, user);

        ApplicationResponse<AssignStaffToTableResponseDto> response = new ApplicationResponse<>(
          HttpStatus.CREATED.value(),
          "Assigned Waiter To Table Successfully",
                details
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
