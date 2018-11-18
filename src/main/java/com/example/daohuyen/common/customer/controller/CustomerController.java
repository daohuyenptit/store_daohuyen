package com.example.daohuyen.common.customer.controller;

import com.example.daohuyen.common.customer.dao.UserRespository;
import com.example.daohuyen.common.customer.models.data.User;
import com.example.daohuyen.common.customer.models.body.NewPassword;
import com.example.daohuyen.common.customer.models.body.UserBody;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.dao.GenderRespository;
import com.example.daohuyen.common.customer.models.body.CustomerBody;
import com.example.daohuyen.common.customer.models.body.CustomerBody1;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.customer.models.data.Gender;
import com.example.daohuyen.common.customer.models.view.CustomerView;
import com.example.daohuyen.response_model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    CustomerRespository customerRespository;
    @Autowired
    GenderRespository genderRespository;
    @ApiOperation(value = "Đăng nhập" , response = Iterable.class)
    @PostMapping("/customer/login")
    Response loginCustomer(@RequestBody UserBody userBody){
        User user= userRespository.findByUsernameAndPasswordAndPost(userBody.getUsername(),userBody.getPassword(),1);
        if(user==null){
            return new NotFoundResponse("Khong co tai khoan");
        }
        Customer customer=customerRespository.findByUser_Id(user.getId());
        CustomerView customerView=new CustomerView(customer);
        return new OkResponse(customerView);
    }
    @ApiOperation(value = "Đổi mật khẩu", response = Iterable.class)
    @PostMapping("/change/{id}/newPassword")
    public Response changePassword(@PathVariable("id") String customerID,
                                   @Valid @RequestBody NewPassword password) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            User u = customer.getUser();
            if (u.getPassword().matches(password.getOldPassword())) {
                u.setPassword(password.getNewPassword());
                userRespository.save(u);
                response = new OkResponse();
            } else {
                response = new Response(HttpStatus.CONFLICT, ResponseConstant.Vi.OLD_PASSWORD_MISMATCH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    /************* api đăng ký khách hàng ******************/
    @ApiOperation(value = "Đăng kí" , response = Iterable.class)
    @PostMapping("/register")
    Response registerCustomer(@RequestBody CustomerBody customerBody){
        User user=userRespository.findByUsername(customerBody.getUsername());
        if(user!=null){
            return new ResourceExistResponse("Tai khoan da ton tai!");

        }else{
        Customer customer= new Customer();
        customer.setFullName(customerBody.getFullname());
        customer.setAddress(customerBody.getAddress());
        customer.setPhone(customerBody.getPhone());
        Gender gender=genderRespository.findGenderByName(customerBody.getGender());
        customer.setGenderID(gender);
        User user1=new User(customerBody.getUsername(),customerBody.getPassword(),1);
        customer.setUser(user1);
        customerRespository.save(customer);
        return new OkResponse();
        }

    }

    @ApiOperation(value = "Lay customer theo userID" , response = Iterable.class)
    @GetMapping("/getcustomer/{id}")
    Response getCustomer(@PathVariable("id") String userID){

        Customer customer= customerRespository.findByUser_Id(userID);
        if(customer==null){
            return new NotFoundResponse("ko tiem thay id");
        }
        return new OkResponse(customer);
    }
    @ApiOperation(value = "update customer theo id" , response = Iterable.class)
    @PutMapping("/updatecustomer/{id}")
    Response updateCustomer(@PathVariable("id") String customerID,@RequestBody CustomerBody customerBody){

        Customer customer= customerRespository.getCustomerByID(customerID);
        if(customer==null){
            return new NotFoundResponse("ko tiem thay id");
        }
        customer.setFullName(customerBody.getFullname());
        customer.setAddress(customerBody.getAddress());
        customerRespository.save(customer);
        return new OkResponse(customer);
    }



    @ApiOperation(value = "Cap nhat thong tin khach hang", response = Iterable.class)
    @PutMapping("profiles/{id}")
    Response updateProfile(@PathVariable("id") String customerID, @RequestBody CustomerBody1 customerBody1) {
        Response response;
        try {
            Customer customer = customerRespository.getCustomerByID(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not exist!");
            }
            customer.setAddress(customerBody1.getAddress());
            customer.setAvatarUrl(customerBody1.getAvatarUrl());
            if (customerBody1.getBirthday() == -1) {
                customer.setBirthday(new Date());
            } else {
                customer.setBirthday(new Date(customerBody1.getBirthday()));
            }
            customer.setDescription(customerBody1.getDescription());
            customer.setEmail(customerBody1.getEmail());
            customer.setFullName(customerBody1.getFullName());
            customer.setIdentityCard(customerBody1.getIdentityCard());
            customer.setPhone(customerBody1.getPhone());
            Gender gender=genderRespository.getGenderID(customerBody1.getGender());
            customer.setGenderID(gender);
            customerRespository.save(customer);
            CustomerView customerView=new CustomerView(customer);
            response = new OkResponse(customerView);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

}
