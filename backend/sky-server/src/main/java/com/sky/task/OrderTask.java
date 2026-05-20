package com.sky.task;

import com.sky.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.sky.mapper.OrderMapper;

/**
 * 定时任务类。定时处理订单状态
 */
@Component
@Slf4j

public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
    处理支付超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder(){
        log.info("定时处理超时订单：{}", LocalDateTime.now());

        LocalDateTime time=LocalDateTime.now().plusMinutes(-15);

        //Select * from orders where status = ? and order_time < (当前时间-15分钟)
        List<Orders> ordersList=orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,time);

        if(ordersList != null && ordersList.size() > 0){
            for(Orders  orders:ordersList ){
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    /**
     * 处理一直处于派送中状态的订单
     */
    @Scheduled(cron = "0 0 21 * * ?")
    public void processDeliveryOrder(){
        log.info("定时处理处于派送中的订单：{}", LocalDateTime.now());

        List<Orders> ordersList=orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS,LocalDateTime.now());
        if(ordersList != null && ordersList.size() > 0){
            for(Orders  orders:ordersList ){
                orders.setStatus(Orders.COMPLETED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }
}
