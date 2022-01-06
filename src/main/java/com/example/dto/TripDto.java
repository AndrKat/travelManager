package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private Long id;
    private String nameOfTrip;
    private String startOfTrip;
    private String finishOfTrip;
}
