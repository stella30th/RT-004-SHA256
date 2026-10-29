package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratedTest_CE015 {

private File dbFile;
private BookManagementDB db;

@Before
public void setUp() throws IOException, SQLException {
    dbFile = File.createTempFile("test_ce015_", ".db");
    db = new BookManagementDB(dbFile.getAbsolutePath());
}

@After
public void tearDown() throws SQLException {
    if (db != null && db.connection != null && !db.connection.isClosed()) {
        db.connection.close();
    }
    if (dbFile != null && dbFile.exists()) {
        dbFile.delete();
    }
}

@Test
public void testAddBookAndSearch() throws SQLException {
    db.addBook("1984", "George Orwell");
    List<BookManagementDB.Book> books = db.searchBooks();

    assertEquals(1, books.size());
    BookManagementDB.Book book = books.get(0);
    assertEquals("1984", book.getTitle());
    assertEquals("George Orwell", book.getAuthor());
    assertEquals(1, book.getAvailable());
    assertTrue(book.toString().contains("1984"));
}

@Test
public void testBorrowAndReturnBook() throws SQLException {
    db.addBook("Brave New World", "Aldous Huxley");
    List<BookManagementDB.Book> books = db.searchBooks();
    int bookId = books.get(0).getId();

    db.borrowBook(bookId);
    List<BookManagementDB.Book> borrowed = db.searchBooks();
    assertEquals(0, borrowed.get(0).getAvailable());

    db.returnBook(bookId);
    List<BookManagementDB.Book> returned = db.searchBooks();
    assertEquals(1, returned.get(0).getAvailable());
}

@Test
public void testRemoveBook() throws SQLException {
    db.addBook("Fahrenheit 451", "Ray Bradbury");
    List<BookManagementDB.Book> books = db.searchBooks();
    assertEquals(1, books.size());

    int bookId = books.get(0).getId();
    db.removeBook(bookId);

    List<BookManagementDB.Book> remaining = db.searchBooks();
    assertEquals(0, remaining.size());
}

@Test
public void testSearchMultipleBooks() throws SQLException {
    db.addBook("Book One", "Author A");
    db.addBook("Book Two", "Author B");

    List<BookManagementDB.Book> books = db.searchBooks();
    assertEquals(2, books.size());
}

}
