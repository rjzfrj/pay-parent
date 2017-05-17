package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.roncoo.pay.permission.entity.PmsRole;
import com.roncoo.pay.permission.service.PmsRoleService;

public class TestPmsRoleService {

	
	
	public static void main(String[] args) {
		final ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:/spring/spring-context-service.xml");
		app.start();
		PmsRoleService pmsMenuRoleService=(PmsRoleService) app.getBean("pmsRoleService");
		List<PmsRole> list=pmsMenuRoleService.listAllRole();
		System.out.println(list);
		//ApplicationContext appContext=new ClassPathXmlApplicationContext("classpath:/spring/spring-context-service.xml");
	
	}
}
