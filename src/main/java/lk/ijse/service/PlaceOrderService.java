package lk.ijse.service;

import lk.ijse.projection.BookDetails;

import java.util.List;

public interface PlaceOrderService extends SuperService{
     List<BookDetails> getIdsAndTitles();
}
