package net.javaguides.springboottransactiondemo.service.impl;

import net.javaguides.springboottransactiondemo.dto.OrderRequest;
import net.javaguides.springboottransactiondemo.dto.OrderResponse;
import net.javaguides.springboottransactiondemo.entity.Order;
import net.javaguides.springboottransactiondemo.entity.Payment;
import net.javaguides.springboottransactiondemo.exception.PaymentException;
import net.javaguides.springboottransactiondemo.repository.OrderRepository;
import net.javaguides.springboottransactiondemo.repository.PaymentRepository;
import net.javaguides.springboottransactiondemo.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);
        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");

        return orderResponse;
    }
}
