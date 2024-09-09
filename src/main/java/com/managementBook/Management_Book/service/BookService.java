package com.managementBook.Management_Book.service;

import com.managementBook.Management_Book.model.Book;
import com.managementBook.Management_Book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;


    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findByIdBook(int idBook) {
        Book book = bookRepository.findById(idBook).orElse(null);
        return book;
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
