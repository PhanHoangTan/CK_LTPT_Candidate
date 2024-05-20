package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "certificates")
public class Certificate implements java.io.Serializable{
//    private final long serialVersionUID = 1L;

    @Id
    @Column(name = "certificate_id", nullable = false)
    private String certificate_id;

    private String name;
    private String organization;
    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    public Certificate(String certificate_id, String name, String organization, LocalDate issueDate, Candidate candidate) {
        this.certificate_id = certificate_id;
        this.name = name;
        this.organization = organization;
        this.issueDate = issueDate;
        this.candidate = candidate;
    }

    public Certificate() {
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificate_id='" + certificate_id + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", issueDate=" + issueDate +
                ", candidate=" + candidate +
                '}';
    }
}