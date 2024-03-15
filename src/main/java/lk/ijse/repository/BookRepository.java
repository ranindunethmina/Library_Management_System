package lk.ijse.repository;

import lk.ijse.entity.Book;
import lk.ijse.projection.BookDetails;
import org.hibernate.Session;

import java.util.List;

public interface BookRepository extends CrudRepository <Book, Long>{
    void setSession(Session session);
    List<Book> getAllBooks();
    List<BookDetails> getIdsAndTitles();
}
