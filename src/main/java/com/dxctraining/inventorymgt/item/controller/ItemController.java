package com.dxctraining.inventorymgt.item.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dxctraining.inventorymgt.item.entities.Computer;
import com.dxctraining.inventorymgt.item.entities.Phone;
import com.dxctraining.inventorymgt.item.service.IItemService;
import com.dxctraining.inventorymgt.supplier.entities.Supplier;
import com.dxctraining.inventorymgt.supplier.service.ISupplierService;
@Controller
public class ItemController {

	@Autowired
	private IItemService service1;
	
	@Autowired
	private ISupplierService service2;
	
	@PostConstruct
	public void init() {
		Supplier supplier1 = new Supplier("poornima","jklm");
		service2.addSupplier(supplier1);
		Supplier supplier2 = new Supplier("pardhasaradhi","poiu");
		service2.addSupplier(supplier2);
		
		Computer computer1 = new Computer("xiaomi",supplier1,1024);
		service1.addItem(computer1);
		Phone phone1 = new Phone("asus",supplier2, 64);
		service1.addItem(phone1);
	}
	
	@GetMapping("/computers")
	public ModelAndView AllComputers() {
		List<Computer>computer = service1.computerlist();
		System.out.println("inside computers method, computers="+computer);
		ModelAndView modelAndView = new ModelAndView("computerlist", "computers", computer);
		return modelAndView;	
	}
	
	@GetMapping("/phones")
	public ModelAndView Allphones() {
		List<Phone>phone =service1.phonelist();
		System.out.println("inside phones method, phones="+phone);
		ModelAndView modelAndView = new ModelAndView("phonelist","phones", phone);
		return modelAndView;
	}
	

}
