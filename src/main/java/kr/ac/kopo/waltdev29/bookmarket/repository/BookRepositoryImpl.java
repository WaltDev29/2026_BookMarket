package kr.ac.kopo.waltdev29.bookmarket.repository;

import kr.ac.kopo.waltdev29.bookmarket.domain.Book;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository // Repository 계층임을 명시
public class BookRepositoryImpl implements BookRepository {
    private List<Book> listOfBooks = new ArrayList<>();

    public BookRepositoryImpl() {
        Book book1 = new Book(
                "isbn1001",
                "스프링 부트 완전정복",
                new BigDecimal(35000),
                "송미영",
                "스프링 부트는 스프링을 기반으로 쉽고 빠르게 웹 애플리케이션을 개발할 수 있는 도구입니다. 이 책에서는 스프링 부트의 기본 개념을 쉽게 이해하고 다양한 실습 예제로 빠르게 익힐 수 있습니다. 그리고 단계별 실습을 따라 하다 보면 도서 쇼핑몰 구축 프로젝트를 완성할 수 있습니다. 개념-실습-프로젝트의 3단계 학습으로 스프링 부트를 제대로 익힌다면 개발 시간을 단축하고 생산성을 높일 수 있는 개발자로 성장할 수 있습니다.",
                "길벗캠퍼스",
                "IT",
                "2024/12/31"
        );

        Book book2 = new Book(
                "isbn1002",
                "자몽살구클럽",
                new BigDecimal(10800),
                "한로로",
                "죽고 싶은 아이들의\n" +
                        "살구 싶은 이야기\n" +
                        "싱어송라이터 한로로의\n" +
                        "첫 번째 단편소설\n",
                "어센틱",
                "소설",
                "2025/07/25"
        );

        Book book3 = new Book(
                "isbn1003",
                "끝없는 이야기",
                new BigDecimal(20000),
                "미하엘 엔데",
                "〈모모〉의 작가 미하엘 엔데의 〈끝없는 이야기〉. '네가 원하는 것을 해라'라고 환상 세계를 상징하는 메달에 쓰여 있었다. 바스티안은 길고 힘겨운 모험 끝에서야 비로소 이 문장을 참뜻을 깨닫게 되는데.....",
                "비룡소",
                "소설",
                "2003/03/15"
        );

        Book book4 = new Book(
                "isbn1004",
                "필로소피 랩",
                new BigDecimal(11900),
                "조니 톰슨",
                "당신 삶과 직결되는 철학자를 연결해주는 철학 연구소\n" +
                        "옥스퍼드 대학 철학 교수가 알려주는 맞춤형 철학 솔루션\n" +
                        "지금 당신을 구할 철학자들의 130여 가지 대답들",
                "윌북",
                "철학",
                "2021/10/20"
        );

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);
    }


    // ============ 전체 도서 목록 반환 ============
    @Override
    public List<Book> getAllBookList() {
        return listOfBooks;
    }


    // ============ ID로 특정 도서 반환 ============
    @Override
    public Book getBookById(String bookId) {
        Book searchBook = null;

        // ====== 도서 목록 순회하면서 ID 일치 도서 찾기 ======
        for (Book book : listOfBooks) {
            if (book != null && book.getBookId() != null && book.getBookId().equals(bookId)) {
                searchBook = book;
                break;
            }
        }

        // ====== 해당 도서 없을 경우 예외 반환 ======
        if (searchBook == null) {
            throw new IllegalArgumentException("도서ID가 " + bookId + "인 도서를 찾을 수 없습니다.");
        }

        return searchBook;
    }


    // ============ 카테고리로 도서목록 반환 ============
    @Override
    public List<Book> getBooksByCategory(String category) {
        List<Book> booksByCategory = new ArrayList<>();

        for (Book book : listOfBooks) {
            if (book != null && book.getCategory() != null && book.getCategory().equalsIgnoreCase(category)) {
                booksByCategory.add(book);
            }
        }

        if (booksByCategory.isEmpty()) {
            throw new IllegalArgumentException(category + " 카테고리 도서가 없습니다.");
        }

        return booksByCategory;
    }


    // ============ 카테고리&출판사 Filter로 도서목록 반환 ============
    @Override
    public Set<Book> getBooksByFilter(Map<String, List<String>> filter) {
        Set<Book> result = new HashSet<>(listOfBooks);

        if (filter.containsKey("category")) {
            Set<Book> booksByCategory = new HashSet<>();
            for (String category : filter.get("category")) {
                booksByCategory.addAll(getBooksByCategory(category));
            }
            result.retainAll(booksByCategory);
        }

        if (filter.containsKey("publisher")) {
            Set<Book> booksByPublisher = new HashSet<>();
            for (String publisher : filter.get("publisher")) {
                for (Book book : listOfBooks) {
                    if (book.getPublisher() != null &&
                            book.getPublisher().equalsIgnoreCase(publisher)) {
                        booksByPublisher.add(book);
                    }
                }
            }
            result.retainAll(booksByPublisher);
        }

        return result;
    }
}
