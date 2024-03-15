package lk.ijse.service;

import lk.ijse.dto.BranchDTO;
import lk.ijse.projection.loadBranchName;

import java.util.List;

public interface BranchService extends SuperService{
    Long save(BranchDTO branchDTO);
    boolean update(BranchDTO branchDTO);
    BranchDTO get(long id);
    boolean delete(BranchDTO branchDTO);
    List<BranchDTO> getAll();

    List<loadBranchName> getbranchName();
}
