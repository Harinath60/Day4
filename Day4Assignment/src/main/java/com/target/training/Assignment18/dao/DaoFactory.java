package com.target.training.Assignment18.dao;

import lombok.SneakyThrows;

import java.util.ResourceBundle;

public final class DaoFactory {
    private DaoFactory(){

    }
    private static ContactsDao dao;
    @SneakyThrows
    public static ContactsDao getContactsDao(){
        ResourceBundle rb = ResourceBundle.getBundle("contact-dao");
        String discriminator = rb.getString("discriminator");

        switch (discriminator.toLowerCase()){
            case "arraylist":
                dao = new ContactManagement();
                break;
            case "hashmap":
                  dao = new HashContactManagement();
                  break;
            case "jdbc":
               dao = new JdbcApplication();
               break;
            default:
        throw new DaoException("Request for unknown type of implementation");
        }
        return dao;
    }
}
