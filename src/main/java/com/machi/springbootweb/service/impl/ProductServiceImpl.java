package com.machi.springbootweb.service.impl;

import com.machi.springbootweb.Enum.ProductStatusEnum;
import com.machi.springbootweb.Enum.ResultEnum;
import com.machi.springbootweb.dto.CartDTO;
import com.machi.springbootweb.exception.SellException;
import com.machi.springbootweb.model.ProductInfo;
import com.machi.springbootweb.repository.ProductInfoRepository;
import com.machi.springbootweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dell on 2018/2/28.
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
       return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO : cartDTOS){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int number = productInfo.getProductStock() + cartDTO.getProductQuantity();

            productInfo.setProductStock(number);

            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO : cartDTOS){
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //库存数减去购买数
            int number = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if (number<0)
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);

            productInfo.setProductStock(number);

            repository.save(productInfo);
        }
    }


}
