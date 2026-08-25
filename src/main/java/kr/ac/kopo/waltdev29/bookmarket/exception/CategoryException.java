package kr.ac.kopo.waltdev29.bookmarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException{
    private String errorMessage;

    public CategoryException() {
        this.errorMessage = "요청한 도서 카테고리가 존재하지 않습니다.";
    }
}
