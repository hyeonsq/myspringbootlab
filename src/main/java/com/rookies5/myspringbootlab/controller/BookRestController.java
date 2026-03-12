package com.rookies5.myspringbootlab.controller;

import com.rookies5.myspringbootlab.entity.Book;
import com.rookies5.myspringbootlab.repository.BookRepository;
import com.rookies5.myspringbootlab.exception.BusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 모든 도서 조회
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ID로 도서 조회 (Optional map / orElse 사용)
    @GetMapping("/{id}")
    public ResponseEntity<Book> getUserById(@PathVariable Long id) {

        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ISBN으로 도서 조회 (BusinessException 사용)
    @GetMapping("/isbn/{isbn}")
    public Book getUserByIsbn(@PathVariable String isbn) {

        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() ->
                        new BusinessException("BOOK_NOT_FOUND", "해당 ISBN의 도서를 찾을 수 없습니다.")
                );
    }

    // 새 도서 등록
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 도서 정보 수정
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {

        Book existing = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("BOOK_NOT_FOUND", "수정할 도서를 찾을 수 없습니다.")
                );

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setPrice(book.getPrice());
        existing.setPublishDate(book.getPublishDate());

        return bookRepository.save(existing);
    }

    // 도서 삭제
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("BOOK_NOT_FOUND", "삭제할 도서를 찾을 수 없습니다.")
                );

        bookRepository.delete(book);
    }
}