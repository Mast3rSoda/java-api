package pl.wi.xd.api.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wi.xd.api.db.entity.User;
import pl.wi.xd.api.db.repository.RoleRepository;
import pl.wi.xd.api.db.repository.UserRepository;
import pl.wi.xd.api.db.services.interfaces.UserServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public String getUsername(Long id) {
        return findById(id).getUsername();
    }
}
