package Hibernate_Library;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final EntityDao dao = new EntityDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().openSession().close();

        apps();
    }

    private static void apps() {
        String command = null;
        do {
            System.out.println("Select number:\n1. Add\n2. List\n3. Delete\n4. Modify");
            command = scanner.nextLine();
            switch (command) {
                case "1":
                    addWhat();
                    break;
                case "2":
                    listWhat();
                    break;
                case "3":
                    deleteWhat();
                    break;
                case "4":
                    modifyWhat();
                    break;
            }
        } while (!command.equalsIgnoreCase("quit"));
    }

    private static void modifyWhat() {
        String command = null;
        System.out.println("Select number:\n1. Author\n2. Book\n3. Client");
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("1")) {
            dao.saveOrUpdate(modifyAuthor());
        } else if (command.equalsIgnoreCase("2")) {
            dao.saveOrUpdate(modifyBook());
        } else if (command.equalsIgnoreCase("3")) {
            dao.saveOrUpdate(modifyClient());
        } else addWhat();
    }

    private static Client modifyClient() {
        Client client;

        System.out.println("Id:");
        String id = scanner.nextLine();
        client = createClient();
        client.setId(Long.valueOf(id));
//        client.setId(Long.valueOf(scanner.nextLine()));
        return client;
    }

    private static Book modifyBook() {
        Book book;
        book = createBook();

        System.out.println("Id:");
        book.setId(Long.valueOf(scanner.nextLine()));
        return book;
    }

    private static Author modifyAuthor() {
        Author author;
        author = createAuthor();

        System.out.println("Id:");
        author.setId(Long.valueOf(scanner.nextLine()));
        return author;
    }

    private static void deleteWhat() {
        String command = null;
        System.out.println("Select number:\n1. Author\n2. Book\n3. Client");
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("1")) {
            dao.delete(Author.class, writeId());
        } else if (command.equalsIgnoreCase("2")) {
            dao.delete(Book.class, writeId());
        } else if (command.equalsIgnoreCase("3")) {
            dao.delete(Client.class, writeId());
        } else deleteWhat();
    }

    private static Long writeId() {
        System.out.println("Which id you want to delete?");

        return scanner.nextLong();
    }

    private static void listWhat() {
        String command = null;
        System.out.println("Select number:\n1. Author\n2. Book\n3. Client");
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("1")) {
            System.out.println();
            dao.getAll(Author.class).forEach(System.out::println);
        } else if (command.equalsIgnoreCase("2")) {
            System.out.println();
            dao.getAll(Book.class).forEach(System.out::println);
        } else if (command.equalsIgnoreCase("3")) {
            System.out.println();
            dao.getAll(Client.class).forEach(System.out::println);
        } else listWhat();
    }

    private static void addWhat() {
        String command = null;
        System.out.println("Select number:\n1. Author\n2. Book\n3. Client");
        command = scanner.nextLine();
        if (command.equalsIgnoreCase("1")) {
            dao.saveOrUpdate(createAuthor());
        } else if (command.equalsIgnoreCase("2")) {
            dao.saveOrUpdate(createBook());
        } else if (command.equalsIgnoreCase("3")) {
            dao.saveOrUpdate(createClient());
        } else addWhat();
    }

    private static Client createClient() {
        Client client = new Client();

        System.out.println("Name:");
        client.setName(scanner.nextLine());
        System.out.println("Surname:");
        client.setSurname(scanner.nextLine());
        System.out.println("Id number:");
        client.setIdNumber(scanner.nextLine());

        return client;
    }

    private static Book createBook() {
        Book book = new Book();

        System.out.println("Title:");
        book.setTitle(scanner.nextLine());
        System.out.println("Year written");
        book.setYearWritten(Integer.parseInt(scanner.nextLine()));
        System.out.println("Number of pages");
        book.setNumberOfPages(Integer.parseInt(scanner.nextLine()));
        System.out.println("Number of available copies");
        book.setNumberOfAvailableCopies(Integer.parseInt(scanner.nextLine()));

        return book;
    }

    private static Author createAuthor() {
        Author author = new Author();

        System.out.println("Name:");
        author.setName(scanner.nextLine());
        System.out.println("Surname:");
        author.setSurname(scanner.nextLine());
        System.out.println("Date of birth:");
        author.setDateOfBirth(LocalDate.parse(scanner.nextLine()));

        return author;
    }

}
