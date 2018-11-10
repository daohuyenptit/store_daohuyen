package com.example.daohuyen.common.historyOrder.controller;

import com.example.daohuyen.common.bill.models.data.Bill;
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
import java.util.List;
import org.springframework.data.domain.Pageable;

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
    public Response getAllBills(@PathVariable("id") String customerID,
                                @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
                                @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Bill.CREATED_DATE)
                                    @RequestParam(value = "sortBy", defaultValue = Bill.CREATED_DATE) String sortBy,
                                @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
                                    @RequestParam(value = "sortType", defaultValue = "desc") String sortType) {
        Response response;
        try {
            Customer customer = customerRespository.findOne(customerID);
            if (customer == null) {
                return new NotFoundResponse("Customer not Exist");
            }
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);

            Page<BillView> billViews = historyRepository.getAllBills(customerID,pageable);
            response = new OkResponse(billViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }


}
