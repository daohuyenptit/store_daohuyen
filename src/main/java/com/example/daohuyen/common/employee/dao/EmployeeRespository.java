package com.example.daohuyen.common.employee.dao;

import com.example.daohuyen.common.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee,String> {

}
