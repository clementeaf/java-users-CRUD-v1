package users.usersAPI.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.usersAPI.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
