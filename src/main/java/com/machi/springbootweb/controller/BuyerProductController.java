package com.machi.springbootweb.controller;

import com.machi.springbootweb.model.ProductCategory;
import com.machi.springbootweb.model.ProductInfo;
import com.machi.springbootweb.service.ProductCategoryService;
import com.machi.springbootweb.service.ProductService;
import com.machi.springbootweb.util.ProductCategoryVO;
import com.machi.springbootweb.util.ProductInfoVO;
import com.machi.springbootweb.util.ReturnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2018/2/28.
 */

@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/list")
    public Object list(){
        //查询所有在线商品
        List<ProductInfo> productInfos = productService.findUpAll();

        List<Integer> list = productInfos.stream().map(x -> x.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> categoryList = productCategoryService.findByCategoryTypeIn(list);

        List<ProductCategoryVO> categoryVOList = new ArrayList<>();

        for (ProductCategory category:categoryList){
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCategoryName(category.getCategoryName());
            productCategoryVO.setCategoryType(category.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfos){
                if (productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productCategoryVO.setFoods(productInfoVOList);
            categoryVOList.add(productCategoryVO);
        }

        return ReturnUtil.success(categoryVOList);
    }

}
