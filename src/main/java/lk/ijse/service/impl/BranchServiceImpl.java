package lk.ijse.service.impl;

import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;
import lk.ijse.projection.loadBranchName;
import lk.ijse.repository.BranchRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.BranchService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {
    private Session session;
    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BRANCH);

    @Override
    public Long save(BranchDTO branchDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            branchRepository.setSession(session);
            Long id = branchRepository.save(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public boolean update(BranchDTO branchDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            branchRepository.setSession(session);
            branchRepository.update(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BranchDTO get(long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        branchRepository.setSession(session);
        return branchRepository.get(id).toDto();
    }

    @Override
    public boolean delete(BranchDTO branchDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            branchRepository.setSession(session);
            branchRepository.delete(branchDTO.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BranchDTO> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        branchRepository.setSession(session);
        List<Branch> allUsers = branchRepository.getAll();
        List<BranchDTO> dtoList = new ArrayList<>();
        for (Branch branch : allUsers){
            dtoList.add(branch.toDto());
        }
        return dtoList;
    }

    @Override
    public List<loadBranchName> getbranchName() {
        session = SessionFactoryConfig.getInstance().getSession();
        branchRepository.setSession(session);
        List<loadBranchName> loadBranchNameList =branchRepository.getbranchName();
        return loadBranchNameList;
    }
}