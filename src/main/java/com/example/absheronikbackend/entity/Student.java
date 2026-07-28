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
    private String address; // Адрес (Ünvan)

    // Данные родителей
    private String fatherName;
    private String motherName;
    private String fatherPhone;
    private String motherPhone;
    private Double fatherHeight;
    private Double motherHeight;

    // Конструктор без ID (для удобного создания объекта)
    public Student(String firstName, String lastName, Double height, Double weight,
                   String school, String studentClass, String studentPhone, String address,
                   String fatherName, String motherName, String fatherPhone,
                   String motherPhone, Double fatherHeight, Double motherHeight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.school = school;
        this.studentClass = studentClass;
        this.studentPhone = studentPhone;
        this.address = address;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.fatherPhone = fatherPhone;
        this.motherPhone = motherPhone;
        this.fatherHeight = fatherHeight;
        this.motherHeight = motherHeight;
    }
}