package com.example.daohuyen.admin.controller;

import com.example.daohuyen.admin.dao.AdminRespository;
import com.example.daohuyen.admin.models.BillListID;
import com.example.daohuyen.auth.models.User;
import com.example.daohuyen.auth.models.body.UserBody;
import com.example.daohuyen.common.bill.dao.BillRespository;
import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.view.BillView;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.historyOrder.dao.HistoryRepository;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;
import com.example.daohuyen.response_model.NotFoundResponse;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import com.example.daohuyen.response_model.ServerErrorResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    AdminRespository adminRespository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    CustomerRespository customerRespository;


    @ApiOperation(value = "Admin Đăng nhập", response = Iterable.class)
    @PostMapping("/login")
    Response loginAdmin(@RequestBody UserBody userBody) {
        User user = adminRespository.findAdminByUsernameAndPasswordAndPost(userBody.getUsername(), userBody.getPassword(), 0);
        if (user == null) {
            return new NotFoundResponse("Khong co tai khoan");
        }
        return new OkResponse();
    }

    /************* api đăng ký nhân viên ******************/
    @ApiOperation(value = "Đăng kí", response = Iterable.class)
    @PostMapping("/register")
    Response registerEmployee(@RequestBody UserBody userBody) {
        User user = new User(userBody.getUsername(), userBody.getPassword(), 0);
        adminRespository.save(user);
        return new OkResponse();
    }


    /**********************All bills have permit=0********************/
    @ApiOperation(value = "tất cả hóa đơn chưa được phê duyệt")
    @GetMapping("/getAllBillnotPermit")
    public Response getAllBillnotPermit() {
        Response response;
        try {
            List<BillView> bills = historyRepository.getAllBillsPermit(0);
            if (bills.size() == 0) {
                return new NotFoundResponse("khong  tim thay bill de duyet");

            }
            response = new OkResponse(bills);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @ApiOperation(value = "Cap nhat thong tin bill", response = Iterable.class)
    @PutMapping("/editbill")
    Response updateBill(@RequestBody Set<String> listID) {
        Response response;
        try {
            for (String id : listID) {
                historyRepository.updateBill(id);
            }
            response = new OkResponse();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
}
