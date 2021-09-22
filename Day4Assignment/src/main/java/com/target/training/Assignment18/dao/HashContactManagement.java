package com.target.training.Assignment18.dao;

import com.target.training.Assignment18.entity.Contact;
import com.target.training.Assignment18.entity.Gender;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashContactManagement  implements ContactsDao{

    HashMap<Integer,Contact> contacts = new HashMap<>();
    @SneakyThrows
    public HashContactManagement() {
        Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse("06/12/1999");
        Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse("12/06/1987");
        Date d3 = new SimpleDateFormat("dd/MM/yyyy").parse("25/04/1887");
        Date d4 = new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2000");
        contacts.put(1,new Contact(6,"hari","gajulapalli", Gender.MALE,"hari@gmail.com","8919080504","tmp","kadapa","AP",516502,"India",d1));
        contacts.put(2,new Contact(16,"rohit","sharma", Gender.MALE,"rohit@gmail.com","9827080504","mumbai","mumbai","MAH",516502,"India",d2));
        contacts.put(3,new Contact(23,"sindhu","posa", Gender.FEMALE,"sindhu@gmail.com","8982372323","jublee hills","hyderabad","TS",516502,"India",d3));
        contacts.put(4,new Contact(45,"smriti","mandhana", Gender.FEMALE,"sm@gmail.com","4985784935","pune","pune","MAH",516502,"India",d4));


    }
    @Override
    public void addContact(Contact contact) throws DaoException {
        contacts.put(contact.getId(), contact);

    }

    @Override
    public Contact getContact(int id) throws DaoException {
        Contact c;
        c = contacts.get(id);

        return c;
    }

    @Override
    @SneakyThrows
    public void updateContact(Contact contact) throws DaoException {
        int choice = -1;
        while ((choice = option()) != 0) {
            switch (choice) {
                case 1:
                    String firstname = KeyBoardUtil.getString("Enter firstname   : ");
                    contact.setFirstname(firstname);
                    break;
                case 2:
                    String lastname = KeyBoardUtil.getString("Enter lastname  : ");
                    contact.setLastname(lastname);
                    break;
                case 3:
                    Gender gender = Gender.valueOf(KeyBoardUtil.getGender("Enter the Gender (MALE | FEMALE) :"));
                    contact.setGender(gender);
                    break;
                case 4:
                    String email = KeyBoardUtil.getEmail("Enter email : ");
                    contact.setEmail(email);
                    break;
                case 5:
                    String phone = KeyBoardUtil.getPhone("Enter the phone no.  : ");
                    contact.setPhone(phone);
                    break;
                case 6:
                    String address = KeyBoardUtil.getString("Enter the address  : ");
                    contact.setAddress(address);
                    break;
                case 7:
                    String city = KeyBoardUtil.getString("Enter the city  : ");
                    contact.setCity(city);
                    break;
                case 8:
                    String state = KeyBoardUtil.getString("Enter the state  : ");
                    contact.setState(state);
                    break;
                case 9:
                    int pincode = KeyBoardUtil.getInt("Enter pincode : ");
                    contact.setPincode(pincode);
                    break;
                case 10:
                    String country = KeyBoardUtil.getString("Enter the country  : ");
                    contact.setCountry(country);
                    break;
                case 11:
                    Date dateofBirth = KeyBoardUtil.getDate("Enter the Date of Birth (dd/MM/yyyy) : ");
                    contact.setDate(dateofBirth);
                    break;
                default:
                    throw new DaoException();
            }
            System.out.println("The new object updated successfully....");
        }
        System.out.println("Bye!!!");

    }

    private int option() {
        int choice = -1;
        System.out.println("Enter the option of your object need to get updated.");
        System.out.println("+-----------------------------------------------------+");
        do{
            System.out.println("|   0   |,                  Exit                  |");
            System.out.println("|   1   |,               firstname                |");
            System.out.println("|   2   |,               lastname                 |");
            System.out.println("|   3   |,                 gender                 |");
            System.out.println("|   4   |,             email address              |");
            System.out.println("|   5   |,             phone number               |");
            System.out.println("|   6   |,               address                  |");
            System.out.println("|   7   |,                 city                   |");
            System.out.println("|   8   |,                 state                  |");
            System.out.println("|   9   |,               pincode                  |");
            System.out.println("|   10  |,               country                  |");
            System.out.println("|   11  |,             DateOfBirth                |");

            System.out.println("--------------------------------------------------+");
            try {
                choice = KeyBoardUtil.getInt("Enter your Choice: ");
                if(choice < 0 || choice > 11){
                    System.out.println("Invalid choice, please try again!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (choice < 0 || choice > 11);

        return choice;
    }



    @Override
    public void deleteContact(int id) throws DaoException {
        if(!contacts.containsKey(id))
            throw new DaoException();
        contacts.remove(id);
    }

    @Override
    public List<Contact> getContactByEmail(String email) throws DaoException {
        Collection<Contact> c = contacts.values();
        List<Contact> list = c.stream().filter(contact -> contact.getEmail().equals(email)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Contact> getContactByPhone(String phone) throws DaoException {
        Collection<Contact> c = contacts.values();
        List<Contact> list = c.stream().filter(contact -> contact.getPhone().equals(phone)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Contact> getContactsByLastName(String lastname) throws DaoException {
        Collection<Contact> c = contacts.values();
        List<Contact> list = c.stream().filter(contact -> contact.getLastname().equals(lastname)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Contact> getContactsByCity(String city) throws DaoException {
        Collection<Contact> c = contacts.values();
        List<Contact> list = c.stream().filter(contact -> contact.getCity().equals(city)).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Contact> getContacts() throws DaoException {
        Collection<Contact> c =contacts.values();
        List<Contact> list = c.stream().collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Contact> getContactsByBirthDate(Date from, Date to) throws DaoException {
        Collection<Contact> c = contacts.values();
        List<Contact> list = c.stream().filter(contact -> contact.getDate().after(from) && contact.getDate().before(to)).collect(Collectors.toList());
        return list;
    }
}
