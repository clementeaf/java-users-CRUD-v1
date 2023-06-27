package users.usersAPI.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import users.usersAPI.model.entity.Phone;
import users.usersAPI.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private int userId;

    @NotEmpty(message = "Name of user is mandatory")
    private String name;

    @NotEmpty(message = "Mail of user is mandatory")
    private String mail;

    @NotEmpty(message = "Password of user is mandatory")
    private String password;

    private LocalDateTime creationDate;

    private LocalDateTime upDate;

    private Boolean isActive;

    @Valid
    private List<Phone> phones = new ArrayList<>();

    public static UserRequest mapToDto(User entity) {
        UserRequest dto = new UserRequest();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setMail(entity.getMail());
        dto.setPassword(entity.getPassword());
        dto.setCreationDate(entity.getCreationDate());
        dto.setUpDate(entity.getUpDate());
        dto.setIsActive(entity.getIsActive());
        dto.setPhones(entity.getPhones());

        return dto;
    }

    public static User mapToEntity(UserRequest dto){
        User userEntity = new User();
        userEntity.setUserId(dto.getUserId());
        userEntity.setName(dto.getName());
        userEntity.setMail(dto.getMail());
        userEntity.setPassword(dto.getPassword());
        userEntity.setCreationDate(dto.getCreationDate());
        userEntity.setUpDate(dto.getUpDate());
        userEntity.setIsActive(dto.getIsActive());
        userEntity.setPhones(dto.getPhones());

        return userEntity;
    }

    @Override
    public String toString() {
        return "UserRequest{" + "userId=" + userId +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", upDate=" + upDate +
                ", isActive=" + isActive +
                ", phones=" + phones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return userId == that.userId && Objects.equals(name, that.name) && Objects.equals(mail, that.mail) && Objects.equals(password, that.password) && Objects.equals(creationDate, that.creationDate) && Objects.equals(upDate, that.upDate) && Objects.equals(isActive, that.isActive) && Objects.equals(phones, that.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, mail, password, creationDate, upDate, isActive, phones);
    }
}
