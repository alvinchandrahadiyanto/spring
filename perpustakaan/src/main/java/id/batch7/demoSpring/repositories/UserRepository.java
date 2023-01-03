package id.batch7.demoSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.batch7.demoSpring.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByName(String nameUser);

  List<User> findByIsDeleted(Boolean status);
}
