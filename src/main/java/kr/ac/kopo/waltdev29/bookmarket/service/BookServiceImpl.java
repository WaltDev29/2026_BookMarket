package kr.ac.kopo.waltdev29.bookmarket.service;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service    // Service 계층임을 명시
public class BookServiceImpl implements BookService{
    @Autowired  // 객체를 인스턴스화 하지 않고 사용할 수 있도록 해줌.
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBookList() {
        return bookRepository.getAllBookList();
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.getBooksByCategory(category);
    }

    @Override
    public Set<Book> getBooksByFilter(Map<String, List<String>> filter) {
        return bookRepository.getBooksByFilter(filter);
    }
}
