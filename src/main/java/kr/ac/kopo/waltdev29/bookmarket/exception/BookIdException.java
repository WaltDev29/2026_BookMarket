package kr.ac.kopo.waltdev29.bookmarket.exception;

import lombok.Data;

@Data
public class BookIdException extends RuntimeException {
    private String bookId;

    public BookIdException(String bookId) {
        this.bookId = bookId;
    }
}
