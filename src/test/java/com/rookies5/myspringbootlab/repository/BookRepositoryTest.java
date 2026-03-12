package com.rookies5.myspringbootlab.repository;

import com.rookies5.myspringbootlab.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 도서 등록 테스트
    @Test
    void testCreateBook() {

        Book book = new Book(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                LocalDate.of(2025,5,7),
                30000
        );

        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
    }

    // ISBN으로 도서 조회 테스트
    @Test
    void testFindByIsbn() {

        Book book = new Book(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                LocalDate.of(2025,5,7),
                30000
        );

        bookRepository.save(book);

        Optional<Book> result = bookRepository.findByIsbn("9788956746425");

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("스프링 부트 입문");
    }

    // 저자명으로 도서 목록 조회 테스트
    @Test
    void testFindByAuthor() {

        bookRepository.save(new Book(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                LocalDate.of(2025,5,7),
                30000
        ));

        bookRepository.save(new Book(
                "JPA 프로그래밍",
                "홍길동",
                "9788956746432",
                LocalDate.of(2025,4,30),
                35000
        ));

        List<Book> books = bookRepository.findByAuthor("홍길동");

        assertThat(books.size()).isEqualTo(2);
    }

    // 도서 정보 수정 테스트
    @Test
    void testUpdateBook() {

        Book book = new Book(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                LocalDate.of(2025,5,7),
                30000
        );

        Book saved = bookRepository.save(book);

        saved.setPrice(32000);
        bookRepository.save(saved);

        Optional<Book> updated = bookRepository.findById(saved.getId());

        assertThat(updated.get().getPrice()).isEqualTo(32000);
    }

    // 도서 삭제 테스트
    @Test
    void testDeleteBook() {

        Book book = new Book(
                "스프링 부트 입문",
                "홍길동",
                "9788956746425",
                LocalDate.of(2025,5,7),
                30000
        );

        Book saved = bookRepository.save(book);

        bookRepository.deleteById(saved.getId());

        Optional<Book> result = bookRepository.findById(saved.getId());

        assertThat(result).isEmpty();
    }
}