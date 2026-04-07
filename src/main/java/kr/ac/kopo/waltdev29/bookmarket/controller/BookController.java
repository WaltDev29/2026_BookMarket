// presentation 계층을 제어하는 클래스

package kr.ac.kopo.waltdev29.bookmarket.controller;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller // 문자열로 HTML을 매핑해서 반환하는 컨트롤러      // RestController는 HTML을 매핑해서 반환하지 않음
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @GetMapping // FastAPI의 @app.get 같은 것
    public String requestBookList(Model model) {
        List<Book> bookList = bookService.getAllBookList();
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

    @GetMapping(value="/{id}")
    public String requestBook(
            Model model,
            @PathVariable("id") String book_id)
    {
        model.addAttribute("id", book_id);
        return "bookInfo";
    }
}
