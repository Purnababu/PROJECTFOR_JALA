package com.example.ProjectJala.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectJala.entity.Employee;
import com.example.ProjectJala.repo.EmployeRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeRepo employeeRepository;

	@PostMapping("/register")
	public Employee registerEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}


    @PostMapping("/login")
    public Employee loginEmployee(@RequestParam String email, @RequestParam String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }

	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeRepository.findById(id);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		return employeeRepository.findById(id).map(employee -> {
			employee.setFirstName(updatedEmployee.getFirstName());
			employee.setLastName(updatedEmployee.getLastName());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
			employee.setPassword(updatedEmployee.getPassword());
			return employeeRepository.save(employee);
		}).orElse(null); 
	}

	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(id);

	    if (optionalEmployee.isPresent()) {
	        employeeRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


}
