package lk.ijse.entity;

import lk.ijse.dto.BranchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "library_branch")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "contact" , nullable = false)
    private String contact;

    @Column(name = "location" , nullable = false)
    private String location;


    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public BranchDTO toDto(){
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(this.id);
        branchDTO.setName(this.name);
        branchDTO.setContact(this.contact);
        branchDTO.setLocation(this.location);
        return branchDTO;
    }
}