package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfInterestDto {

    private Long id;
    private String nameOfPlace;
    private String coordinates;
    private String description;
}
