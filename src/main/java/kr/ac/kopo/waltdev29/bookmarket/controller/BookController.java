// presentation 계층을 제어하는 클래스

package kr.ac.kopo.waltdev29.bookmarket.controller;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)   // FastAPI의 @app.get 같은 것
    public String requestBookList(Model model) {
        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }
}
