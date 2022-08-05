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

    @Column(length = 20)
    @Size(max = 20)
    private String firstName;

    @Column(length = 10)
    @Size(max = 10)
    private String gender;

    @Column(length = 20)
    @Size(max = 20)
    private String lastName;

    @Column(length = 20)
    @Size(max = 20)
    private String middleName;
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

}
