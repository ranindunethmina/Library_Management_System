package lk.ijse.service;

import lk.ijse.dto.BookDTO;
import lk.ijse.projection.BookDetails;

import java.util.List;

public interface BookService extends SuperService{
    Long save(BookDTO bookDto);
    List<BookDTO> getAll();
    boolean update(BookDTO bookDto);
    List<BookDetails> getIdsAndTitles();
    BookDTO get(Long id);

    boolean delete(BookDTO bookDto);
}
