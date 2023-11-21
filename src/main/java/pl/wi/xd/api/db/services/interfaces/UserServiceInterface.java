package pl.wi.xd.api.db.services.interfaces;

import pl.wi.xd.api.db.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();

    User findById(Long id);

    void delete(Long id);

    User save(User user);

    String getUsername(Long id);
}
