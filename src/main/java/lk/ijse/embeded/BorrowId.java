package lk.ijse.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Embeddable
public class BorrowId implements Serializable {
    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "userId")
    private Long userId;
}
