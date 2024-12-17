package kontrolins.lab2weblayer.repos;

import kontrolins.lab2weblayer.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    Client getClientByLoginAndPassword(String login, String password);
    Client getClientByLogin(String login);

    @Query("SELECT c FROM Client c WHERE c.login =?1")
    Client findClientByLoginCustom(String login);
}
