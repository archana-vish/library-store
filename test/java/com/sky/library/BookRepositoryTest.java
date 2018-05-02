package test.java.com.sky.library;

import main.java.com.sky.library.BookNotFoundException;
import main.java.com.sky.library.BookService;
import main.java.com.sky.library.BookServiceImpl;

public class BookRepositoryTest {

    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl(new com.sky.library.BookRepositoryStub());
        try {
//            System.out.println(bookService.retrieveBook("INVALID-TEXT"));
//            System.out.println(bookService.retrieveBook("BOOK-999"));
            System.out.println(bookService.retrieveBook("BOOK-GRUFF472"));

//            System.out.println(bookService.getBookSummary("INVALID-TEXT"));
//            System.out.println(bookService.getBookSummary("BOOK-999"));
            System.out.println(bookService.getBookSummary("BOOK-GRUFF472"));
            System.out.println(bookService.getBookSummary("BOOK-WILL987"));





        } catch (BookNotFoundException bnfe) {
            System.out.println(bnfe.getMessage());
        }


    }
}
