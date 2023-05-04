package com.pky.service;

import java.util.List;

import com.pky.model.Contact;

public interface ContactService {
	public boolean saveContact(Contact contact);
	public List<Contact> getAllContact();
	public Contact getContactById(Integer id);
	public boolean updateContact(Contact contact);
	public boolean deleteContact(Integer id);

}

