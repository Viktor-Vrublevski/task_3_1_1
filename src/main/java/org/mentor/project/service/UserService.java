package org.mentor.project.service;

import org.mentor.project.model.User;
import org.mentor.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory factory;


    public void save(User user) {
       userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAllBy();
    }

    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void delete(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class,id);
        manager.remove(user);
        manager.getTransaction().commit();
    }
}
