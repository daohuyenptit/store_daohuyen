package com.example.daohuyen.common.historyOrder.controller;

import com.example.daohuyen.common.bill.models.view.BillView;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.historyOrder.dao.HistoryRepository;
import com.example.daohuyen.common.historyOrder.models.view.LotproductView;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.constants.Constant;
import com.example.daohuyen.response_model.NotFoundResponse;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import com.example.daohuyen.response_model.ServerErrorResponse;
import com.example.daohuyen.utils.PageAndSortRequestBuilder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class HistoryController {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    CustomerRespository customerRespository;
    // tạo lịch sử mua hàng theo trang
    @ApiOperation(value = "Tạo danh sách sản phẩm của khách hàng")
    @GetMapping("/getProductHistories/{id}")
    public Response getAllHistory(@PathVariable("id") String customerID) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }

            List<LotproductView> lotproductViews = historyRepository.getAllHistory1(customerID);

            response = new OkResponse(lotproductViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }


   // tạo lịch sử mua hàng 2
//    @ApiOperation(value = "Tạo lịch sử mua hàng của khách hàng")
//    @GetMapping("/getHistories/{id}")
//    public Response getAllHistory2(@PathVariable("id") String customerID) {
//        Response response;
//        try {
//            Customer customer = customerRespository.findOne(customerID);
//            if (customer == null) {
//                return new NotFoundResponse("Customer not Exist");
//            }
//
//            List<LotproductView> lotproductViews = historyRepository.getAllHistory1(customerID);
//            response = new OkResponse(lotproductViews);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response = new ServerErrorResponse();
//        }
//        return response;
//    }

    //tạo lịch sư 3
    //tạo lịch sử mua hàng 2
//    @ApiOperation(value = "Tạo lịch sử mua hàng của khách hàng")
//    @GetMapping("/getHistories")
//    public Response getAllHistory2() {
//        Response response;
//        try {
//
//
//            List<LotproductView> lotproductViews = historyRepository.getAllHistory2();
//            response = new OkResponse(lotproductViews);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response = new ServerErrorResponse();
//        }
//        return response;
//    }
    @ApiOperation(value = "Tạo lịch sử mua hàng của khách hàng" , response = Iterable.class)
    @GetMapping("/getHistories/{id}")
    public Response getAllBills(@PathVariable("id") String customerID) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }


            List<BillView> billViews = historyRepository.getAllBills(customerID);
            response = new OkResponse(billViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }


}
