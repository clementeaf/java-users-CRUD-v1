package users.usersAPI.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "name")
    private String name;

    @CreatedDate
    @LastModifiedDate

    @Column(name = "mail", unique = true)
    private String mail;

    @NotEmpty(message = "La contraseña del usuario es obligatorio")
    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime upDate = LocalDateTime.now();

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Phone> phones = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", upDate=").append(upDate);
        sb.append(", isActive=").append(isActive);
        sb.append(", phones=").append(phones);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(creationDate, user.creationDate) && Objects.equals(upDate, user.upDate) && Objects.equals(isActive, user.isActive) && Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, mail, password, creationDate, upDate, isActive, phones);
    }
}
