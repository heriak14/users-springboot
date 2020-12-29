package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserService implements UserService {
    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

//    private final UserRepository userRepository;
//
//    @Autowired
//    public JpaUserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User findById(Long id){
//        return userRepository.getOne(id);
//    }
//
//    public List<User> findAll(){
//        return userRepository.findAll();
//    }
//
//    public User saveUser(User user){
//        return userRepository.save(user);
//    }
//
//    public void deleteById(Long id){
//        userRepository.deleteById(id);
//    }
}
