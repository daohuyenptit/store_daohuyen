package com.example.daohuyen.common.product.controller;

import com.example.daohuyen.common.employee.controller.FirebaseMessagingService;
import com.example.daohuyen.common.product.dao.*;
import com.example.daohuyen.common.product.models.body.*;
import com.example.daohuyen.common.product.models.data.*;
import com.example.daohuyen.common.customer.dao.CustomerRespository;
import com.example.daohuyen.common.customer.models.data.Customer;
import com.example.daohuyen.common.product.models.view.*;
import com.example.daohuyen.constants.Constant;
import com.example.daohuyen.constants.ResponesMessage;
import com.example.daohuyen.constants.Utils;
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

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController  {
    @Autowired
    FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private AprioriRespository aprioriRespository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    private CustomerRespository customerRespository;
    @Autowired
    private BillRespository billRespository;
    @Autowired
    private AprioRes aprioRes;
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

    /**********************All Product********************/
    @ApiOperation(value = "sản phẩm tồn kho")
    @GetMapping("/products/inventory/{date}")
    public Response getAllProductsInventory(@PathVariable("date") String date,
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

            Page<ProductViewModel> productViewModels = productRespository.getAllProductInventory( pageable,Utils.convertDate(date));
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    /*Get 20 product which are the newest*/
    /**********************All Product********************/
    @ApiOperation(value = "Lấy 20 sản phẩm mới nhất")
    @GetMapping("/newest")
    public Response getAllNewestProducts(

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
            Page<ProductViewModel> productViewModels = productRespository.get30ProductNew(pageable);
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
    @ApiOperation(value = "lấy sản phẩm theo categoryID")
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
    /**********************Category********************/
    @ApiOperation(value = "Lấy loại sản phẩm")
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
    @ApiOperation(value = "Lấy Category theo productGroupID")
    @GetMapping("/category/{id}")
    public Response getCategories( @PathVariable("id") String  productGroupID){
        Response response;
        try {

            List<CategoryViewModel> categories = categoryRespository.getCategoryByGroupID(productGroupID);
            return new OkResponse(categories);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Lấy chi tiết sản phẩm")
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

    @ApiOperation(value = "Tìm kiếm sản phẩm")
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
            if(productViewModels.getContent().size()==0){
             productViewModels=productRespository.getAllProductBySearchNameCategory(pageable,"%"+keyword+"%");
            }
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
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
    @GetMapping("/getbillbyID/{id}")
    Response getBillID(@PathVariable("id") String billID){

        BillViewItem billView=billRespository.getBillView(billID);
        if(billView==null){
            return new NotFoundResponse("Không tìm thấy đơn hàng");
        }

        return new OkResponse(billView);
    }
    @ApiOperation(value = "Lay đơn hàng theo id" , response = Iterable.class)
    @GetMapping("/getbill/{id}")
    Response getBill(@PathVariable("id") String billID){

        Set<LotProduct> lotproductViews=billRespository.getBillViewItem(billID);
        if(lotproductViews==null){
            return new NotFoundResponse("ko tiem thay id");
        }
        List<LotproductView> list=new ArrayList<>();
        for (LotProduct lotProduct: lotproductViews){
            list.add(new LotproductView(lotProduct));
        }

        return new OkResponse(list);
    }
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
    @ApiOperation(value = "Tạo danh sách đang chờ phê duyệt của khách hàng" , response = Iterable.class)
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

    @ApiOperation(value = "Tạo danh sách đang giao cho khách hàng" , response = Iterable.class)
    @GetMapping("/getHistoriesSending/{id}")
    public Response getAllSending(@PathVariable("id") String customerID,
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

            Page<BillView> billViews = historyRepository.getAllBillsSending(customerID,pageable);
            response = new OkResponse(billViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Tạo danh sách đã giao cho khách hàng" , response = Iterable.class)
    @GetMapping("/getHistoriesSent/{id}")
    public Response getAllSent(@PathVariable("id") String customerID,
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

            Page<BillView> billViews = historyRepository.getAllBillsSent(customerID,pageable);
            response = new OkResponse(billViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Tạo danh sách đang giao cho khách hàng" , response = Iterable.class)
    @GetMapping("/getHistoriesCancel/{id}")
    public Response getAllCancel(@PathVariable("id") String customerID,
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

            Page<BillView> billViews = historyRepository.getAllBillsCancel(customerID,pageable);
            response = new OkResponse(billViews);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
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
    @ApiOperation(value = "tất cả hóa đơn để convert thanh notification ")
    @GetMapping("/getAllSending/{id}")
    public Response getSending(@PathVariable("id") String customerID) {
        Response response;
        Customer customer = customerRespository.findOne(customerID);
        if (customer == null) {
            return new NotFoundResponse("Customer not Exist");
        }
        try {
            List<BillView> bills = historyRepository.getAllSending(customerID);
            if (bills.size() == 0) {
                return new NotFoundResponse("khong  tim thay bill đang giao");

            }
            response = new OkResponse(bills);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "tất cả hóa đơn đang giao ")
    @GetMapping("/getAllBillAdminSending")
    public Response getAllBillAdminSending() {
        Response response;
        try {
            List<BillView> bills = historyRepository.getAllBillsPermit(1);
            if (bills.size() == 0) {
                return new NotFoundResponse("khong  tim thay bill đang giao");

            }
            response = new OkResponse(bills);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "tất cả hóa đơn đã giao ")
    @GetMapping("/getAllBillAdminSent")
    public Response getAllBillAdminSent() {
        Response response;
        try {
            List<BillView> bills = historyRepository.getAllBillsPermit(2);
            if (bills.size() == 0) {
                return new NotFoundResponse("khong  tim thay bill đã giao");

            }
            response = new OkResponse(bills);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }

    /**********************SP bán chạy********************/
    @ApiOperation(value = "tất cả sản phẩm bán chạy -số sp>5 ")
    @GetMapping("/getAllBestSelllerProduct")
    public Response getAllProduct() {
        Response response;
        try {
            Set<ItemBody> list = productRespository.getAllBestSellerProduct();
            if (list.size() == 0) {
                return new NotFoundResponse("khong  tim thay sản phẩm nào");

            }
            int acount=billRespository.getCountBill();
            int countProduct=billRespository.getCountProduct();
            int total=billRespository.getTotal();
            ItemView itemView=new ItemView(list,acount,countProduct,total);
            response = new OkResponse(itemView);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    /**********************SP bán chạy********************/
    @ApiOperation(value = "Sản phẩm bán chạy trong tháng -số sp>5")
    @GetMapping("/getMonthBestSelllerProduct/{sd}")
    public Response getMonthProduct(@PathVariable("sd") String sd) {
        //truyền lên tháng và năm, yyyy-MM
        String[] s=sd.split("-");
        int month=Integer.parseInt(s[1]);
        int ed=month+1;
        String time=s[0]+"-"+ed+"-01";
        Response response;
        try {
            Set<ItemBody> list = productRespository.getAllBestSellerMonthProduct(Utils.convertDate(sd+"-01"),Utils.convertDate(time));
            if (list.size() == 0) {
                return new NotFoundResponse("khong  tim thay sản phẩm nào");

            }
            int acount=billRespository.getCountMonthBill(Utils.convertDate(sd+"-01"),Utils.convertDate(time));
            int countProduct=billRespository.getCountMonthProduct(Utils.convertDate(sd+"-01"),Utils.convertDate(time));
            int total=billRespository.getMonthTotal(Utils.convertDate(sd+"-01"),Utils.convertDate(time));
            ItemView itemView=new ItemView(list,acount,countProduct,total);
            response = new OkResponse(itemView);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    /**********************SP bán chạy********************/
    @ApiOperation(value = "Sản phẩm bán chạy hàng đầu")
    @GetMapping("/getBestSelllerProduct/{sd}")
    public Response getBestMProduct(@PathVariable("sd") String sd) {
        //truyền lên tháng và năm, yyyy-MM
        String[] s=sd.split("-");
        int month=Integer.parseInt(s[1]);
        int ed=month+1;
        String time=s[0]+"-"+ed+"-01";
        Response response;
        try {
            List<Product> productViewModels = productRespository.getBestSellerProduct(Utils.convertDate(sd+"-01"),Utils.convertDate(time));
            response = new OkResponse(productViewModels);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    /**********************SP bán chạy********************/
    @ApiOperation(value = "Sản phẩm bán chạy trong ngày (số sp>2)")
    @GetMapping("/getDayBestSelllerProduct/{sd}")
    public Response getDayProduct(@PathVariable("sd") String sd) {
        Response response;
        try {
            Set<ItemBody> list = productRespository.getAllBestSellerDayProduct(Utils.convertDate2(sd+" "+"00:00:00"),
                    Utils.convertDate2(sd+" "+"23:59:59")  );
            if (list.size() == 0) {
                return new NotFoundResponse("khong  tim thay sản phẩm nào");

            }
            int acount=billRespository.getCountDayBill(Utils.convertDate2(sd+" "+"00:00:00"),
                    Utils.convertDate2(sd+" "+"23:59:59") );
            int countProduct=billRespository.getCountDayProduct(Utils.convertDate2(sd+" "+"00:00:00"),
                    Utils.convertDate2(sd+" "+"23:59:59") );
            int total=billRespository.getDayTotal(Utils.convertDate2(sd+" "+"00:00:00"),
                    Utils.convertDate2(sd+" "+"23:59:59") );
            ItemView itemView=new ItemView(list,acount,countProduct,total);
            response = new OkResponse(itemView);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }




    @ApiOperation(value = "Chuyển trạng thái bill thành đang giao", response = Iterable.class)
    @PutMapping("/editbill")
    Response updateBill(@RequestBody List<Notification> list ) {
        Response response;
        try {
            Set<String> listID=new HashSet<>();

            for (Notification notification : list) {
                listID.add(notification.getBillID());
            }
            historyRepository.updateBill(1,listID);
            for (int i = 0; i < list.size(); i++) {
                firebaseMessagingService.sendNotification(FirebaseMessagingService
                        .createOrderSuccessMessage(list.get(i).getCustomerID()
                                , "Đơn đặt hàng"+list.get(i).getBillID()+" của bạn đang được giao",
                                list.get(i).getBillID()
                                , new Date()));
            }
            response = new OkResponse();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
    @ApiOperation(value = "Chuyển đơn hàng thành trạng thái đã giao", response = Iterable.class)
    @PutMapping("/editbillSending")
    Response updateBillSending(@RequestBody Set<String> listID) {
        Response response;
        try {
//            for (String id : listID) {
//                historyRepository.updateBill(id);
//            }
            historyRepository.updateBill(2,listID);
            response = new OkResponse();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
    @ApiOperation(value = "Hủy bill", response = Iterable.class)
    @PutMapping("/cancelbill")
    Response deleteBill(@RequestBody String id) {
        Response response;
        try {
            historyRepository.cancelBill(id);
            response = new OkResponse();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
    @ApiOperation(value = "Thêm sản phẩm" , response = Iterable.class)
    @PostMapping("/addproduct/{id}")
    Response addProduct(@PathVariable("id") String categoryID,@RequestBody ProductBody productBody) {
        Response response;
        try {
            Product product = new Product();
            Category category = categoryRespository.findOne(categoryID);
            product.setCategory(category);
            product.setDescription(productBody.getDescription());
            product.setLogoUrl(productBody.getLogoUrl());
            product.setName(productBody.getName());
            product.setPrice(productBody.getPrice());
            productRespository.save(product);
            return new OkResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Sửa sản phẩm", response = Iterable.class)
    @PutMapping("/editproduct/{id}/{idcate}")
    Response editProduct(@PathVariable("idcate") String cateID,@PathVariable("id") String productID, @RequestBody ProductBody productBody) {
        Response response;
        try {
            Product product = productRespository.findOne(productID);
            if (product == null) {
                return new NotFoundResponse("Không tồn tại product");
            }
            Category category = categoryRespository.findOne(cateID);
            product.setPrice(productBody.getPrice());
            product.setName(productBody.getName());
            product.setLogoUrl(productBody.getLogoUrl());
            product.setDescription(productBody.getDescription());
            product.setCategory(category);
            productRespository.save(product);
            ProductViewModel productViewModel=new ProductViewModel(product);
            response = new OkResponse(productViewModel);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;
    }
    @ApiOperation(value = "Xóa sản phẩm", response = Iterable.class)
    @PutMapping("/deleteproduct/{id}")
    Response deleteProduct(@PathVariable("id") String productID) {
        Response response;
        try {
            productRespository.delete(productID);
            response = new OkResponse();

        } catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
    @ApiOperation(value = "Gợi ý sản phẩm theo categoryID")
    @GetMapping("/productsuggest/{inputcate}")
    public Response getApioriProduct(@PathVariable("inputcate") String inputcate,
                                     @ApiParam(name = "pageIndex", value = "Index trang, mặc định là 0")
                                     @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
                                     @ApiParam(name = "pageSize", value = "Kích thước trang, mặc đinh và tối đa là " + Constant.MAX_PAGE_SIZE)
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @ApiParam(name = "sortBy", value = "Trường cần sort, mặc định là " + Product.CREATED_DATE)
                                     @RequestParam(value = "sortBy", defaultValue = Product.CREATED_DATE) String sortBy,
                                     @ApiParam(name = "sortType", value = "Nhận (asc | desc), mặc định là desc")
                                     @RequestParam(value = "sortType", defaultValue = "desc") String sortType) {
        Response response;
        try {

            List<CategoryCollectionView> categoryCollectionViews=new ArrayList<>();

            List<Integer> idInputs=aprioriRespository.getInput(inputcate);
            for (int i = 0; i <idInputs.size() ; i++) {
                List<Associative> list=aprioriRespository.findByInput_Id(idInputs.get(i));
                CategoryCollectionView categoryCollectionView=new CategoryCollectionView();
                Set<Category> associatives=new HashSet<>();
                for (int j = 0; j <list.size() ; j++) {
                    associatives.add(list.get(j).getCategory());
                    categoryCollectionView.setCategories(associatives);
                }
                categoryCollectionViews.add(categoryCollectionView);


            }
            // cai đoạn này hơi thừa nhưng thôi
            Page<ProductViewModel> productViewModels=null;
            List<Category> listCategory=new ArrayList<>();
            boolean flag=true;
            for (int i = 0; i <idInputs.size() ; i++) {
                List<Associative> list=aprioriRespository.findByInput_Id(idInputs.get(i));
                for (int j = 0; j <list.size() ; j++) {
                    if(listCategory.size()>0){
                        for (int k = 0; k <listCategory.size() ; k++) {
                            if(listCategory.get(k).getId().equals(list.get(j).getCategory().getId())){
                                flag=false;
                                break;
                            }

                        }

                    }
                    if(flag==true){
                        listCategory.add(list.get(j).getCategory());
                    }

                }

            }
            if(listCategory.size()==0){
                return new NotFoundResponse("ko tiem thay id");
            }else {
                for (int i = 0; i < listCategory.size(); i++) {
                    Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
                    productViewModels = productRespository.getAllProductListByCategory(pageable, listCategory.get(i).getId());


                }

                response = new OkResponse(productViewModels);
            }
        }catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
    @ApiOperation(value = "Gợi ý sản phẩm theo productID")
    @GetMapping("/productApriori/{input}")
    public Response getApioriProductID(@PathVariable("input") String input) {
        Response response;
        try {
            List<ProductViewModel> associatives=new ArrayList<>();
            List<Integer> idInputs=aprioriRespository.getInput(input);
            for (int i = 0; i <idInputs.size() ; i++) {
                List<Apriori> list=aprioRes.findByInput_Id(idInputs.get(i));

                for (int j = 0; j <list.size() ; j++) {
                    associatives.add(new ProductViewModel(list.get(j).getProduct()));
                }
            }
            response = new OkResponse(associatives);

        }catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }

}
