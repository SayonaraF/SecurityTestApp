package ru.broyaka.SecurityTestApp.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "test1")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от двух до ста символов длиной")
    private String userName;
    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
}
