package lk.ijse.entity;

import lk.ijse.embeded.BorrowId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "borrow_detail")
public class BorrowDetails {
    @EmbeddedId
    private BorrowId borrowId;

    @Column
    private int qty;

    @ManyToOne
    @JoinColumn(name = "uId",insertable = false,updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bId",insertable = false,updatable = false)
    private Book book;

    @CreationTimestamp
    private Timestamp timestamp;
}
