package com.dxctraining.inventorymgt.supplier.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dxctraining.inventorymgt.supplier.dto.CreateSupplierRequest;
import com.dxctraining.inventorymgt.supplier.entities.Supplier;
import com.dxctraining.inventorymgt.supplier.service.ISupplierService;

@Controller
public class SupplierController {
	
	@Autowired
	private ISupplierService service;
	
	@PostConstruct
	public void init() {
		Supplier supplier1 = new Supplier("dharma","522614");
		supplier1 = service.addSupplier(supplier1);
		
		Supplier supplier2 = new Supplier("teja","522303");
		supplier2 = service.addSupplier(supplier2);
		
	}
	
	@GetMapping("/supplier")
	public ModelAndView supplierDetails(@RequestParam("id")int id) {
		Supplier supplier = service.findById(id);
		ModelAndView modelAndView = new ModelAndView("supplierdetails", "supplier", supplier);
		return modelAndView;	
	}
	
	@GetMapping("/supplierlist")
	public ModelAndView allSuppliers() {
		List<Supplier>listAll = service.listAll();
		ModelAndView modelAndView = new ModelAndView("supplierlist","suppliers",listAll);
		return modelAndView;
		
	}
	 
	@GetMapping("/register")
	public ModelAndView registerSupplier() {
		ModelAndView modelAndView = new ModelAndView("registersupplier");
		return modelAndView;	
	}
	
	@GetMapping("/processregister")
	public ModelAndView processRegister(@RequestParam("name")String name, @RequestParam("password")String password) {
		Supplier supplier = new Supplier(name,password);
		supplier = service.addSupplier(supplier);
		ModelAndView modelAndView = new ModelAndView("details","supplier",supplier);
		return modelAndView;
	}
	
	
}