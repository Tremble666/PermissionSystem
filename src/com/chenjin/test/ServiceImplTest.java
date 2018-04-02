package com.chenjin.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chenjin.dao.IEmployeeDAO;
import com.chenjin.domain.Department;
import com.chenjin.domain.Employee;
import com.chenjin.query.EmployeeQueryObject;
import com.chenjin.query.PageResult;
import com.chenjin.service.IDepartmentService;
import com.chenjin.service.IEmployeeService;
import com.chenjin.service.IPermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ServiceImplTest {
	@Autowired
	private IEmployeeService empService;
	
	@Autowired
	private IDepartmentService deptService;
	
	@Autowired
	private IPermissionService permissionService;
	
	
	@Test
	public void testPermission() throws Exception {
		permissionService.reload();
	}
	
	@Test
	public void testDept() throws Exception {
		for (int i = 0; i < 22; i++) {
			Department d = new Department();
			d.setName("haha"+i);
			d.setSn("120"+i);
			deptService.save(d);
		}
	}
	@Test
	public void testName() throws Exception {
		Department d = deptService.get(1l);
		d.setName("小狗");
		deptService.update(d);
	}
	
	@Test
	public void testSave() {
		for (int i = 0; i < 30; i++) {
			Employee e = new Employee();
			e.setName("chenjin_"+i);
			e.setAge(i);
			empService.save(e);
		}	
	}

	@Test
	public void testUpdate() {
		Employee e = new Employee();
		e.setName("东门吹雪");
		e.setAge(22);
		e.setId(3l);
		empService.update(e);
	}

	@Test
	public void testDelete() {
		empService.delete(11l);
	}

	@Test
	public void testGet() {
		System.out.println(empService.get(2l));
	}

	@Test
	public void testList() {
		List<Employee> list = empService.list();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	
	/*
	 * 测试高级查询与分页查询
	 */
	@Test
	public void testQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setCurrentPage(1);
		qo.setKeyword("chenjin");
		qo.setDeptId(2l);
		PageResult pr = empService.advancedPageQuery(qo);
		for (Object obj : pr.getListData()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void testQueryForList() throws Exception {
		List<Employee> list = empService.queryForList("obj.name = ? and obj.password = ?", new Object[]{"admin","1111"}, -1, -1);
		System.out.println(list.size());
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}
