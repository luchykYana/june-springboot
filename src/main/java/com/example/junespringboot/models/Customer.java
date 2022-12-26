package com.example.junespringboot.models;

import com.example.junespringboot.models.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Admin.class})
    private int id;

    @NotEmpty
    @Length(min = 3, max = 12, message = "not valid name")
    @JsonView({Views.Admin.class, Views.Client.class})
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
