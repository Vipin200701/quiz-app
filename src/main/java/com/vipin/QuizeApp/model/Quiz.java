package com.vipin.QuizeApp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String difficulty;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Question> questions;

    // Getters and setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}
