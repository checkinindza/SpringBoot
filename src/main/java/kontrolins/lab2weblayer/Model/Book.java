package kontrolins.lab2weblayer.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kontrolins.lab2weblayer.Model.enums.BookGenre;
import kontrolins.lab2weblayer.Model.enums.Format;
import kontrolins.lab2weblayer.Model.enums.Language;
import kontrolins.lab2weblayer.Model.enums.PublicationStatus;
import kontrolins.lab2weblayer.utils.BookGenreConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book extends Publication {
    private int isbn;
    @JsonIgnore
    @Convert(converter = BookGenreConverter.class)
    @Column(name = "genres", columnDefinition = "varchar(255) default NULL")
    private List<BookGenre> bookGenre;
    @Enumerated(EnumType.STRING)
    private Format format;

    public Book(String title, Language language, LocalDate publicationDate, int pageCount, String publisher, String author, String summary, PublicationStatus publicationStatus, int isbn, Format format, List<BookGenre> bookGenre) {
        super(title, language, publicationDate, pageCount, publisher, author, summary, publicationStatus);
        this.isbn = isbn;
        this.format = format;
        this.bookGenre = bookGenre;
    }

    public Book(String title, Language language, LocalDate publicationDate, int pageCount, String publisher, String author, String summary, PublicationStatus publicationStatus, Client owner, int isbn, Format format, List<BookGenre> bookGenre) {
        super(pageCount, title, publicationDate, publisher, author, summary, publicationStatus, language, owner);
        this.isbn = isbn;
        this.bookGenre = bookGenre;
        this.format = format;
    }

    public List<BookGenre> getBookGenres() {
        return this.bookGenre;
    }
}
