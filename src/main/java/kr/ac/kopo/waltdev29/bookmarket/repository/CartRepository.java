package kr.ac.kopo.waltdev29.bookmarket.repository;

import kr.ac.kopo.waltdev29.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);
    Cart read(String cartId);
}
