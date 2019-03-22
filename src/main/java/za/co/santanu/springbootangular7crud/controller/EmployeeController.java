package za.co.santanu.springbootangular7crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.santanu.springbootangular7crud.model.Employee;
import za.co.santanu.springbootangular7crud.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
		
		//Service method call skipped
		employeeRepository.save(employee);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees(){
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getEmployee(@PathVariable("id") long id){
		System.out.println("Id value: "+id);
		Employee employee = employeeRepository.findById(id).orElse(null);
		return new ResponseEntity<Object>(employee, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
		ResponseEntity<Object> res = null;
		Employee employee2 = employeeRepository.findById(id).orElse(null);
		if(employee2 != null) {
			employee2.setFirstName(employee.getFirstName());
			employee2.setLastName(employee.getLastName());
			employee2.setEmailId(employee.getEmailId());
			
			employeeRepository.save(employee2);
			res = new ResponseEntity<>(employee2, HttpStatus.OK);
		}
		return res;
	}

}
