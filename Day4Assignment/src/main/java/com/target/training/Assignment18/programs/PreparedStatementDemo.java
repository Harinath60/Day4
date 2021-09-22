package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.entity.Gender;
import com.target.training.Assignment18.util.Dbutil;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class PreparedStatementDemo {
    public static void main(String[] args) {
        String sql = "insert into contacts values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String choice;


        try(
                Connection con = Dbutil.createConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ){
            do {
                    try {
                        int id = KeyBoardUtil.getInt("Enter the id: ");
                        String firstname = KeyBoardUtil.getString("Enter the firstname: ");
                        String lastname = KeyBoardUtil.getString("Enter lastname  : ");
                        String gender = String.valueOf(Gender.valueOf(KeyBoardUtil.getGender("Enter the Gender (MALE | FEMALE) :")));
                        String email = KeyBoardUtil.getEmail("Enter email : ");
                        String phone = KeyBoardUtil.getPhone("Enter the phone no.  : ");
                        String address = KeyBoardUtil.getString("Enter the address  : ");
                        String city = KeyBoardUtil.getString("Enter the city  : ");
                        String state = KeyBoardUtil.getString("Enter the state  : ");
                        int pincode = KeyBoardUtil.getInt("Enter pincode : ");
                        String country = KeyBoardUtil.getString("Enter the country  : ");
                        String dateofBirth = KeyBoardUtil.getString("Enter the Date of Birth (yyyy-mm-dd) : ");

                        stmt.setInt(1,id);
                        stmt.setString(2,firstname);
                        stmt.setString(3,lastname);
                        stmt.setString(4, String.valueOf(gender));
                        stmt.setString(5,email);
                        stmt.setString(6,phone);
                        stmt.setString(7,address);
                        stmt.setString(8,city);
                        stmt.setString(9,state);
                        stmt.setInt(10,pincode);
                        stmt.setString(11,country);
                        stmt.setDate(12, java.sql.Date.valueOf(dateofBirth));

                        stmt.execute();
                        log.debug("New product added successfully id ={}, firstname={}",id, firstname);
                    } catch (SQLException e) {
                        log.warn("there was an error", e.getMessage());
                    }

                    choice = KeyBoardUtil.getString("want to add another(yes/no)");

                    if (choice.trim().equals(""))
                        choice = "yes";
            }while (choice.equalsIgnoreCase("yes"));
            }
            catch (Exception e){
                log.error("Error while acquiring db resources ",e);
            }

    }
}
