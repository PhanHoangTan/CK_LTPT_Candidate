package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {
//   private final long serialVersionUID = 1L;
    @Id
    @Column(name = "candidate_id", nullable = false)
    private String candidate_id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "year_of_birth", nullable = false)
    private int yearOfBirth;
    private String gender;
    private String email;
    private String phone;
    private String description;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToMany(mappedBy = "candidate_id")
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "candidate")
    private Set<Certificate> certificates;

    public Candidate(String candidate_id, String fullName, int yearOfBirth, String gender, String email, String phone, String description, Position position) {
        this.candidate_id = candidate_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.position = position;
    }

    public Candidate() {
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "candidate_id='" + candidate_id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", position=" + position +
                '}';
    }
}