package com.target.training.Assignment18.dao;

import com.target.training.Assignment18.entity.Contact;

import java.util.Date;
import java.util.List;

public interface ContactsDao {

    //CRUD Operations

    public void addContact(Contact contact) throws DaoException;
    public Contact getContact(int id) throws DaoException;
    public void updateContact(Contact contact) throws DaoException;
    public void deleteContact(int id) throws DaoException;

    //Queries

    public List<Contact> getContactByEmail(String email) throws DaoException;
    public List<Contact> getContactByPhone(String phone) throws DaoException;

    public List<Contact> getContactsByLastName(String lastname) throws DaoException;
    public List<Contact> getContactsByCity(String city) throws DaoException;
    public List<Contact> getContacts() throws DaoException;
    public List<Contact> getContactsByBirthDate(Date from, Date to) throws DaoException;

}
