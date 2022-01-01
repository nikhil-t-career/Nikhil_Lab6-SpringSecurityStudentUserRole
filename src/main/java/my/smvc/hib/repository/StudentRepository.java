package my.smvc.hib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import my.smvc.hib.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
//	List<Student> findByUserName(String user);
}
