package com.axonactive.companyjavaee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatives {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    @Size(max = 255)
    private String fullName;

    @Min(0)
    @Max(1)
    private Integer gender;

    @Column(length = 255)
    @Size(max = 255)
    private String phoneNumber;

    @Column(length = 255)
    @Size(max = 255)
    private String relationship;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
