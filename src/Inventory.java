import java.util.ArrayList;

public class Inventory
{
    
    private ArrayList<Book> mainInventory; // stores books currently available in the library
    private ArrayList<Book> borrowedBooks; // stores books that have been borrowed

    // constructor initializes both inventory lists
    public Inventory()
    {
        mainInventory = new ArrayList<Book>();
        borrowedBooks = new ArrayList<Book>();
    }

    // adds a new book to the main inventory 
    public void addBook(Book book)
    {
        mainInventory.add(book);
    }

    public boolean borrowBook(int id) // borrows a book by moving it from available inventory to the borrowed books list
    {
        for (Book book : mainInventory)
        {
            if (book.getId() == id)
            {
                borrowedBooks.add(book);
                mainInventory.remove(book);
                return true;
            }
        }

        return false; // book not found 
    }

    public boolean returnBook(int id) // returns a borrowed book back to main inventory
    {
        for (Book book : borrowedBooks)
        {
            if (book.getId() == id)
            {
                mainInventory.add(book);
                borrowedBooks.remove(book);
                return true;
            }
        }

        return false; // book was not found in borrowed inventory 

    }

    public void printAll() // prints all books currently available in the library
    {
        if (mainInventory.isEmpty())
        {
            System.out.println("No books in inventory.");
            return;
        }

        for (Book book : mainInventory)
        {
            book.printBookInfo();
        }
    }

    public void searchByTitle(String title) // search for books using a full or partial title match
    {                                       // search is case-insensitive
        boolean found = false;

        for (Book book : mainInventory)
        {
            if (book.getTitle().toLowerCase()
                    .contains(title.toLowerCase()))
                {
                    book.printBookInfo();
                    found = true;
                }
        }

        if (!found)
        {
            System.out.println("No matching book found.");
        }
    }

    public int getMainInventoryCount() // returns the number of books currently available
    {
        return mainInventory.size();
    }
}
