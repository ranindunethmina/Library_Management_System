package lk.ijse.dto;

import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BranchDTO implements Serializable {
    private Long id;
    private String name;
    private String contact;
    private String location;

    public BranchDTO(String name, String contact, String location){
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public Branch toEntity(){
        Branch branch = new Branch();
        branch.setId(this.id);
        branch.setName(this.name);
        branch.setContact(this.contact);
        branch.setLocation(this.location);
        return branch;
    }
}
