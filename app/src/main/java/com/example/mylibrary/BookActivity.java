package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtbookName,txtAuthorName,txtPages,longDesc ;
    private Button btnAddToCurrentlyReading,btnAddToWantToReadList,btnAddToAlreadyReadList,btnAddToFavourite;
    private ImageView bookImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
    //    String longDes="Amazon Kindle Description Generator helps self-publishers create Amazon book descriptions by using a rich text editor, generating the code you should use in KDP for you! Add headings, line breaks, bullet points, bold, italic, underline text and more! The editor only allows the HTML elements supported by Amazon so you can generate and preview your Kindle book description without knowing HTML – error free!";

      //  Book book = new Book(1,"1Q48","Haruki Murakami",
        //        "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1630460042l/11297._SY475_.jpg",
          //      "A work of brilliance ","Amazon Kindle Description Generator helps  " +
            //    "\n" +
              //  "self-publishers create Amazon"+
                //"\n" +
           //     " book descriptions by using a rich text editor, , " +
             //   "\n" +
       //         "generating the code you should use! "+
         //       "\n" +
           //     " , bullet points, bold, italic, underline " +
             //   "\n" +
               // " The editor only ements supported"+
        //        "\n" +
          //      " by Amazon so you can generate and preview your"+
            //    "\n" +
              //  " Kindle book description without knowing "+
                //"\n" +
          //      "Amazon Kindle Description Generator " +
            //    "\n" +
              //  " book descriptions by using a rich text editor, , " +
   //             "\n" +
     //           "generating the code you should use in KDP for yo"+
       //         "\n" +
         //       "line breaks, bullet points, bold, italic, " +
           //     "\n" +
             //   "The editor only allows the HTML elements supported"+
               // "\n" +
                //" by Amazon so you can generate "+
               // "\n" +
              //  " book description without knowing HTML ",1350);
        Intent intent = getIntent();
        if ( null != intent){
            int bookId =intent.getIntExtra(BOOK_ID_KEY,-1);
       if(bookId != -1){
           Book incomingBook = utils.getInstance(this).getBookById(bookId);
           if(null!= incomingBook){
               setData(incomingBook);

               handleAlreadyRead(incomingBook);
               handleWantToReadBooks(incomingBook);
               handleCurrentlyReadingBooks(incomingBook);
               handleFavoriteBooks(incomingBook);
           }
       }
        }

    }
    private void handleWantToReadBooks( final Book book ){
        ArrayList<Book> wantToReadBooks = utils.getInstance(this).getWantToReadBooks();
        boolean existInwantToReadBooks = false ;
        for(Book b:wantToReadBooks){

            if (b.getId() == book.getId()){
                existInwantToReadBooks = true ;
            }
        }
        if (existInwantToReadBooks) {
            btnAddToWantToReadList.setEnabled(false);
        }else{
            btnAddToWantToReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        // navigate the user
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "sthg wrong happened try another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
    //unable and disable button + add book to alreadyread list
    private void handleAlreadyRead (Book book){
        ArrayList<Book> alreadyReadBooks = utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false ;
        for(Book b:alreadyReadBooks){

            if (b.getId() == book.getId()){
                existInAlreadyReadBooks = true ;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyReadList.setEnabled(false);
        }else{
           btnAddToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                       Toast.makeText(BookActivity.this, "book added", Toast.LENGTH_SHORT).show();
                       // navigate the user
                       Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                       startActivity(intent);

                   }else{
                       Toast.makeText(BookActivity.this, "sthg wrong happened try another time", Toast.LENGTH_SHORT).show();
                   }

               }
           });
        }
    }
    private void handleCurrentlyReadingBooks( final Book book ){
        ArrayList<Book> currentlyReading = utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existIncurrentlyReading = false ;
        for(Book b:currentlyReading){

            if (b.getId() == book.getId()){
                existIncurrentlyReading = true ;
            }
        }
        if (existIncurrentlyReading) {
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(utils.getInstance(BookActivity.this).AddToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        // navigate the user
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "sthg wrong happened try another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
    private void handleFavoriteBooks( final Book book ){
        ArrayList<Book> favourite = utils.getInstance(this).getFavouriteBooks();
        boolean existInfavourite = false ;
        for(Book b:favourite){

            if (b.getId() == book.getId()){
                existInfavourite = true ;
            }
        }
        if (existInfavourite) {
            btnAddToFavourite.setEnabled(false);
        }else{
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(utils.getInstance(BookActivity.this).AddToFavourite(book)){
                        Toast.makeText(BookActivity.this, "book added", Toast.LENGTH_SHORT).show();
                        // navigate the user
                        Intent intent = new Intent(BookActivity.this,FavouriteActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(BookActivity.this, "sthg wrong happened try another time", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
    private void setData(Book book ){
        txtbookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        longDesc.setText(book.getLongDesc());
        Glide.with(this )
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }
    private void initViews(){
        txtbookName = findViewById(R.id.txtbookName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPages = findViewById(R.id.txtPages);
        longDesc=findViewById(R.id.longDesc);


        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToReadList = findViewById(R.id.btnAddToWantToReadList);
        btnAddToAlreadyReadList=findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite);


        bookImage = findViewById(R.id.bookImage);
    }
}