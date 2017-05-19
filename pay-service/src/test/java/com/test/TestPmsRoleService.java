package com.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zyzf.polymer.pay.permission.entity.PmsRole;
import com.zyzf.polymer.pay.permission.service.PmsMenuService;
import com.zyzf.polymer.pay.permission.service.PmsRoleService;

public class TestPmsRoleService {

	
	
	public static void main(String[] args) {
		final ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:/spring/spring-context-service.xml");
		app.start();
	/*	PmsRoleService pmsMenuRoleService=(PmsRoleService) app.getBean("pmsRoleService");
		List<PmsRole> list=pmsMenuRoleService.listAllRole();
		System.out.println(list);*/
		PmsMenuService pmsMenuService= (PmsMenuService) app.getBean("pmsMenuService");
		List treeData = pmsMenuService.getListByParent(null);
		System.out.println(treeData);
		//ApplicationContext appContext=new ClassPathXmlApplicationContext("classpath:/spring/spring-context-service.xml");
	
	}
}
