package lk.ijse.repository;

import lk.ijse.entity.Branch;
import lk.ijse.projection.loadBranchName;
import org.hibernate.Session;

import java.util.List;

public interface BranchRepository extends CrudRepository <Branch, Long>{
    void setSession(Session session);
    List<Branch> getAll();

    List<loadBranchName> getbranchName();
}
