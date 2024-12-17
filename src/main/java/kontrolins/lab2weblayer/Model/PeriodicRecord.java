package kontrolins.lab2weblayer.Model;

import jakarta.persistence.*;
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

public class PeriodicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User actionUser;
    @ManyToOne
    private User borrowerClient;
    @ManyToOne
    private Publication publication;
    private LocalDate transactionDate;
    private PublicationStatus status;

    public PeriodicRecord(Publication publication, LocalDate transactionDate, User borrowerClient, PublicationStatus status) {
        this.publication = publication;
        this.transactionDate = transactionDate;
        this.borrowerClient = borrowerClient;
        this.status = status;
    }

    public PeriodicRecord(User user, Publication publication, LocalDate transactionDate, PublicationStatus status) {
        this.actionUser = user;
        this.status = status;
        this.publication = publication;
        this.transactionDate = transactionDate;
    }
}
