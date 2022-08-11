package com.sms.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sms.model.Student;
import com.sms.repository.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Transactional(readOnly = true)
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}
	
	@Transactional
	public Student save(Student student){
		return studentRepo.save(student);
	}
	
	@Transactional(readOnly = true)
	public boolean existById(Long id){
		return studentRepo.existsById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Student> getById(Long id){
		return studentRepo.findById(id);
	}
	
	public void delete(int sid) {
		 studentRepo.deleteById((long) sid);
	}
}
