package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TransactionDTO {
    private Long id;
    private User user;
    private Book book;
    private LocalDateTime borrowedDate;
    private LocalDateTime returnDate;
}
