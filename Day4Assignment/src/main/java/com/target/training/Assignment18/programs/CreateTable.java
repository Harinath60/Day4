package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.util.Dbutil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Statement;


@Slf4j
public class CreateTable {
    @SneakyThrows
    public static void main(String[] args) {
        String cmd = "create table contacts("+
                "id integer primary key,"+
                "firstname varchar(50) not null,"+
                "lastname varchar(50) not null,"+
                "gender varchar(10) not null,"+
                "email varchar(50) not null,"+
                "phone varchar(10) not null,"+
                "address varchar(50) not null,"+
                "city varchar(20) default 'Bangalore',"+
                "state varchar(20) default 'Karnataka',"+
                "pincode integer not null,"+
                "country varchar(20) default 'India',"+
                " dob date not null);";
        try(
                Connection conn = Dbutil.createConnection();
                Statement stmt = conn.createStatement();
                ){
            stmt.execute(cmd);
            log.debug("SQL Command executed successfully");
        }
        catch (Exception e){
          //      e.printStackTrace();
            log.error("There was an error",e);
        }
    }
}
