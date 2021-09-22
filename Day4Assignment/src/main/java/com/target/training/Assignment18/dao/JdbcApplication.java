package com.target.training.Assignment18.dao;

import com.target.training.Assignment18.entity.Contact;
import com.target.training.Assignment18.entity.Gender;
import com.target.training.Assignment18.util.Dbutil;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
public class JdbcApplication  implements ContactsDao{
    public JdbcApplication(){

    }

    @Override
    @SneakyThrows
    public void addContact(Contact contact) throws DaoException {
        String sql = "insert into contacts values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String choice;
        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1,contact.getId());
            stmt.setString(2,contact.getFirstname());
            stmt.setString(3,contact.getLastname());
            stmt.setString(4,String.valueOf(contact.getGender()));
            stmt.setString(5,contact.getEmail());
            stmt.setString(6,contact.getPhone());
            stmt.setString(7,contact.getAddress());
            stmt.setString(8,contact.getCity());
            stmt.setString(9,contact.getState());
            stmt.setInt(10,contact.getPincode());
            stmt.setString(11,contact.getCountry());
            String dob = (new SimpleDateFormat("yyyy-MM-dd").format(contact.getDate()));
            stmt.setDate(12, java.sql.Date.valueOf(dob));

            stmt.execute();
            log.debug("New product added successfully ....");
        }
        catch (Exception e){
            log.error("Error while acquiring db resources ",e);
        }

    }

    @Override
    @SneakyThrows
    public Contact getContact(int id) throws DaoException {
        String sql = "select * from contacts where id=?";
        Contact c = new Contact();
        try (
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.execute();

            try(ResultSet rs = stmt.getResultSet()){
                rs.next();
                 c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));

            } catch (Exception e) {
                log.error("error while acquiring data ", e);
            }
        }
        return c;
    }
    @SneakyThrows
    public void updateColumn(String column,String value1,int id){
        String sql = " update contacts set "+ column + " = ? where id = ?";
        try(    Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            if(value1.equals("pin"))
                stmt.setInt(1,Integer.parseInt(value1));
            else if(value1.equals("dateofBirth"))
                stmt.setDate(1, java.sql.Date.valueOf(value1));
            else
                stmt.setString(1,value1);

            stmt.setInt(2,id);
            stmt.execute();
        }catch (Exception e) {
            log.error("Error occurred while acquiring db resources", e);
        }

    }

    @Override
    @SneakyThrows
    public void updateContact(Contact contact) throws DaoException {
            int id = contact.getId();
            int choice = -1;
            while ((choice = option()) != 0) {
                switch (choice) {
                    case 1:
                        String firstname = KeyBoardUtil.getString("Enter firstname  : ");
                        updateColumn("firstname",firstname,id);
                            break;
                    case 2:
                        String lastname = KeyBoardUtil.getString("Enter lastname  : ");
                        updateColumn("lastname",lastname,id);
                        break;
                    case 3:
                        String gender = (KeyBoardUtil.getGender("Enter the Gender (MALE | FEMALE) :"));
                        updateColumn("gender",gender,id);
                        break;
                    case 4:
                        String email = KeyBoardUtil.getEmail("Enter email : ");
                        updateColumn("email",email,id);
                        break;
                    case 5:
                        String phone = KeyBoardUtil.getPhone("Enter the phone no.  : ");
                        updateColumn("phone",phone,id);
                        break;
                    case 6:
                        String address = KeyBoardUtil.getString("Enter the address  : ");
                        updateColumn("address",address,id);
                        break;
                    case 7:
                        String city = KeyBoardUtil.getString("Enter the city  : ");
                        updateColumn("city",city,id);
                        break;
                    case 8:

                        String state = KeyBoardUtil.getString("Enter the state  : ");
                        updateColumn("state",state,id);
                        break;

                case 9:

                        int pincode = KeyBoardUtil.getInt("Enter pincode : ");
                        String pin =""+pincode;
                        updateColumn("pincode",pin,id);
                        break;

                    case 10:

                        String country = KeyBoardUtil.getString("Enter the country  : ");
                        updateColumn("country",country,id);

                        break;
                    case 11:
                        String dateofBirth = new SimpleDateFormat("yyyy-MM-dd").format(KeyBoardUtil.getDate("Enter the Date of Birth (dd/MM/yyyy) : "));
                        updateColumn("dob",dateofBirth,id);

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
            System.out.println("|   0   |                   Exit                  |");
            System.out.println("|   1   |                firstname                |");
            System.out.println("|   2   |                lastname                 |");
            System.out.println("|   3   |                  gender                 |");
            System.out.println("|   4   |              email address              |");
            System.out.println("|   5   |              phone number               |");
            System.out.println("|   6   |                address                  |");
            System.out.println("|   7   |                  city                   |");
            System.out.println("|   8   |                  state                  |");
            System.out.println("|   9   |                pincode                  |");
            System.out.println("|   10  |                country                  |");
            System.out.println("|   11  |              DateOfBirth                |");

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
    @SneakyThrows
    public void deleteContact(int id) throws DaoException {
        String sql = "delete from contacts where id = ?";
        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, id);
            stmt.execute();
            log.debug("The contact of id ={} has been deleted successfully");
        }
        catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }
    }

    @Override
    public List<Contact> getContactByEmail(String email) throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts where email = ?";

        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,email);
            stmt.execute();
            try(ResultSet rs = stmt.getResultSet()) {
                while(rs.next()){
                    Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                    list.add(c);
                }
            }

        }catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }

        return list;
    }

    @Override
    public List<Contact> getContactByPhone(String phone) throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts where phone = ?";

        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,phone);
            stmt.execute();
            try(ResultSet rs = stmt.getResultSet()) {
                while(rs.next()){
                    Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                    list.add(c);
                }
            }

        }catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }

        return list;
    }

    @Override
    public List<Contact> getContactsByLastName(String lastname) throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts where lastname = ?";

        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            stmt.setString(1,lastname);
            stmt.execute();
            try(ResultSet rs = stmt.getResultSet()) {
                while(rs.next()){
                    Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                    list.add(c);
                }
            }

        }catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }

        return list;
    }

    @Override
    @SneakyThrows
    public List<Contact> getContactsByCity(String city) throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts where city = ?";

        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
                    stmt.setString(1,city);
                    stmt.execute();
                    try(ResultSet rs = stmt.getResultSet()) {
                        while(rs.next()){
                            Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                            list.add(c);
                        }
                    }

        }catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }

        return list;
    }

    @Override
    @SneakyThrows
    public List<Contact> getContacts() throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts";
        try(
                Connection conn = Dbutil.createConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                list.add(c);
            }
        }
        catch (Exception e){
            throw new DaoException();
        }
        return list;
    }

    @Override
    public List<Contact> getContactsByBirthDate(Date from, Date to) throws DaoException {
        List<Contact> list = new ArrayList<>();
        String sql = "select * from contacts where dob >= ? and dob <= ?";

        try(
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            String dobFrom = (new SimpleDateFormat("yyyy-MM-dd").format(from));
            String dobTo = (new SimpleDateFormat("yyyy-MM-dd").format(to));

            stmt.setDate(1, java.sql.Date.valueOf(dobFrom));
            stmt.setDate(2, java.sql.Date.valueOf(dobTo));
            stmt.execute();
            try(ResultSet rs = stmt.getResultSet()) {
                while(rs.next()){
                    Contact c = new Contact(rs.getInt("id"),rs.getString("firstname"),rs.getString("lastname"), Gender.valueOf(rs.getString("gender")),rs.getString("email"),rs.getString("phone"),rs.getString("address"),rs.getString("city"),rs.getString("state"),rs.getInt("pincode"),rs.getString("country"),rs.getDate("dob"));
                    list.add(c);
                }
            }

        }catch (Exception e){
            log.warn("Error while acquiring db resources ",e);
        }

        return list;
    }
}
