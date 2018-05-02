package main.java.com.sky.library;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException {
        if (!bookReference.startsWith(bookRepository.getBookReferencePrefix())) {
            throw new BookNotFoundException("Invalid book prefix :: " + bookReference);
        } else if (bookRepository.retrieveBook(bookReference) == null) {
            throw new BookNotFoundException("Book not found for reference :: " + bookReference);
        } else {
            return bookRepository.retrieveBook(bookReference);
        }
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException {
        if (!bookReference.startsWith(bookRepository.getBookReferencePrefix())) {
            throw new BookNotFoundException("Invalid book prefix :: " + bookReference);
        } else if (bookRepository.retrieveBook(bookReference) == null) {
            throw new BookNotFoundException("Book not found for reference :: " + bookReference);
        } else {
            return getBookSummaryDetails(bookRepository.retrieveBook(bookReference));
        }
    }

    private String getBookSummaryDetails(Book book) {
        return book.getReference() + "-" + book.getTitle() + "-" + checkReview(book.getReview());
    }


    public String checkReview(String review) {
       String[] words =  review.split("\\s");

        String result = Arrays.stream(words)
                .limit(9)
                .collect(Collectors.joining(" "));

        if (words.length > 9) { result = result.concat("..."); }

        return result;

    }
}
