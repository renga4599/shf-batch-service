package com.sriram.home.finance.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentCsv{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}
