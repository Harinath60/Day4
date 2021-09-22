package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.util.Dbutil;
import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Slf4j
public class GetProductById {
    @SneakyThrows
    public static void main(String[] args) {
        String sql = "select * from contacts where id=?";
        try (
                Connection conn = Dbutil.createConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            int id = KeyBoardUtil.getInt("Enter contact id to search : ");
            stmt.setInt(1, id);
            stmt.execute();
            try(ResultSet rs = stmt.getResultSet()){
            if(rs.next()) {
                log.debug("id ={}, name = {}",rs.getInt("id"), rs.getString("firstname"));
            }
            else{
                log.warn("No contact data is available for id = {}",id);
            }
        } catch (Exception e) {
            log.error("error while acquiring data ", e);
        }
    }
}
}
