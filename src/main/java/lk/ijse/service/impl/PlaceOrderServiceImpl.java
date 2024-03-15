package lk.ijse.service.impl;

import lk.ijse.projection.BookDetails;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.PlaceOrderService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    @Override
    public List<BookDetails> getIdsAndTitles(){
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookDetails> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }
}