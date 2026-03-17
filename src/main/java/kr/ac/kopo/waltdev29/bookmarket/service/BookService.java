// Business 계층 : 데이터베이스에서 가져온 데이터를 가공하고 처리하는 계층

package kr.ac.kopo.waltdev29.bookmarket.service;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBookList();
}
