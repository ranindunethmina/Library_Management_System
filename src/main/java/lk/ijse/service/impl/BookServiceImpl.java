package lk.ijse.service.impl;

import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookDetails;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.BookService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);

    @Override
    public Long save(BookDTO bookDto){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            Long id = bookRepository.save(bookDto.toEntity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public List<BookDTO> getAll(){
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<Book> allBooks = bookRepository.getAllBooks();
        List<BookDTO> dtoList = new ArrayList<>();
        for (Book book : allBooks){
            dtoList.add(book.toDto());
        }
        return dtoList;
    }

    @Override
    public boolean update(BookDTO bookDto){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
           bookRepository.setSession(session);
           bookRepository.update(bookDto.toEntity());
           transaction.commit();
           session.close();
           return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BookDetails> getIdsAndTitles(){
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookDetails> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public BookDTO get(Long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        return bookRepository.get(id).toDto();
    }

    @Override
    public boolean delete(BookDTO bookDto){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            bookRepository.setSession(session);
            bookRepository.delete(bookDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}