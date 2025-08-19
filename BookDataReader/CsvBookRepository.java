package BookDataReader;

import java.util.*;
import java.io.*;

public class CsvBookRepository implements BookRepository {
    private String filename;

    public CsvBookRepository(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] tokens = parseLine(line);
                if (tokens.length != 7) continue; 
                try {
                    String title = tokens[0];
                    String author = tokens[1];
                    double userRating = Double.parseDouble(tokens[2]);
                    int reviews = Integer.parseInt(tokens[3]);
                    double price = Double.parseDouble(tokens[4]);
                    int year = Integer.parseInt(tokens[5]);
                    String genre = tokens[6];

                    books.add(new Book(title, author, userRating, reviews, price, year, genre));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing line: " + line + " - " + e.getMessage());
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    private String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuote = false;
        for (char c : line.toCharArray()) {
            if (c == '"') inQuote = !inQuote;
            else if (c == ',' && !inQuote) {
                result.add(cur.toString().trim());
                cur = new StringBuilder();
            } else cur.append(c);
        }
        result.add(cur.toString().trim());
        return result.toArray(new String[0]);
    }
}
