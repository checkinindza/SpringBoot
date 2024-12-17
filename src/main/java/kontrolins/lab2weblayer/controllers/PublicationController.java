package kontrolins.lab2weblayer.controllers;

import kontrolins.lab2weblayer.Model.Publication;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import kontrolins.lab2weblayer.repos.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationController {
    @Autowired
    private PublicationRepo publicationRepo;

    @GetMapping(value = "'/allAvailablePublications")
    public @ResponseBody
    Iterable<Publication> getAllAvailable() {
        return publicationRepo.findPublicationByPublicationStatusEquals(PublicationStatus.AVAILABLE);
    }
}
