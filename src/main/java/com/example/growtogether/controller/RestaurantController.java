package com.example.growtogether.controller;

import com.example.growtogether.dto.restaurant.request.AddRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.request.DeleteRestaurantRequestDto;
import com.example.growtogether.dto.restaurant.response.AddRestaurantResponseDto;
import com.example.growtogether.entity.Users;
import com.example.growtogether.response.ApplicationResponse;
import com.example.growtogether.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    @PostMapping("/")
    public ResponseEntity<ApplicationResponse<AddRestaurantResponseDto>> addRestaurant(@Valid @RequestBody AddRestaurantRequestDto request, @AuthenticationPrincipal Users user) {

        AddRestaurantResponseDto restaurantDetails = restaurantService.addRestaurant(request, user);

        ApplicationResponse<AddRestaurantResponseDto> response = new ApplicationResponse<>(
                HttpStatus.CREATED.value(),
                "Restaurant Added Successfully",
                restaurantDetails
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    @DeleteMapping("/")
    public ResponseEntity<ApplicationResponse<String>> deleteRestaurant(@Valid @RequestBody DeleteRestaurantRequestDto request, @AuthenticationPrincipal Users user) {

        String message = restaurantService.deleteRestaurant(request, user);

        ApplicationResponse<String> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Restaurant Deleted Successfully",
                message
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','OWNER')")
    @GetMapping("/")
    public ResponseEntity<ApplicationResponse<List<AddRestaurantResponseDto>>> getAllRestaurants(@RequestParam int page, @AuthenticationPrincipal Users user) {

        List<AddRestaurantResponseDto> restaurantList = restaurantService.getAllRestaurants(page, user);

        ApplicationResponse<List<AddRestaurantResponseDto>> response = new ApplicationResponse<>(
                HttpStatus.OK.value(),
                "Retrieved All Restaurants Successfully",
                restaurantList
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
