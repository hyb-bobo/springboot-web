package com.machi.springbootweb.repository;

import com.machi.springbootweb.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dell on 2018/3/1.
 */
public interface OrderRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}
