package com.vipin.QuizeApp.controller;

import com.vipin.QuizeApp.model.Question;
import com.vipin.QuizeApp.model.QuestionForm;
import com.vipin.QuizeApp.model.QuestionListForm;
import com.vipin.QuizeApp.model.Quiz;
import com.vipin.QuizeApp.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuizeController {

    private final QuizService quizService;

    public QuizeController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/quiz/create")
    public String createQuizForm(Model model) {
        return "create_quiz";
    }

    @PostMapping("/quiz/add")
    public String addQuiz(@RequestParam String title,
                          @RequestParam String category,
                          @RequestParam String difficulty,
                          @RequestParam int numQuestions,
                          RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("title", title);
        redirectAttributes.addFlashAttribute("category", category);
        redirectAttributes.addFlashAttribute("difficulty", difficulty);
        redirectAttributes.addFlashAttribute("numQuestions", numQuestions);

        return "redirect:/quiz/add-questions";
    }

    @GetMapping("/quiz/add-questions")
    public String addQuestionsPage(@ModelAttribute("numQuestions") Integer numQuestions,
                                   @ModelAttribute("title") String title,
                                   @ModelAttribute("category") String category,
                                   @ModelAttribute("difficulty") String difficulty,
                                   Model model) {

        if (numQuestions == null) numQuestions = 1;

        QuestionListForm questionListForm = new QuestionListForm();
        List<QuestionForm> questions = new ArrayList<>();
        for (int i = 0; i < numQuestions; i++) {
            questions.add(new QuestionForm());
        }
        questionListForm.setQuestions(questions);

        model.addAttribute("questionListForm", questionListForm);
        model.addAttribute("title", title);
        model.addAttribute("category", category);
        model.addAttribute("difficulty", difficulty);

        return "add_questions";
    }

    @PostMapping("/quiz/save-questions")
    public String saveQuestions(
            @RequestParam String title,
            @RequestParam String category,
            @RequestParam String difficulty,
            @ModelAttribute("questionListForm") QuestionListForm questionListForm,
            Model model) {

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setCategory(category);
        quiz.setDifficulty(difficulty);

        List<Question> questionEntities = new ArrayList<>();

        for (QuestionForm qf : questionListForm.getQuestions()) {
            Question q = new Question();
            q.setQuestionText(qf.getQuestionText());
            q.setOption1(qf.getOption1());
            q.setOption2(qf.getOption2());
            q.setOption3(qf.getOption3());
            q.setOption4(qf.getOption4());
            q.setAnswer(qf.getAnswer());
            q.setQuiz(quiz);

            questionEntities.add(q);
        }

        quiz.setQuestions(questionEntities);
        quizService.saveQuiz(quiz);

        model.addAttribute("title", title);
        model.addAttribute("numQuestions", questionEntities.size());

        return "quiz_success";
    }

    @GetMapping("/quiz/join")
    public String joinQuizPage(Model model) {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        model.addAttribute("quizzes", quizzes);
        return "join_quiz";
    }

    @GetMapping("/quiz/start/{id}")
    public String startQuiz(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null || quiz.getQuestions() == null || quiz.getQuestions().isEmpty()) {
            model.addAttribute("error", "Quiz not found or has no questions");
            return "quiz_error";
        }

        int totalQuestions = quiz.getQuestions().size();
        int totalTimeInMinutes = totalQuestions;

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", quiz.getQuestions());
        model.addAttribute("totalTime", totalTimeInMinutes * 60);

        return "start_quiz";
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(
            @RequestParam Long quizId,
            @RequestParam Map<String, String> answers,
            Model model) {

        Quiz quiz = quizService.getQuizById(quizId);

        int score = 0;
        int total = quiz.getQuestions().size();

        for (Question q : quiz.getQuestions()) {
            String userAnswer = answers.get("answers[" + q.getId() + "]");
            if (userAnswer != null && userAnswer.equals(q.getAnswer())) {
                score++;
            }
        }

        model.addAttribute("score", score);
        model.addAttribute("total", total);
        return "quiz_result";
    }

    // ===== Delete Quiz =====
    @GetMapping("/quiz/delete/{id}")
    public String deleteQuiz(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            quizService.deleteQuizById(id);
            redirectAttributes.addFlashAttribute("success", "Quiz deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete quiz!");
        }
        return "redirect:/quiz/join";
    }
}
