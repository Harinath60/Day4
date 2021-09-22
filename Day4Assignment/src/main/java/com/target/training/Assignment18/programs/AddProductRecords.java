package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.util.Dbutil;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class AddProductRecords {
    @SneakyThrows
    public static void main(String[] args) {


        String choice;
        int id;
        String firstname;

        try (Connection conn = Dbutil.createConnection();
            Statement stmt = conn.createStatement();){
            do {
                try {
                    id = KeyBoardUtil.getInt("Enter the id: ");
                    firstname = KeyBoardUtil.getString("Enter the firstname: ");
                    String sql = String.format("insert into products values (%d , '%s')", id, firstname);

                    stmt.execute(sql);
                } catch (SQLException throwables) {
                    log.warn("there was an error",throwables.getMessage());
                }

                choice = KeyBoardUtil.getString("want to add another(yes/no)");

                if(choice.trim().equals(""))
                    choice = "yes";
            }while (choice.equalsIgnoreCase("yes"));
        }
        catch (Exception e){
            log.error("Error while acquiring db resources ",e);
        }
    }
}
