package com.vipin.QuizeApp.service;

import com.vipin.QuizeApp.model.Quiz;
import java.util.List;

public interface QuizService {
    Quiz saveQuiz(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id);
    void deleteQuizById(Long id); // new method
}
