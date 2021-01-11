package com.wcs.Spring.Rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.Spring.Rest.entity.Book;
import com.wcs.Spring.Rest.service.BookService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		Book bookToCreate = bookService.createBook(book);
		return ResponseEntity.ok().body(bookToCreate);
	}
	
	@GetMapping("/books")
	public List<Book> indexBook() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> showBook(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		return ResponseEntity.ok().body(book);
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
		Book bookToUpdate = bookService.updateBook(book, id);
		return ResponseEntity.ok().body(bookToUpdate);
	}
	
	@DeleteMapping("/books/{id}")
	public boolean deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id);	
	}
	
	@PostMapping("/books/search")
	public List<Book> search(@RequestBody Map<String, String> body) {
		return bookService.searchByKeyWords(body);
	}
	
}
