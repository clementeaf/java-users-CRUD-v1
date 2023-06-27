package users.usersAPI.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneId;

    @Column(name = "number", unique = true)
    private int number;

    @Column(name = "codCity")
    private int codCity;

    @Column(name = "codCountry")
    private int codCountry;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Phone{");
        sb.append("phoneId=").append(phoneId);
        sb.append(", number=").append(number);
        sb.append(", codCity=").append(codCity);
        sb.append(", codCountry=").append(codCountry);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return phoneId == phone.phoneId && number == phone.number && codCity == phone.codCity && codCountry == phone.codCountry && Objects.equals(user, phone.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId, number, codCity, codCountry, user);
    }
}
