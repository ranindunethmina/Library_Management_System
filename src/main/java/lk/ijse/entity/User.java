package lk.ijse.entity;

import lk.ijse.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;

    @Column(name = "u_name" , nullable = false)
    private String name;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "phone" , nullable = false )
    private String phone;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password" , nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "user")
    private List<Branch> brances;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY , mappedBy = "user")
    private List<BorrowDetails> borrowDetails;


    public User(String name, String email, String phone, String username, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
    public UserDTO toDto(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setName(this.name);
        userDTO.setEmail(this.email);
        userDTO.setPhone(this.phone);
        userDTO.setUserName(this.username);
        userDTO.setPassword(this.password);
        return userDTO;
    }
}