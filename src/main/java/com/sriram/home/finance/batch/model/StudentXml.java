package com.sriram.home.finance.batch.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@XmlRootElement(name="student")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentXml{
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @XmlElement(name="first_name")
    public String getFirstName() {
        return this.firstName;
    }

}
