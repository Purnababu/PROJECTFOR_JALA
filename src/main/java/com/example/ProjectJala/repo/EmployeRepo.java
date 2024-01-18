package com.example.ProjectJala.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectJala.entity.Employee;

@Repository
public interface EmployeRepo extends JpaRepository<Employee, Long> {
	
	 Employee findByEmailAndPassword(String email, String password);

	void deleteByEmail(String email);
	

}
