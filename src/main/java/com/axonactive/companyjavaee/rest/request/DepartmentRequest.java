package com.axonactive.companyjavaee.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentRequest {

    private String departmentName;
    private LocalDate startDate;
}
