package kontrolins.lab2weblayer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kontrolins.lab2weblayer.Model.enums.Frequency;
import kontrolins.lab2weblayer.Model.enums.Language;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Periodical extends Publication {
    private int issn;
    private int issueNumber;
    private String editor;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public Periodical(String title, Language language, LocalDate publicationDate, int pageCount, String publisher, String author, String summary, PublicationStatus publicationStatus, int issn, int issueNumber, String editor, Frequency frequency) {
        super(title, language, publicationDate, pageCount, publisher, author, summary, publicationStatus);
        this.issn = issn;
        this.issueNumber = issueNumber;
        this.editor = editor;
        this.frequency = frequency;
    }

    public Periodical(String title, Language language, LocalDate publicationDate, int pageCount, String publisher, String author, String summary, PublicationStatus publicationStatus, Client owner, int issn, int issueNumber, String editor, Frequency frequency) {
        super(pageCount, title, publicationDate, publisher, author, summary, publicationStatus, language, owner);
        this.issn = issn;
        this.issueNumber = issueNumber;
        this.editor = editor;
        this.frequency = frequency;
    }
}
