package com.galvanize.springwebdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	List<Employee> employees = new ArrayList<>();

	public EmployeeController() {
		employees.add(new Employee(1, "First", "Employee"));
		employees.add(new Employee(2, "Second", "Employee"));
		employees.add(new Employee(3, "Third", "Employee"));
		employees.add(new Employee(4, "Fourth", "Employee"));
		employees.add(new Employee(5, "Fifth", "Employee"));
	}

	@GetMapping("/{id}")
	public Employee findEmployeeById(@PathVariable int id) {
		Employee employee = null;
		for (Employee emp : employees) {
			if (emp.getId() == id) {
				employee = emp;
			}
		}

		return employee;
	}

	@GetMapping("/search")
	public List<Employee> findEmployeesByFirstAndLast(@RequestParam String firstName,
													  @RequestParam String lastName){
		List<Employee> searchResults = new ArrayList<>();
		for(Employee emp : employees){
			if(emp.getFirstName().equals(firstName)
					&& emp.getLastName().equals(lastName)){
				searchResults.add(emp);
			}
		}
		return searchResults;
	}
//  3 different ways to change the status to "CREATED" and return the request body

//	@PostMapping("/add")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addEmployee(@RequestBody Employee newEmployee) {
//		employees.add(newEmployee);
//	}

//	@PostMapping("/add")
//	public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
//		employees.add(newEmployee);
//		return new ResponseEntity<Employee>(newEmployee, HttpStatus.CREATED);  <-- response entity is instatiated
//	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
		employees.add(newEmployee);
		return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
	}

}
