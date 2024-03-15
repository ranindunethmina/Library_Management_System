package lk.ijse.dto;

import lk.ijse.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookDTO implements Serializable {
    private String title;
    private String author;
    private String genre;
    private Long isbn;
    private int qty;

    public BookDTO(String title, String author, String genre, long isbn, int qty) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.qty = qty;
    }

    public Book toEntity(){
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        book.setIsbn(this.isbn);
        book.setQty(this.qty);
        return book;
    }
}
