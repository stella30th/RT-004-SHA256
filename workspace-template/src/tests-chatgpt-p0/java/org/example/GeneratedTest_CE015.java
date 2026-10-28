package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE015 {
    @Test public void behavioralCase1() throws Exception {
        BookManagementDB.Book b=new BookManagementDB.Book(7,"Title","Author",1); assertEquals(7,b.getId()); assertEquals("Title",b.getTitle()); assertEquals("Author",b.getAuthor()); assertEquals(1,b.getAvailable());
    }
}
