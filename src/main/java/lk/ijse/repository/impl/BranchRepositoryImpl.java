package lk.ijse.repository.impl;

import lk.ijse.entity.Branch;
import lk.ijse.projection.loadBranchName;
import lk.ijse.repository.BranchRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public List<Branch> getAll() {
        String sqlQuery = "FROM Branch";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<loadBranchName> getbranchName() {
        String sqlQuery = "SELECT new lk.ijse.projection.loadBranchName (B.name) FROM Branch AS B";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long save(Branch branch) {
        return (Long) session.save(branch);
    }

    @Override
    public void update(Branch branch) {
        session.update(branch);
    }

    @Override
    public Branch get(Long id) {
        return session.get(Branch.class, id);
    }

    @Override
    public void delete(Branch branch) {
        session.delete(branch);
    }

}