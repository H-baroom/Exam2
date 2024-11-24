package com.example.exam2.Service;

import com.example.exam2.Controller.UserController1;
import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book> books = new ArrayList();
    UserController1 userController1;

    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBooks(Book book){
        books.add(book);
    }

    public boolean updateBook(String ID,Book book){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID().equals(ID)){
                books.set(i,book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String ID){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID().equals(ID)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
    public Book searchByName(String name){
        for (Book b:books){
            if (b.getName().equalsIgnoreCase(name)){
                return b;
            }
        }
        return null;
    }

    public ArrayList<Book> listBookByCategory(String category){
        ArrayList<Book> bookCategory = new ArrayList();
        for (Book b:books){
            if (b.getCategory().equalsIgnoreCase(category)){
                bookCategory.add(b);
            }
        }
        return bookCategory;
    }

    public ArrayList<Book> listBookByNumberOfPage(int number_of_pages){
        ArrayList<Book> bookNumberOfPage = new ArrayList();
        for (Book b:books){
            if (b.getNumber_of_pages() >= number_of_pages){
                bookNumberOfPage.add(b);
            }
        }
        return bookNumberOfPage;
    }

    /*
    public boolean changeStatus(String ID, User user){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID().equals(ID)){
                if (user.getRole().equalsIgnoreCase("libraian")){
                    books.get(i).setAvailable(false);
                    return true;
                }
            }
        }
        return false;
    }
     */

    public int changeStatus(String ID, String userID){
        ArrayList<User> users = userController1.getUsers();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getID().equals(ID)){
                for (int j = 0; j < users.size(); j++) {
                    if (users.get(i).getID().equals(userID)){
                        if (users.get(i).getRole().equalsIgnoreCase("libraian")){
                            books.get(i).setAvailable(false);
                            return 1;
                        }
                    }
                }
                return 2;
            }
        }
        return 4;
    }
}
