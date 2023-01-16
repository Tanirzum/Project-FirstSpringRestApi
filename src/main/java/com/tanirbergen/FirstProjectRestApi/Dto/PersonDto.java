package com.tanirbergen.FirstProjectRestApi.Dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDto {

    @Size(min = 3, max = 50, message = "Введите от 3 до 50 букв")
    @NotEmpty
    private String username;

    @Size(min = 3, max = 15, message = "Введите от 3 до 50 букв")
    @NotEmpty
    private String password;

    @Min(value = 0,message = "Введите правильную цифру")
    @Max(value = 110,message = "Введите до 110 цифр")
    private int year;
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
}
