package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public  class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(order.getOrderId())
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId()).build();
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity order =  orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .items(order.getItems())
                .cost(order.getCost())
                .userId(order.getUserId()).build();
        return  orderDto;

    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setUserId(order.getUserId());
        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        orderRepository.deleteById(Long.valueOf(orderId));
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(OrderEntity order : orderRepository.findAll()){
            OrderDto orderDto = OrderDto.builder()
                    .orderId(order.getOrderId())
                    .items(order.getItems())
                    .cost(order.getCost())
                    .userId(order.getUserId()).build();
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}