package org.example.softwarefinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salesman {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private double amount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dot;
    @Enumerated(EnumType.STRING)
    private ItemType item;
    private String name;

    public Salesman(double amount, LocalDate dot, ItemType item, String name) {
        this.amount = amount;
        this.dot = dot;
        this.item = item;
        this.name = name;
    }


}
