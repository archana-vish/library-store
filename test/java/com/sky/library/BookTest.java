package test.java.com.sky.library;


import main.java.com.sky.library.BookNotFoundException;
import main.java.com.sky.library.BookServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BookTest {

    BookServiceImpl bookService = new BookServiceImpl(new com.sky.library.BookRepositoryStub());

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void validBook() throws BookNotFoundException{
        assertEquals("The Gruffalo",
                bookService.retrieveBook("BOOK-GRUFF472").getTitle());
    }

    @Test
    public void invalidPrefix() throws BookNotFoundException {
        thrown.expect(BookNotFoundException.class);
        thrown.expectMessage("Invalid book prefix :: INVALID-TEXT");
        bookService.retrieveBook("INVALID-TEXT");
    }

    @Test
    public void invalidBook() throws BookNotFoundException {
        thrown.expect(BookNotFoundException.class);
        thrown.expectMessage("Book not found for reference :: BOOK-999");
        bookService.retrieveBook("BOOK-999");
    }

    @Test
    public void validBookSummary() throws BookNotFoundException{
        assertEquals("BOOK-GRUFF472-The Gruffalo-A mouse taking a walk in the woods",
                bookService.getBookSummary("BOOK-GRUFF472"));
        assertEquals("BOOK-WILL987-The Wind In The Willows-With the arrival of spring and fine weather outside,...",
                bookService.getBookSummary("BOOK-WILL987"));
    }

    @Test
    public void invalidPrefixForSummary() throws BookNotFoundException {
        thrown.expect(BookNotFoundException.class);
        thrown.expectMessage("Invalid book prefix :: INVALID-TEXT");
        bookService.getBookSummary("INVALID-TEXT");
    }

    @Test
    public void invalidBookForSummary() throws BookNotFoundException {
        thrown.expect(BookNotFoundException.class);
        thrown.expectMessage("Book not found for reference :: BOOK-999");
        bookService.getBookSummary("BOOK-999");
    }
}
