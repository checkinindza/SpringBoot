package kontrolins.lab2weblayer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(unique = true)
    protected String login;
    protected String password;
    protected String name;
    protected String surname;
    protected String email;
    @JsonIgnore
    @OneToMany(mappedBy = "actionUser", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PeriodicRecord> actionUserPeriodicRecords;
    @JsonIgnore
    @OneToMany(mappedBy = "borrowerClient", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PeriodicRecord> borrowerClientPeriodicRecords;

    public User(String login, String password, String name, String surname, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
