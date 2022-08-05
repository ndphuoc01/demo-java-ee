package com.axonactive.companyjavaee.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(length = 9, unique = true, name = "employee_id")
    @Size(max = 9)

    private String employeeId;
    private LocalDate dateOfBirth;
    private String firstName;
    private String gender;
    private String lastName;
    private String middleName;
    private Integer salary;
    private Department department;

}
