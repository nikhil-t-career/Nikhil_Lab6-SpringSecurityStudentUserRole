package my.smvc.hib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.smvc.hib.model.Student;
import my.smvc.hib.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
	@Transactional
    public List < Student > getStudents() {
        return studentRepo.findAll();
    }

    @Override
	@Transactional
    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    @Override
	@Transactional
    public Student getStudent(long id) {
    	
    	Optional<Student> studentOptional = studentRepo.findById(id);
    	Student student = studentOptional.get();
        return student;
    }

    @Override
	@Transactional
    public void deleteStudent(long id) {
    	
    	Optional<Student> studentOptional = studentRepo.findById(id);
    	
    	if(studentOptional.isPresent())
    	{
    		Student student = studentOptional.get();
    		studentRepo.delete(student);
    	}
    	
    }
}
