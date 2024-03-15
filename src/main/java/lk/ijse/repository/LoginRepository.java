package lk.ijse.repository;

import lk.ijse.entity.Admin;
import org.hibernate.Session;

public interface LoginRepository extends CrudRepository <Admin, Long>{
    void setSession(Session session);
    Long save(Admin admin);
}
