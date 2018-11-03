package com.example.daohuyen.auth.controller;

import com.example.daohuyen.auth.dao.UserRespository;
import com.example.daohuyen.auth.models.User;
import com.example.daohuyen.auth.models.body.UserBody;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.customer.models.view.CustomerView;
import com.example.daohuyen.common.product.dao.CategoryRespository;
import com.example.daohuyen.common.product.dao.ProductRespository;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.response_model.NotFoundResponse;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import com.example.daohuyen.response_model.ServerErrorResponse;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auths")
@CrossOrigin(origins = "*")
public class AuthController {
    //
    //    @Autowired
//    private BCryptPasswordEncoder bCryptPassw
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private CustomerRespository customerRespository;
    @Autowired
    private CategoryRespository categoryRespository;
    @Autowired
    private ProductRespository productRespository;

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
    @ApiOperation(value = "insert data jsoup", response = Iterable.class)
    @GetMapping("/jsoup/{id}")
    Response demo(@Param("path") String path, @PathVariable("id") String categoryID) {
        Response response;
        try {

            Document document = Jsoup.connect(path).get();
            Elements listSp= document.select("div.section").get(0).getElementsByTag("li").get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
            for(Element e:listSp){
                String url = e.getElementsByTag("a").attr("href");
                Document documentCon = Jsoup.connect(url).get();
                Element elementCon1 = documentCon.select("div.section").get(0);
                String name=     elementCon1.getElementsByTag("h1").text();
                String price=     elementCon1.select("div.summary").get(0).select("div").get(0).select("div").get(0).getElementsByTag("meta").get(0).attr("content");
                String src=     elementCon1.select("div").get(0).getElementsByTag("li").select("div.images").get(0).getElementsByTag("img").attr("src");
                String description=     elementCon1.select("div.summary").get(0).select("div").get(0).getElementsByTag("p").get(4).text()+"\n"+elementCon1.select("div.summary").get(0).select("div").get(0).getElementsByTag("p").get(5).text();;
                Product product = new Product();
                product.setName(name);
                product.setLogoUrl(src);
                product.setPrice(Integer.valueOf(price));
                product.setDescription(description);
                product.setCategory(categoryRespository.getOne(categoryID));
                productRespository.save(product);
            }

            response = new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

}