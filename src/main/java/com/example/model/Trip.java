package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_trip")
    private String nameOfTrip;

    @Column(name = "start_of_trip")
    private String startOfTrip;

    @Column(name = "finish_of_trip")
    private String finishOfTrip;

    @ManyToMany(mappedBy = "trips")
    @JsonIgnore
    private List<Customer> customers;
}