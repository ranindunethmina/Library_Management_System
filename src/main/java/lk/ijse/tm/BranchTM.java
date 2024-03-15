package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BranchTM {
    private Long id;
    private String name;
    private String contact;
    private String location;

}