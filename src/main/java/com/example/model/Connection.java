package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "connections")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_of_movement")
    private String typeOfMovement;

    @Column(name = "availability_of_tickets")
    private boolean availabilityOfTickets;

    @Column(name = "price_of_ticket")
    private double priceOfTicket;
}
