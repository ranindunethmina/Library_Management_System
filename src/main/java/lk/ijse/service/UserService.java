package lk.ijse.service;

import lk.ijse.dto.UserDTO;
import lk.ijse.projection.UserIds;

import java.util.List;

public interface UserService extends SuperService{
     Long save(UserDTO userDTO) ;
     List<UserDTO> getAll() ;
     List<UserIds> getAllIds();
     UserDTO get(long id);
     void update(UserDTO userDTO);
     boolean delete(UserDTO userDTO);
     boolean isExists(String username);
     UserDTO getCustomerUsingUsername(String username);
}
