package kr.ac.kopo.waltdev29.bookmarket.service;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.domain.Order;
import kr.ac.kopo.waltdev29.bookmarket.repository.BookRepository;
import kr.ac.kopo.waltdev29.bookmarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void confirmOrder(String bookId, long quantity) {
        Book bookById = bookRepository.getBookById(bookId);
        if (bookById.getUnitsInStock() < quantity) throw new IllegalArgumentException("도서 재고가 부족합니다. 구입 가능 수량 : " + bookById.getUnitsInStock() + "권");

        bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
    }

    @Override
    public Long saveOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        return orderId;
    }
}
