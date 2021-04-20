package com.atlassian.confluence.service;

import com.atlassian.confluence.ao.Book;
import com.atlassian.confluence.model.BookModel;

public interface BookService {
    Book addBook(BookModel bookModel);
    void deleteBook(Integer id);
    BookModel[] getAllBooks();
}
