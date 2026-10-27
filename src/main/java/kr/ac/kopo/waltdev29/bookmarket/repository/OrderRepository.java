package kr.ac.kopo.waltdev29.bookmarket.repository;

import kr.ac.kopo.waltdev29.bookmarket.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);
}
