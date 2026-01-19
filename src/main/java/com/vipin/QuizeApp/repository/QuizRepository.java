package com.vipin.QuizeApp.repository;

import com.vipin.QuizeApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // JpaRepository already provides findAll(), so no extra method needed
}

