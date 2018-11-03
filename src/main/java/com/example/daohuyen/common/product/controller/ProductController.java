package com.example.daohuyen.common.product.controller;

import com.example.daohuyen.common.product.dao.CategoryRespository;
import com.example.daohuyen.common.product.dao.ProductRespository;
import com.example.daohuyen.common.product.models.body.ProductBody;
import com.example.daohuyen.common.product.models.data.Category;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.common.product.models.view.CategoryViewModel;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
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
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController  {
    @Autowired
    ProductRespository productRespository;
    @Autowired
    CategoryRespository categoryRespository;
    @ApiOperation(value = "Them san pham" , response = Iterable.class)
    @PostMapping("/insertProduct/{id}")
    Response insertProduct(@PathVariable("id") String catetoryID, @RequestBody ProductBody productBody){
        Category category=categoryRespository.getOne(catetoryID);
        if(category==null){
            return new NotFoundResponse();
        }
        Product product=new Product();
        product.setName(productBody.getName());
        product.setPrice(productBody.getPrice());
        product.setDescription(productBody.getDescription());
        product.setLogoUrl(productBody.getLogoUrl());
        product.setCategory(category);
        productRespository.save(product);
        product.setCreatedDate(new Date());


        return new OkResponse();
    }
    @ApiOperation(value = "Lay product theo id" , response = Iterable.class)
    @GetMapping("/getproduct/{id}")
    Response getProduct(@PathVariable("id") String productID){

        Product product= productRespository.findOne(productID);
        if(product==null){
            return new NotFoundResponse("ko tiem thay id");
        }
        return new OkResponse(product);
    }
    /**********************All Product********************/
    @ApiOperation(value = "get Product")
    @GetMapping("/products")
    public Response getAllProducts(

            @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
            @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
            @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Product.CREATED_DATE)
            @RequestParam(value = "sortBy", defaultValue = Product.CREATED_DATE) String sortBy,
            @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
            @RequestParam(value = "sortType", defaultValue = "desc") String sortType
    ) {
        Response response;
        try {
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<ProductViewModel> productViewModels = productRespository.getAllProductViewModels(pageable);
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }


    @ApiOperation(value = "get Product not page")
    @GetMapping("/productsnotpage")
    public Response getAllProduct(

            @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
            @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
            @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Product.CREATED_DATE)
            @RequestParam(value = "sortBy", defaultValue = Product.CREATED_DATE) String sortBy,
            @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
            @RequestParam(value = "sortType", defaultValue = "desc") String sortType
    ) {
        Response response;
        try {
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<ProductViewModel> productViewModels = productRespository.getAllProductViewModels(pageable);
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    /**********************All Product IDCategory********************/
    @ApiOperation(value = "get Product by IdCategory")
    @GetMapping("/getProducts/{id}")
    public Response getAllProductsByIdCategory(
            @PathVariable("id") String catetoryID,
            @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
            @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
            @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Product.CREATED_DATE)
            @RequestParam(value = "sortBy", defaultValue = Product.CREATED_DATE) String sortBy,
            @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
            @RequestParam(value = "sortType", defaultValue = "desc") String sortType
    ) {
        Response response;
        try {
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<ProductViewModel> productViewModels = productRespository.getAllProductByCategory(pageable,catetoryID);
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }


    /**********************All Product IDCategory********************/
    @ApiOperation(value = "get Product by IdCategory khong dung page")
    @GetMapping("/getProductsnotpage/{id}")
    public Response getProductsByIdCategory(
            @PathVariable("id") String catetoryID) {
        Response response;
        try {
            List<ProductViewModel> productViewModels = productRespository.getAllProductListByCategory(catetoryID);
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    /**********************Category********************/
    @ApiOperation(value = "get Category")
    @GetMapping("/category")
    public Response getAllCategories(){
        Response response;
        try {

            List<CategoryViewModel> categories = categoryRespository.getAllCategoryViewModels();
            return new OkResponse(categories);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    @GetMapping("/productdetail/{id}")
    public Response getDetailProduct(@PathVariable("id") String productID) {
        Response response;
        try{
            ProductViewModel productViewModel = productRespository.getProductViewModel(productID);
            response = new OkResponse(productViewModel);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
// get all product when search

    @ApiOperation(value = "get Product Search")
    @GetMapping("/getProductSearch/{key}")
    public Response getAllProductsBySearch(
            @PathVariable("key") String keyword,
            @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
            @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
            @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Product.CREATED_DATE)
            @RequestParam(value = "sortBy", defaultValue = Product.CREATED_DATE) String sortBy,
            @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
            @RequestParam(value = "sortType", defaultValue = "desc") String sortType
    ) {
        Response response;
        try {
            Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
            Page<ProductViewModel> productViewModels = productRespository.getAllProductBySearch(pageable,"%"+keyword+"%");
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

}
