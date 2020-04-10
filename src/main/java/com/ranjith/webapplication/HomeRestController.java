package com.ranjith.webapplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ranjith.webapplication.dao.StudentDAO;
import com.ranjith.webapplication.model.Student;


@RestController
@RequestMapping("/")
public class HomeRestController {
	
	@Autowired
	private StudentDAO studentRepo;
	
	@GetMapping(path="students",produces= {"application/json"})//
	public List<Student>  getStudents()
	{
		return studentRepo.findByStudents();
	}
	
	@GetMapping(path="student/{id}",produces= {"application/json"})//
	public Student  getStudent(@PathVariable("id") int rollNumber)
	{
		return studentRepo.findById(rollNumber);
	}
	@PostMapping(path="student",consumes= {"application/json"})
	 @Convert(converter= JsonbHttpMessageConverter.class)
	public Student addStudent(@RequestBody Student student)
	{
		//studentRepo.save(student);
		Integer roll=student.getRollNumber();
		Integer age=student.getAge();
		String name=student.getName();
		String sex=student.getSex();
		Integer x=studentRepo.addStudent(roll, age, name, sex);
		
		JSONObject outputJsonObj = new JSONObject();
		outputJsonObj.put("addedStudent", student);
		
			return student;
		
		
		
	}

}
