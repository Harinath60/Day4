package com.target.training.Assignment18.util;



import com.target.training.Assignment18.entity.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class    KeyBoardUtil {
    public static int getInt(String message){
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }



    public static String getString(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);

        return sc.nextLine().toLowerCase();
    }

    public static Date getDate(String s) throws ParseException {
        System.out.print(s);
        Scanner sc = new Scanner(System.in);

        String date = sc.nextLine();

        return new SimpleDateFormat("dd/MM/yyyy").parse(date);

    }

    public static String getEmail(String s) {
        while(true) {
            System.out.print(s);
            Scanner sc = new Scanner(System.in);
            String email = sc.nextLine().toLowerCase();
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            if (matcher.matches())
                return email;
            else
                System.out.println("Invalid Email Address. Please Try again!!!");
        }

    }

    public static String getPhone(String s) {
        while(true) {
            System.out.print(s);
            Scanner sc = new Scanner((System.in));
            String phone = sc.nextLine();
            if (phone.matches("\\d{10}"))
                return phone;
            else
                System.out.println("Invalid phone no. It should be a 10 digit phone no. Please try again!!!");
        }
    }

    public static String getGender(String s) {
        while (true) {
            System.out.print(s);
            Scanner sc = new Scanner(System.in);
            String gender = sc.nextLine().toUpperCase();
            if (gender.equals("MALE") || gender.equals("FEMALE"))
                return gender;
            else
                System.out.println("Enter the Valid Gender.");
        }
    }
}
