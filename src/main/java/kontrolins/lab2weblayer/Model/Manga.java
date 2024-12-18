package kontrolins.lab2weblayer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kontrolins.lab2weblayer.Model.enums.Demographic;
import kontrolins.lab2weblayer.Model.enums.Language;
import kontrolins.lab2weblayer.Model.enums.MangaGenre;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import kontrolins.lab2weblayer.utils.MangaGenreConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Manga extends Publication {
    private int jan;
    private String illustrator;
    private int volumeNumber;
    @Enumerated(EnumType.STRING)
    private Demographic demographic;
    @JsonIgnore
    @Convert(converter = MangaGenreConverter.class)
    @Column(name = "genres", columnDefinition = "varchar(255) default NULL")
    private List<MangaGenre> mangaGenres;
    private boolean isColor;

    public Manga(String title, Language language, LocalDate publicationDate, int pageCount, String publisher, String author, String summary, PublicationStatus publicationStatus, int jan, String illustrator, int volumeNumber, Demographic demographic, List<MangaGenre> mangaGenres, boolean isColor) {
        super(title, language, publicationDate, pageCount, publisher, author, summary, publicationStatus);
        this.jan = jan;
        this.illustrator = illustrator;
        this.volumeNumber = volumeNumber;
        this.demographic = demographic;
        this.mangaGenres = mangaGenres;
        this.isColor = isColor;
    }

    public boolean getIsColor() {
        return this.isColor;
    }

    public void setIsColor(boolean value) {
        this.isColor = value;
    }
}
