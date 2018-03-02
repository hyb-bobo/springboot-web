package com.machi.springbootweb.controller;

import com.machi.springbootweb.Enum.ResultEnum;
import com.machi.springbootweb.converter.OrderForm2OrderDTO;
import com.machi.springbootweb.dto.OrderDTO;
import com.machi.springbootweb.exception.SellException;
import com.machi.springbootweb.form.OrderForm;
import com.machi.springbootweb.service.BuyerOrderService;
import com.machi.springbootweb.service.OrderService;
import com.machi.springbootweb.util.ResultVO;
import com.machi.springbootweb.util.ReturnUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/3/2.
 */

@RestController
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    private Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired private OrderService orderService;

    @Autowired private BuyerOrderService buyerOrderService;

    //创建订单
    @PostMapping("/createOrder")
    public ResultVO<Map<String,String>> create(
            @Valid OrderForm orderForm,BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            logger.error("【创建订单】前台参数不正确, orderForm = {}",orderForm);
            throw new SellException(ResultEnum.PRRAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTO.orderForm2OrderDTO(orderForm);

        OrderDTO dto = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",dto.getOrderId());

        return ReturnUtil.success(map);
    }

    //查询订单列表
    @GetMapping(value = "/list")
    public ResultVO<List<OrderDTO>> list(
            @RequestParam(value = "openid") String openid,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "0") Integer size){
        PageRequest pageRequest = new PageRequest(page,size);

        Page<OrderDTO> orderDTOPage = orderService.findOrderList(openid, pageRequest);

        List<OrderDTO> orderDTOList = orderDTOPage.getContent();

        return ReturnUtil.success(orderDTOList);
    }

    //订单详情
    @GetMapping(value = "/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

        OrderDTO orderOne = buyerOrderService.findOrderOne(openid, orderId);

        return ReturnUtil.success(orderOne);
    }

    //取消订单
    @PostMapping(value = "/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        buyerOrderService.findOrderOne(openid, orderId);
        return ReturnUtil.success();
    }
}
