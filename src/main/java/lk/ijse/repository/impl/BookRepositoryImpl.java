package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.projection.BookDetails;
import lk.ijse.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public List<Book> getAllBooks() {
        String sqlQuery = "FROM Book";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<BookDetails> getIdsAndTitles() {
        String sqlQuery = "SELECT new lk.ijse.projection.BookDetails (B.id, B.title) FROM Book AS B";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long save(Book book) {
        return (Long) session.save(book);
    }

    @Override
    public void update(Book book) {
        session.update(book);
    }

    @Override
    public Book get(Long aLong) {
        return session.get(Book.class, aLong);
    }

    @Override
    public void delete(Book book) {
        session.delete(book);
    }

}