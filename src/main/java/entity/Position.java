package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position implements Serializable{
//    private final long serialVersionUID = 1L;

    @Id
    @Column(name = "position_id", nullable = false)
    private String position_id;
    private String name;
    private String description;
    private double salary;
    private int number;


    public Position(String position_id, String name, String description, double salary, int number) {
        this.position_id = position_id;
        this.name = name;
        this.description = description;
        this.salary = salary;
        this.number = number;
    }

    public Position() {
    }

    public Position(String position_id) {
        this.position_id = position_id;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position_id='" + position_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", number=" + number +
                '}';
    }
}