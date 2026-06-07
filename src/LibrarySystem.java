import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.scanner;

public class librarySystem
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in); // scanner collects user input from keyboard
        Inventory inventory = new Inventory(); // inventory object controls library book lists

        boolean running = true; // keeps the menu running until user chooses "Exit"

        while (running)
        {
            printMenu();

            try
            {
                System.out.print("Choose an option: ");
                int choice = input.nextInt();
                input.nextLine();

                // menu choices call separarte helper methods
                if (choice == 1) 
                {
                    addBookMenu(input, inventory);
                }
                else if (choice == 2)
                {
                    borrowedBookMenu(input, inventory);
                }
                else if (choice == 3)
                {
                    returnBookMenu(input, inventory);
                }
                else if (choice == 4)
                {
                    searchBookMenu(input, inventory);
                }
                else if (choice == 5)
                {
                    inventory.printAll();
                }
                else if (choice == 6)
                {
                    System.out.println("\nExiting program. Goodbye!");
                    running = false;
                }
                else
                {
                    System.out.println("\nInvalid option. Please choose 1-6.");
                }
            }
            catch (InputMismatchException e)
            {
                System.err.println("\nInvalid input. Please enter a number."); // handles non-number input for menu choice
                input.nextLine();
            }
        }

        input.close();  
    }

    public static void printMenu() // prints themed menu (going for a dark academia theme)
    {
        System.out.println();
        System.out.println("╭────────────────────────────────╮");
        System.out.println("│        Raven Hall Library        │");
        System.out.println("│      Collection Management       │");
        System.out.println("╰────────────────────────────────╯");
        System.out.println("[1] Add Book");
        System.out.println("[2] Borrow Book");
        System.out.println("[3] Return Book");
        System.out.println("[4] Search by Title");
        System.out.println("[5] Print All Books");
        System.out.println("[6] Exit");
        System.out.println("──────────────────────────────────");
    }

    // collects book details from the user and adds a new book 
    public static void addBookMenu(Scanner input, Inventory inventory)
    {
        try
        {
            System.out.print("Enter book ID: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Enter title: ");
            String title = input.nextLine();

            System.out.print("Enter author: ");
            String author = input.nextLine();

            System.out.print("Enter ISBN: ");
            String isbn = input.nextLine();

            System.out.print("Enter number of pages: ");
            int pages = input.nextInt();
            input.nextLine();

            Book newBook = new Book(id, title, author, isbn, pages); // creates a Book object using parameterized constructor 
            inventory.addBook(newBook); // sends the book object to inventory for storage 

            System.out.println("\nBook added to the library.");
        }
        catch (InputMismatchException e)
        {
            // handles invalid number input while addint a book 
            System.out.println("\nInvalid input. Book was not added.");
            input.nextLine();
        }
    }

    // handles borrowing by asking for book ID
    public static void borrowBookMenu(Scanner input, Inventory inventory)
    {
        try
        {
            System.out.print("Enter book ID to borrow: ");
            int id = input.nextInt();
            input.nextLine();

            if (inventory.borrowBook(id)) // borrowBook returns true when the book is found and moved
            {
                System.out.println("\nBook successfully borrowed.");
            }
            else
            {
                System.out.println("\nBook not found or already borrowed.");
            }
        }
        catch (InputMismatchException e)
        {
            // handles invalid ID input
            System.out.println("\nInvalid input. Please enter a valid ID.");
            input.nextLine();
        }
    }

    // handles returning a borrowed book by ID 
    public static void returnBookMenu(Scanner input, Inventory inventory)
    {
        try
        {
            System.out.print("Enter book ID to return: ");
            int id = input.nextInt();
            input.nextLine();

            if (inventory.returnBook(id)) // returnBook returns true when the book is found and moved back
            {
                System.out.println("\nBook successfully returned.");
            }
            else
            {
                System.out.println("\nBook was not found in borrowed books.");
            }
        }
        catch (InputMismatchException e)
        {
            // handles invalid ID input
            System.out.println("\nInvalid input. Please enter a valid ID.");
            input.nextLine();
        }
    }

    // seraches available books by full or partial title
    public static void searchBookMenu(Scanner input, Inventory inventory)
    {
        System.out.print("Enter full or partial title: ");
        String title = input.nextLine();

        inventory.searchByTitle(title);
    }
}