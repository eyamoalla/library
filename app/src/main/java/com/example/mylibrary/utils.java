package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class utils {
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";
    private static utils instance;
    private SharedPreferences sharedPreferences;

  //  private static ArrayList<Book> allBooks;
    //private static ArrayList<Book> alreadyReadBooks;
   // private static ArrayList<Book> wantToReadBooks;
    //private static ArrayList<Book> currentlyReadingBooks;
    //private static ArrayList<Book> favouriteBooks;

    private utils(Context context) {
        sharedPreferences=context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        if (null == getAllBooks()) {

            initData();

        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavouriteBooks()) {
            editor.putString(FAVOURITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }


    }

    private void initData() {
        // add initial Data
       ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q48", "Haruki Murakami", "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1630460042l/11297._SY475_.jpg", "A work of brilliance ", "long description ", 1350));
        books.add(new Book(2, "the fault in our stars ", "john green", "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1660273739i/11870085.jpg", "great", "long desc", 500));
       SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }


    public static utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new utils( context);
            return instance;
        }

    }

    public  ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);


        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);


        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);


        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);


        return books;
    }

    public  ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null),type);


        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if(null!=books){
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        }
        return null ;
    }
    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean AddToCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean AddToFavourite(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if(null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
     public boolean removeFromAlreadyRead(Book book){
       ArrayList<Book> books = getAlreadyReadBooks();
       if(null!= books){
          for(Book b:books){
              if(b.getId()==book.getId()){
                 if( books.remove(b)){
                   Gson gson = new Gson();
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                   editor.remove(ALREADY_READ_BOOKS);
                   editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                   editor.commit();
                   return true ;
                 }
              }

          }
       }
       return false ;
     }
     public boolean removeFromWantToRead (Book book){
         ArrayList<Book> books = getWantToReadBooks();
         if(null!= books){
             for(Book b:books){
                 if(b.getId()==book.getId()){
                     if( books.remove(b)){
                         Gson gson = new Gson();
                         SharedPreferences.Editor editor = sharedPreferences.edit();
                         editor.remove(WANT_TO_READ_BOOKS);
                         editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                         editor.commit();
                         return true ;
                     }
                 }

             }
         }
         return false ;
     }
    public boolean removeFromFavourites (Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if(null!= books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if( books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true ;
                    }
                }

            }
        }
        return false ;
    }
    public boolean removeFromcurrentlyReading (Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if(null!= books){
            for(Book b:books){
                if(b.getId()==book.getId()){
                    if( books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true ;
                    }
                }

            }
        }
        return false ;
    }
}
