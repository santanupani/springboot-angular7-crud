package za.co.santanu.springbootangular7crud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.santanu.springbootangular7crud.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
