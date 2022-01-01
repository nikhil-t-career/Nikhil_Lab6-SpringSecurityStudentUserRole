package my.smvc.hib.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import my.smvc.hib.model.Student;

public interface StudentService {

	List<Student> getStudents();

	void saveStudent(Student student);

	Student getStudent(long id);

	void deleteStudent(long id);

}