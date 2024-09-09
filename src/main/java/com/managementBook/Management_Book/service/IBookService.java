package com.managementBook.Management_Book.service;

import com.managementBook.Management_Book.model.Book;

import java.util.List;

public interface IBookService {
    public List<Book> listBooks();

    public Book findByIdBook(int idBook);

    public void saveBook(Book book);

    public void deleteBook(Book book);
}
