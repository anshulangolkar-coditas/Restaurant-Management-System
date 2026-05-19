package com.example.growtogether.dto.restaurant.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRestaurantRequestDto {

    @NotNull
    private Long restaurantId;

}
