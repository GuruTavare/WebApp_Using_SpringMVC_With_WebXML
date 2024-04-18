package com.prowings.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prowings.DAO.StudentDao;
import com.prowings.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentDao studentDao;

	// @RequestMapping(value = "/save",method = RequestMethod.POST)
	@PostMapping("/save")
	public String addStudent(HttpServletRequest request) {
		System.out.println("inside addStudent method");
		String name = request.getParameter("name");
		String addr = request.getParameter("address");
		Student student = new Student(name, addr);

		System.out.println(student);
		// call to DAO logic to save incoming student
		studentDao.saveStudent(student);

		return "savedSuccess";

	}

	@GetMapping("/list")
	public String getStudentsList(Model model) {
		System.out.println("inside getStudentList() method of StudentController!!");
		List<Student> stdList = studentDao.getStudentsList();
		System.out.println(stdList);

		model.addAttribute("students", stdList);

		return "listStudent";
	}

}
