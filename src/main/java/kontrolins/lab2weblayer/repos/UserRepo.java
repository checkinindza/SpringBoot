package kontrolins.lab2weblayer.repos;

import kontrolins.lab2weblayer.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
