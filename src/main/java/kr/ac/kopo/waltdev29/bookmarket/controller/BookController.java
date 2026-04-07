// presentation 계층을 제어하는 클래스

package kr.ac.kopo.waltdev29.bookmarket.controller;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller // 문자열로 HTML을 매핑해서 반환하는 컨트롤러      // RestController는 HTML을 매핑해서 반환하지 않음
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @GetMapping // FastAPI의 @app.get 같은 것
    public String requestBookList(Model model, @RequestParam(value = "category", required = false) String category) {
        List<Book> bookList = new ArrayList<>();
        if (category == null) {
            bookList = bookService.getAllBookList();
        }
        else {
            bookList = bookService.getBooksByCategory(category);
        }

        model.addAttribute("bookList", bookList);   // HTML에 담을 데이터 설정
        return "books"; // View 이름 (Not String)
    }



    // ModelAndView 실습 (결과는 위 엔드포인트와 같음)
    @GetMapping(value="/all")
    public ModelAndView requestAllBooks() {
        ModelAndView mav = new ModelAndView();
        List<Book> bookList= bookService.getAllBookList();
        mav.addObject("bookList", bookList);
        mav.setViewName("books");
        return mav;
    }



    // Path Parameter(id)로 특정 도서 상세 정보 반환
    @GetMapping(value="/{id}")
    public String requestBookById(
            Model model,
            @PathVariable("id") String bookId)
    {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "bookInfo";
    }
}
