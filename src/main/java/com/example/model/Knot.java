package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "knots")
public class Knot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "place_of_transfer")
    private String placeOfTransfer;

    @Column(name = "date_of_transfer")
    private LocalDate dateOfTransfer;

    @Column(name = "numbers_of_days")
    private int numbersOfDays;
}
