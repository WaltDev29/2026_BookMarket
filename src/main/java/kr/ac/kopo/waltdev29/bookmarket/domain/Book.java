// 데이터베이스에서 가져온 데이터를 저장하는 객체

package kr.ac.kopo.waltdev29.bookmarket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data // data 클래스임을 명시
@Setter // setter를 자동으로 생성
@Getter // getter를 자동으로 생성
public class Book {
    private String bookId;  // 도서 ID
    private String name;    // 도서 이름
    private BigDecimal unitPrice;   // 가격
    private String author;  // 저자
    private String description; // 설명
    private String publisher;   // 출판사
    private String category;    // 분류
    private long unitsInStock;  // 재고
    private String releaseDate; // 출판 일자
    private String condition;   // 책 상태 (신품, 중고, e-book)
    private String fileName;

    public Book(String bookId, String name, BigDecimal unitPrice, String author, String description, String publisher, String category, String releaseDate) {
        this.bookId = bookId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.category = category;
        this.releaseDate = releaseDate;
        this.fileName = bookId + ".jpg";
    }
}
