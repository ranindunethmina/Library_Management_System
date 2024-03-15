package lk.ijse.repository;

import lk.ijse.entity.User;
import lk.ijse.projection.UserIds;
import org.hibernate.Session;

import java.util.List;

public interface UserRepository extends CrudRepository <User, Long> {
    void setSession(Session session);
    List<User> getAll();
    List<UserIds> getAllIds();
    Long isExists(String username);
    User getCustomerUsingUsername(String username);
}