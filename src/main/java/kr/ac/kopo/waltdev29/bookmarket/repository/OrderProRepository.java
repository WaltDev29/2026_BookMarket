package kr.ac.kopo.waltdev29.bookmarket.repository;

import kr.ac.kopo.waltdev29.bookmarket.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProRepository extends JpaRepository<Order, Long> {

}
