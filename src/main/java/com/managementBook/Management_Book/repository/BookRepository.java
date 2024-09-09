package com.managementBook.Management_Book.repository;

import com.managementBook.Management_Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
