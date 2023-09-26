package com.jdbc.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbc.entity.Student;

@Repository
public class Studentrepo {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertStudent(Student student) {
		jdbcTemplate.update("insert into student(id,name,city) values(?,?,?)",
				student.getId(),student.getName(),student.getCity());
	}
	
	public List<Student> getALLStudent() {
		return jdbcTemplate.query("select * from  student", new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public Student getStudent(int id) {
		 Student s1=null;
		 try {
			  s1 = jdbcTemplate.queryForObject("select * from student where id=?",
						new Object[]{id},new BeanPropertyRowMapper<Student>(Student.class));
		} catch (Exception e) {
            e.printStackTrace();  
		}
		return s1;		
	}
	
	public int updateStudent(Student student) {
         return jdbcTemplate.update("update student set name=?,city=? where id=?",student.getName(),student.getCity(),student.getId());
	}
	
	public void deleteStudent(int id) {
		jdbcTemplate.update("delete from student where id=?",id);
	}
}
