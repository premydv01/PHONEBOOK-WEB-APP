package com.pky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pky.model.Contact;
import com.pky.service.ContactService;

@Controller
public class ContactInfoController {
	
	@Autowired
	private ContactService service;
	
	@GetMapping(value = {"/","/addContact"})
	public String  loadForm(Model model) {
		Contact c=new Contact();
		model.addAttribute("contact",c);
		return "contactInfo";
	}
	
	@PostMapping(value = "/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact")Contact c,Model model) {
		 boolean isSaved=service.saveContact(c);
		 
		 if(isSaved) {
			 model.addAttribute("succMsg","Contact is Saved");
		 }else {
			 model.addAttribute("errMsg","Failed to Save Contact");
		 }
		return "contactInfo";
	}
	 
	@GetMapping(value = "/viewContacts")
	public String handleViewContactsLinks(Model model) {
	List<Contact> contactList=service.getAllContact();
	
		model.addAttribute("contacts",contactList);
		return "viewContacts";
	}
}
