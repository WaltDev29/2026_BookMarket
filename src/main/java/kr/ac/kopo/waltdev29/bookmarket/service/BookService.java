// Business 계층 : 데이터베이스에서 가져온 데이터를 가공하고 처리하는 계층

package kr.ac.kopo.waltdev29.bookmarket.service;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBooksByCategory(String category);
    Set<Book> getBooksByFilter(Map<String, List<String>> filter);
}
