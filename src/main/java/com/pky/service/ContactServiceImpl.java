package com.pky.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pky.entity.ContactEntity;
import com.pky.model.Contact;
import com.pky.repository.ContactDtlsRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDtlsRepository repo;

	@Override
	public boolean saveContact(Contact contact) {
		
		ContactEntity entity=new ContactEntity();
		BeanUtils.copyProperties(contact,entity);
		ContactEntity savedEntity=repo.save(entity);
		
		System.out.println(savedEntity);
		return savedEntity.getContactId()!=null ;
	}

	@Override
	public List<Contact> getAllContact() {
		
		List<ContactEntity> entities=repo.findAll();
		
		//legacy approch
	/*	List<Contact> contacts=new ArrayList<Contact>();
		for(ContactEntity entity:entities) {
			Contact contact=new Contact();
			BeanUtils.copyProperties(entity, contact);
			contacts.add(contact);
		} */

		
		//java 8 stream approch		
		List<Contact> contacts=entities.stream().map(entity->{
			Contact contact=new Contact();
			BeanUtils.copyProperties(entity, contact);
			return contact;
		}).collect(Collectors.toList());
		return contacts;
	}

	@Override
	public Contact getContactById(Integer id) {
	Optional<ContactEntity> findById=repo.findById(id);
	if(findById.isPresent()) {
		ContactEntity entity=findById.get();
		Contact contact=new Contact();
		BeanUtils.copyProperties(entity, contact);
		return contact;
	}
		return null;
	}
	
	@Override
	public boolean deleteContact(Integer id) {
		repo.deleteById(id);
	/*	if(repo.findById(id)==null) {
			return true;
		} */
		return true;
	}

}
