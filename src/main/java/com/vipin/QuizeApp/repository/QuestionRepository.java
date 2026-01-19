package com.vipin.QuizeApp.repository;

import com.vipin.QuizeApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Optional: find questions by quiz id, etc.
}
