package com.target.training.Assignment18.entity;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ToString
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private Gender gender = Gender.MALE;
    private String email;
    private String phone;
    private String address;
    private String city = "Bangalore";
    private String state = "Karnataka";
    private Integer pincode;
    private String country = "India";
    private Date date;


}
