package lk.ijse.service.impl;

import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.projection.UserIds;
import lk.ijse.repository.UserRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private Session session;
    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.USER);

    @Override
    public Long save(UserDTO userDTO){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            userRepository.setSession(session);
            Long id = userRepository.save(userDTO.toEntity());
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
    public List<UserDTO> getAll(){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        List<User> allUsers = userRepository.getAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : allUsers){
            dtoList.add(user.toDto());
        }
        return dtoList;
    }

    @Override
    public List<UserIds> getAllIds(){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        return userRepository.getAllIds();
    }

    @Override
    public UserDTO get(long id){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        UserDTO userDTO = userRepository.get(id).toDto();
        session.close();
        return userDTO;

    }

    @Override
    public void update(UserDTO userDTO){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        userRepository.setSession(session);
        userRepository.update(userDTO.toEntity());
        transaction.commit();
        session.close();
    }

    @Override
    public boolean delete(UserDTO userDTO){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        Transaction transaction = session.beginTransaction();
        userRepository.delete(userDTO.toEntity());
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean isExists(String username){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        Long exists = userRepository.isExists(username);
        return exists!=0;
    }

    @Override
    public UserDTO getCustomerUsingUsername(String username){
        session = SessionFactoryConfig.getInstance().getSession();
        userRepository.setSession(session);
        User user = userRepository.getCustomerUsingUsername(username);
        session.close();
        return user.toDto();
    }

}