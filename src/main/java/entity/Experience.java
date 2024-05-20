package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "experience")
public class Experience implements java.io.Serializable{
//    private final long serialVersionUID = 1L;

    @Id
    @Column(name = "company_name", nullable = false)
    private String company_name;
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
    private String description;

    @Id
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position_id;

    public Experience(String company_name, LocalDate fromDate, LocalDate toDate, String description, Candidate candidate_id, Position position_id) {
        this.company_name = company_name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
        this.candidate_id = candidate_id;
        this.position_id = position_id;
    }

    public Experience() {
    }

    @Override
    public String toString() {
        return "Experience{" +
                "company_name='" + company_name + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", description='" + description + '\'' +
                ", candidate_id=" + candidate_id +
                ", position_id=" + position_id +
                '}';
    }
}