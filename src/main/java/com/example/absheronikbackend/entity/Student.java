package com.example.absheronikbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Данные ученика
    private String firstName;
    private String lastName;
    private Double height;
    private Double weight;
    private String school;
    private String studentClass;
    private String studentPhone;
    private String address; // <--- Новое поле: Адрес (Ünvan)

    // Данные родителей
    private String fatherName;
    private String motherName;
    private String fatherPhone;
    private String motherPhone;
    private Double fatherHeight;
    private Double motherHeight;
}