package com.sms.controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.service.StudentService;
import com.sms.model.Student;

@RestController
@RequestMapping("api/student")
@CrossOrigin("*")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/getall")
	public List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@PostMapping("/addstudent")
	public Student addStudent(@RequestBody Student student) {
		return studentService.save(student);
	}

	@GetMapping("/{id}")
	public Student getById(@PathVariable Long id) {
		return studentService.getById(id).orElseThrow(() -> new EntityNotFoundException("Requested Student not found"));
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Long id){
		if(studentService.existById(id)) {
			Student st =  studentService.getById(id).orElseThrow(()->new EntityNotFoundException("Requested Student not found"));
			st.setsName(student.getsName());
			st.setsEmail(student.getsEmail());
			st.setsCourse(student.getsCourse());
			st.setsGender(student.getsGender());
			st.setsAddress(student.getsAddress());
			st.setIsCheck(student.getIsCheck());
			studentService.save(st);
			return ResponseEntity.ok().body(st);
		}else {
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " student not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
		
	}
	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<?> deleteStudent(@RequestBody Student student, @PathVariable Long id){
//		if(studentService.existById(id)) {
//			studentService.delete(id);
//			HashMap<String, String>message= new HashMap<>();
//			message.put("message", id + " student removed");
//			return ResponseEntity.status(HttpStatus.OK).body(message);
//		}else {
//			HashMap<String, String>message= new HashMap<>();
//			message.put("message", id + " student not found or matched");
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//		}
//		
//	} 
////	
	//delete student
		@DeleteMapping("/delete/{sid}")
		public void deleteStudent(@PathVariable("sid") int sid) {
			studentService.delete(sid);
		}

}
