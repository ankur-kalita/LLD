package Oops.BookDataReader;

import java.util.*;
import java.util.stream.Collectors;

public class BookApp {
    private final BookRepository repository;

    public BookApp(BookRepository repository) {
        this.repository = repository;
    }

    public int countBooksByAuthor(String author) {
        return (int) repository.getAllBooks().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .count();
    }

    public Set<String> getAllAuthors() {
        return repository.getAllBooks().stream()
                .map(Book::getAuthor)
                .collect(Collectors.toSet());
    }

    public List<String> getBooksByAuthor(String author) {
        return repository.getAllBooks().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByRating(double rating) {
        return repository.getAllBooks().stream()
                .filter(b -> b.getUserRating() == rating)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getPricesByAuthor(String author) {
        return repository.getAllBooks().stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toMap(Book::getTitle, Book::getPrice));
    }
}