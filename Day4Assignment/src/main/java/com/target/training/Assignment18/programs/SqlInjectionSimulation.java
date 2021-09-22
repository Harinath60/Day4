package com.target.training.Assignment18.programs;

import com.target.training.Assignment18.util.KeyBoardUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlInjectionSimulation {
    public static void main(String[] args) {

        String username = KeyBoardUtil.getString("Enter Username: ");
        String password = KeyBoardUtil.getString("Enter Password: ");

        String sql = String.format("select * from users where username ='%s' and password='%s'",username,password);
        log.debug(sql);
    }
}
