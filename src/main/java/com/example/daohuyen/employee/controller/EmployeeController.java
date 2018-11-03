package com.example.daohuyen.employee.controller;

import com.example.daohuyen.auth.models.User;
import com.example.daohuyen.common.customer.models.body.CustomerBody;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.employee.dao.EmployeeRespository;
import com.example.daohuyen.employee.models.Employee;
import com.example.daohuyen.employee.models.EmployeeBody;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeRespository employeeRespository;
    /************* api đăng ký nhân viên ******************/
    @ApiOperation(value = "Đăng kí" , response = Iterable.class)
    @PostMapping("/register")
    Response registerEmployee(@RequestBody EmployeeBody employeeBody){
        Employee employee=new Employee();
        employee.setFullName(employeeBody.getFullName());
        User user=new User(employeeBody.getUsername(),employeeBody.getPassword());
        employee.setUser(user);
        employeeRespository.save(employee);
        return new OkResponse();
    }
}
