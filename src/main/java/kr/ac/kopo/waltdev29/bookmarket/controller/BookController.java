// presentation 계층을 제어하는 클래스

package kr.ac.kopo.waltdev29.bookmarket.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import kr.ac.kopo.waltdev29.bookmarket.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.*;

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
    @GetMapping(value="/id/{id}")
    public String requestBookById(
            Model model,
            @PathVariable("id") String bookId)
    {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "bookInfo";
    }


    // MatrixVariable로 도서 Filtering
    @GetMapping(value="/filter/{filter}")
    public String requestBookByFilter(
            Model model,
            @MatrixVariable(pathVar = "filter")Map<String, List<String>> filter
            )
    {
        Set<Book> bookList = bookService.getBooksByFilter(filter);
        model.addAttribute("bookList", bookList);
        return "books";
    }


    @Value("${file.uploadDir}")
    String fileDir;

    @GetMapping(value="/add")
    public String showAddBookPage(Model model)
    {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping(value="/add")
    public String addBook(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "addBook";

        MultipartFile bookImage = book.getBookImage();
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir, saveName);
        if (bookImage != null && !bookImage.isEmpty()) {
            try {
                bookImage.transferTo(saveFile);
            } catch (IOException e) {
                throw new RuntimeException("도서 이미지 업로드가 실패하였습니다." ,e);
            }
        }
        book.setFileName(saveName);
        bookService.setNewBook(book);
        return "redirect:/books";
    }

    @GetMapping("/download")
    public void bookImage(@RequestParam("file") String paramKey, HttpServletResponse response) {
        File imgFile = new File(fileDir + paramKey);
        response.setContentType("application/download");
        response.setContentLength((int)imgFile.length());
        response.setHeader("Content-Disposition", "attachment;filename=\"" + paramKey + "\"");

        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(imgFile);
            FileCopyUtils.copy(fis,os);
            fis.close();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ModelAttribute
    public void addTitle(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록");
    }
}
