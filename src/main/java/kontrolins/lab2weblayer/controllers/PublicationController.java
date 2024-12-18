package kontrolins.lab2weblayer.controllers;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import kontrolins.lab2weblayer.Model.Publication;
import kontrolins.lab2weblayer.Model.User;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import kontrolins.lab2weblayer.errors.UserNotFound;
import kontrolins.lab2weblayer.repos.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RestController
public class PublicationController {
    @Autowired
    private PublicationRepo publicationRepo;

    @GetMapping(value = "allAvailablePublications")
    public @ResponseBody
    Iterable<Publication> getAllAvailable() {
        return publicationRepo.findPublicationByPublicationStatusEquals(PublicationStatus.AVAILABLE);
    }

    @GetMapping(value = "getOwnedPublications/{id}")
    public @ResponseBody
    Iterable<Publication> getOwnedPublications(@PathVariable int id) {
        return publicationRepo.findPublicationByOwnerId(id);
    }

    @DeleteMapping(value = "deletePublication/{id}")
    @ResponseBody
    @Transactional
    public String deletePublication(@PathVariable int id) {
        try {
            publicationRepo.deleteById(id);
            return "Success";
        } catch (EmptyResultDataAccessException e) {
            return "Publication not found";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    @PutMapping(value = "setOwnerToNull/{id}")
    public @ResponseBody
    String updatePublication(@PathVariable int id) {
        Publication publication = publicationRepo.findById(id)
                .orElseThrow(() -> new UserNotFound(id));
        publication.setOwner(null);
        publicationRepo.save(publication);
        return "Success";
    }
}
