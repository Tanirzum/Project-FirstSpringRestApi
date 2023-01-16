package com.tanirbergen.FirstProjectRestApi.Model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @Size(min = 3, max = 50, message = "Введите от 3 до 50 букв")
    @NotEmpty
    private String username;
    @Column(name = "password")
    @Size(min = 3, max = 15, message = "Введите от 3 до 50 букв")
    @NotEmpty
    private String password;
    @Column(name = "year")
    @Min(value = 0,message = "Введите правильную цифру")
    @Max(value = 110,message = "Введите до 110 цифр")
    private int year;

    @Column(name = "create_data")
    private LocalDateTime createdAd;
    @Column(name = "update_data")
    private LocalDateTime updatedAd;

    @Column(name = "created_who")
    @Size(min = 3, max = 15, message = "Введите от 3 до 50 букв")
    @NotEmpty
    private String createdWho;

    public Person() {

    }

    public Person(String username, String password, int year) {
        this.username = username;
        this.password = password;
        this.year = year;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDateTime getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(LocalDateTime createdAd) {
        this.createdAd = createdAd;
    }

    public LocalDateTime getUpdatedAd() {
        return updatedAd;
    }

    public void setUpdatedAd(LocalDateTime updatedAd) {
        this.updatedAd = updatedAd;
    }

    public String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(String createdWho) {
        this.createdWho = createdWho;
    }
}
