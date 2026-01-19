package com.vipin.QuizeApp.service.impl;

import com.vipin.QuizeApp.model.Quiz;
import com.vipin.QuizeApp.repository.QuizRepository;
import com.vipin.QuizeApp.service.QuizService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    @Transactional
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }
}
