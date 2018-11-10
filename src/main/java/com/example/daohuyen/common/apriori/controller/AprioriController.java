package com.example.daohuyen.common.apriori.controller;

import com.example.daohuyen.common.apriori.dao.AprioriRespository;
import com.example.daohuyen.common.apriori.models.Associative;
import com.example.daohuyen.common.apriori.models.Input;
import com.example.daohuyen.common.apriori.models.view.CategoryCollectionView;
import com.example.daohuyen.common.product.dao.ProductRespository;
import com.example.daohuyen.common.product.models.data.Category;
import com.example.daohuyen.common.product.models.data.Product;
import com.example.daohuyen.common.product.models.view.ProductViewModel;
import com.example.daohuyen.constants.Constant;
import com.example.daohuyen.response_model.NotFoundResponse;
import com.example.daohuyen.response_model.OkResponse;
import com.example.daohuyen.response_model.Response;
import com.example.daohuyen.response_model.ServerErrorResponse;
import com.example.daohuyen.utils.PageAndSortRequestBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/apriori")
@Api(value = "candidate-api", description = "Nhóm API Apriori")
@CrossOrigin(origins = "*")
public class AprioriController {
    @Autowired
    private AprioriRespository aprioriRespository;
    @Autowired
    private ProductRespository productRespository;

    @ApiOperation(value = "Suggest product")
    @GetMapping("/productsuggest/{inputcate}")
    public Response getDetailProduct(@PathVariable("inputcate") String inputcate,
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
            for (int i = 0; i <listCategory.size() ; i++) {
                Pageable pageable = PageAndSortRequestBuilder.createPageRequest(pageIndex, pageSize, sortBy, sortType, Constant.MAX_PAGE_SIZE);
                productViewModels = productRespository.getAllProductListByCategory(pageable,listCategory.get(i).getId());


            }

            response= new OkResponse(productViewModels);
        }catch (Exception e) {
            e.printStackTrace();
            response = new ServerErrorResponse();
        }
        return response;

    }
}
