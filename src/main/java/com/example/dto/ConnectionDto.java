package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDto {

    private Long id;
    private String typeOfMovement;
    private boolean availabilityOfTickets;
    private double priceOfTicket;
}
