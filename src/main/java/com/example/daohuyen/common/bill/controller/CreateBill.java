package com.example.daohuyen.common.bill.controller;

import com.example.daohuyen.common.bill.dao.BillRespository;
import com.example.daohuyen.common.bill.models.body.BillBody;
import com.example.daohuyen.common.bill.models.body.LotProductBody;
import com.example.daohuyen.common.bill.models.data.Bill;
import com.example.daohuyen.common.bill.models.data.LotProduct;
import com.example.daohuyen.common.bill.models.view.BillView;
import com.example.daohuyen.common.bill.models.view.BillViewItem;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.product.dao.ProductRespository;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.constants.Constant;
import com.example.daohuyen.constants.ResponesMessage;
import com.example.daohuyen.response_model.NotFoundResponse;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import com.example.daohuyen.response_model.ServerErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/bill")
@Api(value = "candidate-api", description = "Nhóm API Bill")
@CrossOrigin(origins = "*")
public class CreateBill {
    @Autowired
    private BillRespository billRespository;
    @Autowired
    private CustomerRespository customerRespository;
    @Autowired
    private ProductRespository productRespository;
    @ApiOperation(value = "api tạo hóa đơn", response = Iterable.class)
    @PostMapping("/createBills")
    public Response insertBill(@Valid @RequestBody BillBody billBody) {
        Response response;
        try {
            Customer customer = customerRespository.getCustomerByID(billBody.getCustomerID());
            if (customer == null) {
                return new NotFoundResponse(ResponesMessage.CUSTOMER_NOT_EXITS);
            }
            Bill bill = new Bill(customer);
            Set<LotProduct> lotProducts = new HashSet<>();
            int total = 0;
            for (LotProductBody lotProductBody : billBody.getLotProductBodies()) {
                Product product = productRespository.getProduct(lotProductBody.getIdProduct());
                // kiêểm tra product null or ko ?
                if(product==null){
                    return new NotFoundResponse(ResponesMessage.PRODUCT_NOT_EXITS);
                }

                LotProduct lotProduct = new LotProduct(product, bill, lotProductBody.getNumber());
                lotProducts.add(lotProduct);
                total += lotProductBody.getPrice() * lotProductBody.getNumber();

            }
            total+=billBody.getPrice_transport();
            if (lotProducts.size()>0){
                bill.setLotProducts(lotProducts);
                bill.setTotal(total);
                bill.setPermit(0);
                bill.setReceiver(billBody.getReceiver());
                bill.setPhone(billBody.getPhone());
                bill.setAddress_receive(billBody.getAddress_receive());
                bill.setTransport(billBody.getTransport());
                bill.setPrice_transport(billBody.getPrice_transport());
                bill.setPay(billBody.getPay());
                billRespository.save(bill);
            }
            response=new OkResponse();

        }catch (Exception ex){
            ex.printStackTrace();
            response=new ServerErrorResponse();

        }
        return response;
    }
    @ApiOperation(value = "Lay đơn hàng theo id" , response = Iterable.class)
    @GetMapping("/getbill/{id}")
    Response getBill(@PathVariable("id") String billID){

        BillViewItem billView=billRespository.getBillViewItem(billID);
        if(billView==null){
            return new NotFoundResponse("ko tiem thay id");
        }
        return new OkResponse(billView);
    }
//
//    @ApiOperation(value = "api get id bill", response = Iterable.class)
//    @GetMapping("/getBillIDs")
//    public Response getBillID() {
//        Response response;
//        try {
//
//
//
//            response = new OkResponse(billRespository.getlistBillId());
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//            response=new ServerErrorResponse();
//        }
//        return response;
//    }

}
