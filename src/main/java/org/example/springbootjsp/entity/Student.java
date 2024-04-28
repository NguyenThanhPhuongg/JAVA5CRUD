package org.example.springbootjsp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name is not empty")
    @Size(min = 10 , max = 20 , message = "ten khong duoc qua 20 ki tu")
    @Column(name = "name")
    private String name;

    @Positive(message = "tuoi khong duoc am")
    @NotNull(message = "age not null")
    @Column(name = "age")
    private Integer age;
}
