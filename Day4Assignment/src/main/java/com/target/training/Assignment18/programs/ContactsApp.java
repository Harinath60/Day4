package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.dao.ContactManagement;
import com.target.training.Assignment18.dao.ContactsDao;
import com.target.training.Assignment18.dao.DaoException;
import com.target.training.Assignment18.dao.DaoFactory;
import com.target.training.Assignment18.entity.Contact;
import com.target.training.Assignment18.entity.Gender;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsApp {
    ContactsDao cd;

    public static void main(String[] args) {
        new ContactsApp().start();
    }
    void start(){
       // cd = new ContactManagement();
        try{
            cd = DaoFactory.getContactsDao();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int choice = -1;

        while ((choice = menu())!= 0){
            switch (choice){
                case 1:
                    addContactDetails();
                    break;
                case 2:
                    listAllContacts();
                    break;
                case 3:
                    searchByLastName();
                    break;
                case 4:
                    searchByEmail();
                    break;
                case 5:
                    searchByPhoneNo();
                    break;
                case 6:
                    searchByCity();
                    break;
                case 7:
                    searchById();
                    break;
                case 8:
                    searchByDateOfBirthRange();
                    break;
                case 9:
                    deleteContact();
                    break;
                case 10:
                    updateContact();
                    break;

            }
        }
        System.out.println("Bye!!!");
    }



    int menu() {
        int choice = -1;
        do{
            System.out.println("+=============================================================+");
            System.out.println("|                      CONTACTS APP                           |");
            System.out.println("+=============================================================+");
            System.out.println("|   0   |               Exit                                  |");
            System.out.println("|   1   |           Add new contact                           |");
            System.out.println("|   2   |          List All contacts                          |");
            System.out.println("|   3   |          Search by lastname                         |");
            System.out.println("|   4   |        Search by email address                      |");
            System.out.println("|   5   |         Search by phone number                      |");
            System.out.println("|   6   |           Search by city                            |");
            System.out.println("|   7   |           Search by id                              |");
            System.out.println("|   8   |     Search by Date of Birth Range(From and To)      |");
            System.out.println("|   9   |           Delete Contact                            |");
            System.out.println("|  10   |           Edit Contact                              |");
            System.out.println("+-------------------------------------------------------------+");

            try {
                choice = KeyBoardUtil.getInt("\nEnter your Choice: ");
                if(choice < 0 || choice > 10){
                    System.out.println("\nInvalid choice, please try again!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (choice < 0 || choice > 10);

        return choice;

    }

    private void addContactDetails() {
        try {
            int id = KeyBoardUtil.getInt("Enter id     : ");
            String firstname = KeyBoardUtil.getString("Enter firstname   : ");
            String lastname = KeyBoardUtil.getString("Enter lastname  : ");
            Gender gender = Gender.valueOf(KeyBoardUtil.getGender("Enter the Gender (MALE | FEMALE) :"));
            String email = KeyBoardUtil.getEmail("Enter email : ");
            String phone = KeyBoardUtil.getPhone("Enter the phone no.  : ");
            String address = KeyBoardUtil.getString("Enter the address  : ");
            String city = KeyBoardUtil.getString("Enter the city  : ");
            String state = KeyBoardUtil.getString("Enter the state  : ");
            int pincode = KeyBoardUtil.getInt("Enter pincode : ");
            String country = KeyBoardUtil.getString("Enter the country  : ");
            Date dateofBirth = KeyBoardUtil.getDate("Enter the Date of Birth (dd/MM/yyyy) : ");


            Contact c = new Contact(id, firstname,lastname,gender,email,phone,address,city,state,pincode,country,dateofBirth);
            cd.addContact(c);
            System.out.println("New product added successfully!");
        }
        catch(Exception e){
            System.out.println("There was a problem while adding the product details.");
            System.out.println(e.getMessage());
        }
    }

    void pattern(String s){
        for(int i=0;i<217;i++)
            System.out.print(s);
        System.out.println();
    }
    private void listAllContacts() {
        try {
            List<Contact> contacts = cd.getContacts();
            if(contacts.isEmpty())
                System.out.println("No Contacts are available....");
            else{
                System.out.println("The list of contacts are.......");
                pattern("=");
                System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                pattern("=");
                for(Contact c: contacts)
                    System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                pattern("-");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    private void searchByLastName() {
        try {
            String lastname = KeyBoardUtil.getString("Enter the last name: ");
            List<Contact> contacts = cd.getContactsByLastName(lastname);
            if(contacts.isEmpty()){
                System.out.println("No contacts are available.....");
            }
            else{
                System.out.println("The list of contacts whose lastname "+lastname+" are.......");
                pattern("=");
                System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                pattern("=");
                for(Contact c: contacts)
                    System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                pattern("-");
            }
            System.out.println("Search has been done successfully!!!");
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    private void searchByEmail() {
        try {
                String email = KeyBoardUtil.getEmail("Enter the Email Address: ");
                List<Contact> contacts =  cd.getContactByEmail(email);
                if(contacts.isEmpty())
                    System.out.println("No Contacts are available....");
                else{
                    System.out.println("The list of contacts whose email address is "+email+" are.......");
                    pattern("=");
                    System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                    pattern("=");
                    for(Contact c: contacts)
                        System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                    pattern("-");
                }
                System.out.println("Search has been done successfully!!!");
                
        } catch (DaoException e) {
            System.out.println("There is an error in the email address provided");
        }
    }

    private void searchByPhoneNo() {
        try {
            String phone = KeyBoardUtil.getPhone("Enter the Phone no: ");
            List<Contact> contacts = cd.getContactByPhone(phone);
            if(contacts.isEmpty())
                System.out.println("No Contacts are available....");
            else{
                System.out.println("The list of contacts whose phone no. "+phone+" are.....");
                pattern("=");
                System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                pattern("=");
                for(Contact c: contacts)
                    System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                pattern("-");
            }
            System.out.println("Search has been done successfully!!!");

    }
        catch (DaoException e) {
            e.printStackTrace();
        }
    }

        private void searchByCity() {
        try {
            String city = KeyBoardUtil.getString("Enter the city : ");
            List<Contact> contacts = cd.getContactsByCity(city);
            if(contacts.isEmpty())
                System.out.println("No Contacts are available....");
            else{
                System.out.println("The list of contacts whose city is "+city+" are.....");
                pattern("=");
                System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                pattern("=");
                for(Contact c: contacts)
                    System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                pattern("-");
            }
            System.out.println("Search has been done successfully!!!");
        } catch (DaoException e) {
            System.out.println("There is an error in the city");
        }
    }

    @SneakyThrows
    private void searchById() {
        try {
            int id = KeyBoardUtil.getInt("Enter the ID: ");
            Contact c  = cd.getContact(id);
            System.out.println("The contact whose id "+id+" is.... ");
            pattern("=");
            System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
            pattern("=");
            System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());

            System.out.println("Search has been done successfully!!!");

        }
         catch (DaoException e) {
            System.out.println("You have entered an Invalid ID");
        }

    }

    private void searchByDateOfBirthRange() {
        try {
            Date fromDate = KeyBoardUtil.getDate("Enter the From Date: ");
            Date toDate = KeyBoardUtil.getDate("Enter the To Date: ");
            List<Contact> contacts = cd.getContactsByBirthDate(fromDate, toDate);
            if(contacts.isEmpty())
                System.out.println("No Contacts are available....");
            else{
                System.out.println("The list of contacts whose dob are in  the range of "+fromDate+" and "+toDate+" are ");
                pattern("=");
                System.out.printf("|  %-10s %-20s %-20s %10s %20s %10s %30s %20s %20s %10s %15s %15s  |\n", "ID", "Firstname", "Lastname","Gender","Email","Phone","Address","City","State","Pincode","Country","DateOfBirth");
                pattern("=");
                for(Contact c: contacts)
                    System.out.printf("|  %-10d %-20s %-20s %10s %20s %10s %30s %20s %20s %10d %15s %15s  |\n", c.getId(), c.getFirstname(), c.getLastname(),c.getGender(),c.getEmail(),c.getPhone(),c.getAddress(),c.getCity(),c.getState(),c.getPincode(),c.getCountry(),c.getDate());
                pattern("-");
            }
            System.out.println("Search has been done successfully!!!");
        } catch (ParseException | DaoException e) {
            e.printStackTrace();
        }
    }


    private void deleteContact() {
        try {
            int id = KeyBoardUtil.getInt("Enter the ID: ");
            cd.deleteContact(id);
            System.out.println("The contact has been deleted successfully!!!");
        } catch (DaoException e) {
            System.out.println("You have entered an Invalid ID");
        }
    }





    private void updateContact() {
        try {
            int id = KeyBoardUtil.getInt("Enter id     : ");
            boolean check = false;
            Contact c = new Contact();
            if(check) {
                c = cd.getContact(id);
                check = true;
            }
            if(check) {
                cd.updateContact(c);
                System.out.println("New contact updated successfully!");
            }
            else
                System.out.println("Contact of given id "+id +" is not available");

        } catch (Exception e) {
            System.out.println("There was a problem while updating the product details.");
            System.out.println(e.getMessage());
        }

    }



}

