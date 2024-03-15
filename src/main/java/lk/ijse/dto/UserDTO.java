package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String userName;
    private String password;

    public UserDTO(String name, String email, String phone, String userName, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
    }

    public User toEntity(){
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setUsername(this.userName);
        user.setPassword(this.password);
        return user;
    }
}