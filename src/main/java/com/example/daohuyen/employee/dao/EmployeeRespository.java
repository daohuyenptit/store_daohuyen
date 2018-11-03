package com.example.daohuyen.employee.dao;

import com.example.daohuyen.employee.models.Employee;
import com.example.daohuyen.employee.models.EmployeeBody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee,String> {

}
