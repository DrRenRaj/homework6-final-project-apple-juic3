package hw6;

import java.util.*;

public class LibrarySystem {

    static class Book {
        String t;
        String a;
        String idk; // ISBN
        boolean avail = true;

        Book(String t, String a, String idk) {
            this.t = t;
            this.a = a;
            this.idk = idk;
        }

        public String toString() {
            return t + " by " + a + " [" + idk + "] - " + (avail ? "Yes" : "No");
        }
    }

    static class Library {
        ArrayList<Book> books = new ArrayList<>();

        void add(Book b) {
            for (Book other : books) {
                if (other.idk.equals(b.idk)) {
                    System.out.println("nope, duplicate isbn");
                    return;
                }
            }
            books.add(b);
            System.out.println("added :)");
        }

        void remove(String i) {
            boolean gone = false;
            for (int j = 0; j < books.size(); j++) {
                if (books.get(j).idk.equals(i)) {
                    books.remove(j);
                    gone = true;
                    break;
                }
            }
            if (gone) System.out.println("bye book");
            else System.out.println("book not found");
        }

        void show() {
            if (books.size() == 0) {
                System.out.println("nothing here");
            } else {
                for (Book b : books) System.out.println(b);
            }
        }

        void findTitle(String t) {
            boolean f = false;
            for (Book b : books) {
                if (b.t.equalsIgnoreCase(t)) {
                    System.out.println(b);
                    f = true;
                }
            }
            if (!f) System.out.println("nah, no match");
        }

        void findAuthor(String a) {
            boolean f = false;
            for (Book b : books) {
                if (b.a.equalsIgnoreCase(a)) {
                    System.out.println(b);
                    f = true;
                }
            }
            if (!f) System.out.println("who?");
        }

        void out(String i) {
            for (Book b : books) {
                if (b.idk.equals(i)) {
                    if (b.avail) {
                        b.avail = false;
                        System.out.println("checked out");
                    } else {
                        System.out.println("already gone");
                    }
                    return;
                }
            }
            System.out.println("cant find it");
        }

        void back(String i) {
            for (Book b : books) {
                if (b.idk.equals(i)) {
                    if (!b.avail) {
                        b.avail = true;
                        System.out.println("back in");
                    } else {
                        System.out.println("was already here");
                    }
                    return;
                }
            }
            System.out.println("no such book");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Library l = new Library();
        int c = 0;

        while (c != 8) {
            System.out.println("\nmenu (choose wisely):");
            System.out.println("1 add");
            System.out.println("2 remove");
            System.out.println("3 show all");
            System.out.println("4 find title");
            System.out.println("5 find author");
            System.out.println("6 checkout");
            System.out.println("7 return");
            System.out.println("8 quit");

            try {
                c = Integer.parseInt(s.nextLine());
            } catch (Exception e) {
                System.out.println("bruh that's not a number");
                continue;
            }

            switch (c) {
                case 1:
                    System.out.print("title? ");
                    String t = s.nextLine();
                    System.out.print("author? ");
                    String a = s.nextLine();
                    System.out.print("isbn? ");
                    String i = s.nextLine();
                    l.add(new Book(t, a, i));
                    break;
                case 2:
                    System.out.print("isbn to remove: ");
                    l.remove(s.nextLine());
                    break;
                case 3:
                    l.show();
                    break;
                case 4:
                    System.out.print("title to find: ");
                    l.findTitle(s.nextLine());
                    break;
                case 5:
                    System.out.print("author to find: ");
                    l.findAuthor(s.nextLine());
                    break;
                case 6:
                    System.out.print("isbn to checkout: ");
                    l.out(s.nextLine());
                    break;
                case 7:
                    System.out.print("isbn to return: ");
                    l.back(s.nextLine());
                    break;
                case 8:
                    System.out.println("later nerd");
                    break;
                default:
                    System.out.println("that ain't it");
            }
        }

        s.close();
    }
}
