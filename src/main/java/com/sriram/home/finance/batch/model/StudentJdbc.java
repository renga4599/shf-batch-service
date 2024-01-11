package com.sriram.home.finance.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentJdbc{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
