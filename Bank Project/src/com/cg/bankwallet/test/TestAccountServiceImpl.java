package com.cg.bankwallet.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.bankwallet.bean.AccountBean;
import com.cg.bankwallet.bean.CustomerBean;
import com.cg.bankwallet.service.AccountServiceImpl;
import com.cg.bankwallet.service.IAccountService;

public class TestAccountServiceImpl {

	public static IAccountService service=null;
	
	@BeforeClass
	public static void init(){
		service=new AccountServiceImpl();
	}
	
	@Test
	public void testCreateAccountNegative1(){
		
		AccountBean accountBean=new AccountBean();
		CustomerBean customerBean=new CustomerBean();
		customerBean.setFirstName("hari");
		customerBean.setLastName("varun");
		customerBean.setEmailId("mshvarunkumar@gmail.com");
		customerBean.setPhoneNo("9876543212");
		customerBean.setAddress("chennai");
	}
}
