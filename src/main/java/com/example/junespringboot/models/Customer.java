package com.example.junespringboot.models;

import com.example.junespringboot.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.swing.text.View;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Admin.class})
    private int id;

    @NotEmpty
    @Length(min = 2, max = 20, message = "too short or long name")
    @JsonView({Views.Admin.class, Views.Client.class})
    private String name;

    @JsonView({Views.Admin.class, Views.Client.class})
    private String surname;

    @NotEmpty
    @Email
    @JsonView({Views.Admin.class, Views.Client.class})
    private String email;

    @JsonView({Views.Admin.class})
    private boolean isActivated = false;

    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
