package pl.wi.xd.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wi.xd.api.db.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}