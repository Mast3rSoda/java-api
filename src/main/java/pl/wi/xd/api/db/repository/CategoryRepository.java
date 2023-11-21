package pl.wi.xd.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wi.xd.api.db.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}