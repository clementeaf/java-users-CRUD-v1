package users.usersAPI.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import users.usersAPI.model.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
