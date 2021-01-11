package com.wcs.Spring.Rest.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wcs.Spring.Rest.entity.Book;
import com.wcs.Spring.Rest.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public Book getBookById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	public Book updateBook(@RequestBody Book book,Long id) {
		Book bookToUpdate = bookRepository.findById(id).get();
		bookToUpdate.setTitle(book.getTitle());
		bookToUpdate.setAuthor(book.getAuthor());
		bookToUpdate.setDescription(book.getDescription());
		return bookRepository.save(bookToUpdate);
	}
	
	public boolean deleteBook(Long id) {
		bookRepository.deleteById(id);
		return true;
	}
	
	public List<Book> searchByKeyWords(@RequestBody Map<String,String> body) {
		String searchTerm = body.get("text");
		 return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
	}
}
