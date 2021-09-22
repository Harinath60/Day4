package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.util.Dbutil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@Slf4j
public class GetTableRecords {
    @SneakyThrows
    public static void main(String[] args) {
        String sql = "select * from contacts";
        try(
                Connection conn = Dbutil.createConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");

                log.debug("id = {}, name = {}", id, firstname);
            }
        }
    }
}
