package lk.ijse.repository;

import lk.ijse.repository.impl.BookRepositoryImpl;
import lk.ijse.repository.impl.BranchRepositoryImpl;
import lk.ijse.repository.impl.UserRepositoryImpl;
import lk.ijse.repository.impl.LoginRepositoryImpl;

public class RepositoryFactory {
    static RepositoryFactory repositoryFactory;
    private RepositoryFactory(){}

    public static RepositoryFactory getRepositoryFactory(){
        return repositoryFactory == null ? new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        LOGIN, USER, BOOK, BRANCH
    }

    public SuperRepository getRepository(RepositoryTypes repositoryTypes){
        switch (repositoryTypes){
            case LOGIN: return new LoginRepositoryImpl();
            case USER: return new UserRepositoryImpl();
            case BOOK: return new BookRepositoryImpl();
            case BRANCH: return new BranchRepositoryImpl();
            default:return null;
        }
    }

}