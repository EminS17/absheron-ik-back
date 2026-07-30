package com.example.absheronikbackend.controller;

import com.example.absheronikbackend.entity.Student;
import com.example.absheronikbackend.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // --- МЕТОД ДЛЯ УДАЛЕНИЯ УЧЕНИКА ПО ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build(); // Возвращает статус 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Возвращает 404, если ID не найден
        }
    }
}