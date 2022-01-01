package my.smvc.hib.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import my.smvc.hib.model.Student;
import my.smvc.hib.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/list-students")
	public String showStudents(ModelMap model) {
		model.put("students", studentService.getStudents());
		return "list-students";
	}

	@GetMapping(value = "/delete-student")
	public String deleteStudent(@RequestParam long id) {
		studentService.deleteStudent(id);
		return "redirect:/list-students";
	}

	@GetMapping(value = "/update-student")
	public String showUpdateStudent(@RequestParam long id, ModelMap model) {
		Student student = studentService.getStudent(id);
		model.put("student", student);
		return "student";
	}

	@GetMapping(value = "/add-student")
	public String showAddStudent(ModelMap model) {
		model.addAttribute("student", new Student());
		return "student";
	}

	@PostMapping(value = "/save-student")
	public String addStudent(ModelMap model, @Valid Student student, BindingResult result) {

		if (result.hasErrors()) {
			return "student";
		}

		studentService.saveStudent(student);
		return "redirect:/list-students";
	}
}
