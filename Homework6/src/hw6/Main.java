package hw6;
import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

    // Book class
    static class Book {
        private String title;
        private String author;
        private String isbn;
        private boolean isAvailable;

        public Book(String title, String author, String isbn) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isAvailable = true;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getIsbn() { return isbn; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { this.isAvailable = available; }

        public String toString() {
            return title + " by " + author + " (ISBN: " + isbn + ") - " +
                    (isAvailable ? "Available" : "Checked out");
        }
    }

    // Library class
    static class Library {
        private ArrayList<Book> books = new ArrayList<>();

        public void addBook(Book book) {
            for (Book b : books) {
                if (b.getIsbn().equals(book.getIsbn())) {
                    System.out.println("Book with that ISBN already exists.");
                    return;
                }
            }
            books.add(book);
            System.out.println("Book added successfully.");
        }

        public void removeBook(String isbn) {
            Book toRemove = null;
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    toRemove = b;
                    break;
                }
            }
            if (toRemove != null) {
                books.remove(toRemove);
                System.out.println("Book removed.");
            } else {
                System.out.println("Book not found.");
            }
        }

        public void displayAllBooks() {
            if (books.isEmpty()) {
                System.out.println("No books in the library.");
            } else {
                for (Book b : books) {
                    System.out.println(b);
                }
            }
        }

        public void searchByTitle(String title) {
            boolean found = false;
            for (Book b : books) {
                if (b.getTitle().equalsIgnoreCase(title)) {
                    System.out.println(b);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No book found with that title.");
            }
        }

        public void searchByAuthor(String author) {
            boolean found = false;
            for (Book b : books) {
                if (b.getAuthor().equalsIgnoreCase(author)) {
                    System.out.println(b);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No book found by that author.");
            }
        }

        public void checkOutBook(String isbn) {
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    if (b.isAvailable()) {
                        b.setAvailable(false);
                        System.out.println("Book checked out.");
                    } else {
                        System.out.println("Book is already checked out.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }

        public void returnBook(String isbn) {
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    if (!b.isAvailable()) {
                        b.setAvailable(true);
                        System.out.println("Book returned.");
                    } else {
                        System.out.println("Book was already available.");
                    }
                    return;
                }
            }
            System.out.println("Book not found.");
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice = 0;

        while (choice != 8) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String t = sc.nextLine();
                    System.out.print("Author: ");
                    String a = sc.nextLine();
                    System.out.print("ISBN: ");
                    String i = sc.nextLine();
                    lib.addBook(new Book(t, a, i));
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    lib.removeBook(sc.nextLine());
                    break;
                case 3:
                    lib.displayAllBooks();
                    break;
                case 4:
                    System.out.print("Enter title to search: ");
                    lib.searchByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter author to search: ");
                    lib.searchByAuthor(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter ISBN to check out: ");
                    lib.checkOutBook(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter ISBN to return: ");
                    lib.returnBook(sc.nextLine());
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
