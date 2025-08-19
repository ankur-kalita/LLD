package BookDataReader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookRepository repo = new CsvBookRepository("/Users/akquasar/Documents/Codes/LLD/BookDataReader/books.csv");
        BookApp app = new BookApp(repo);

        while (true) {
            System.out.println(
                "\nChoose:\n1.Total books by author\n2.All authors\n3.Books by author\n4.Books by rating\n5.Price of books by author\n6.Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter author: ");
                    String author1 = sc.nextLine();
                    System.out.println("Total: " + app.countBooksByAuthor(author1));
                    break;
                case 2:
                    System.out.println("Authors:");
                    app.getAllAuthors().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter author: ");
                    String author2 = sc.nextLine();
                    System.out.println("Books:");
                    app.getBooksByAuthor(author2).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter rating: ");
                    double rating = sc.nextDouble();
                    System.out.println("Books:");
                    app.getBooksByRating(rating).forEach(Book::printDetails);
                    break;
                case 5:
                    System.out.print("Enter author: ");
                    String author3 = sc.nextLine();
                    app.getPricesByAuthor(author3)
                        .forEach((title, price) -> System.out.println(title + ": $" + price));
                    break;
                case 6:
                    System.out.println("Bye!"); 
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid!");
            }
        }
    }
}