// persistence 계층 : 데이터베이스와 소통하는 계층

package kr.ac.kopo.waltdev29.bookmarket.repository;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookRepository {
    List<Book> getAllBookList();
    Book getBookById(String bookId);
    List<Book> getBooksByCategory(String category);
    Set<Book> getBooksByFilter(Map<String, List<String>> filter);
    void setNewBook(Book book);
}
