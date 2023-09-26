package com.jdbc.controller;


import java.util.List;

import javax.management.DescriptorKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.entity.Student;
import com.jdbc.repo.Studentrepo;

@RestController
public class StudentController {
     
	@Autowired
	private Studentrepo studentrepo;
	
	@PostMapping("/insert/{id}/{name}/{city}")
	public void insertStudent(@PathVariable("id") int id,
			@PathVariable("name") String name,@PathVariable("city") String city,Student student) {
		studentrepo.insertStudent(student);
		System.out.println(student);
	}
	
	@GetMapping("/studentlist")
	public List<Student> getallStudent(){
		List<Student> list = studentrepo.getALLStudent();
		return list;
	}
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") int id){
		Student student = this.studentrepo.getStudent(id);
		System.out.println(student);
		if(student==null) {
			return student;
		}
		else {
		return student;	}
		}
    @PutMapping("/update/{id}/{name}/{city}")
    public Student updateStudent(@PathVariable("id") int id,@PathVariable("name") 
                              String name,@PathVariable("city") String city ,Student student){
    	  if(studentrepo.updateStudent(student)!=0) {
    	      return student;
    	  }
    	  else {
    		  return student;
    	  }
    }
    
    
    @DeleteMapping("/delete/{id}")
     public void deleteStudent(@PathVariable("id") int id) {
    	 studentrepo.deleteStudent(id);
    	 System.out.println("deleted successfully");
    }
}
