package lk.ijse.entity;

import lk.ijse.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long isbn;

    @Column(name = "book_title",nullable = false)
    private String title;

    @Column(name = "author" , nullable = false)
    private String author;

    @Column(name = "genre" , nullable = false)
    private String genre;

    @Column
    private int qty;

//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "book")
//    private List<BorrowDetails> borrowDetails;

    public BookDTO toDto(){
        BookDTO bookDto = new BookDTO();
        bookDto.setIsbn(this.isbn);
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);
        bookDto.setQty(this.qty);
        return bookDto;
    }
}