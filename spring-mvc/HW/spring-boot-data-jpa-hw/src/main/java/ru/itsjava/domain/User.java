package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fio;
    private Date birthday;
    private Boolean male;

    @JoinColumn(name = "email_id")
    @OneToOne(targetEntity = Email.class, cascade = CascadeType.MERGE)
    private Email email;

    @JoinColumn(name = "pet_id")
    @ManyToOne(targetEntity = Pet.class)
    private Pet pet;
}
