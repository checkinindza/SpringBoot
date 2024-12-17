package kontrolins.lab2weblayer.repos;

import kontrolins.lab2weblayer.Model.Publication;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepo extends JpaRepository<Publication, Integer> {
    List<Publication> findPublicationByPublicationStatusEquals(PublicationStatus publicationStatus);
}
