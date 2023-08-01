package net.javaguides.springboottransactiondemo.dto;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboottransactiondemo.entity.Order;
import net.javaguides.springboottransactiondemo.entity.Payment;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
