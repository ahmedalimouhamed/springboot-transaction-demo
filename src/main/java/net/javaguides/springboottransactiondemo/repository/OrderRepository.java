package net.javaguides.springboottransactiondemo.repository;

import net.javaguides.springboottransactiondemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
