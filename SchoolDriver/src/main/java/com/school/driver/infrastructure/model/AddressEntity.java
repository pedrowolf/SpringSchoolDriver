package com.school.driver.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressEntity {

    @Id
    @Column(name = "ID_ADDRESS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "DS_CITY", nullable = false)
    private String city;

    @Column(name = "DS_STREET", nullable = false)
    private String streetAddress;

    @Column(name = "DS_NEIGHBORHOOD", nullable = false)
    private String neighborhoodAddress;

    @Column(name = "DS_STATE", nullable = false)
    private String state;
}
