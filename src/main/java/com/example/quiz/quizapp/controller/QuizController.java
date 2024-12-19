package com.example.quiz.quizapp.controller;


import com.example.quiz.quizapp.entity.Question;
import com.example.quiz.quizapp.entity.QuizSession;
import com.example.quiz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public QuizSession startNewSession(@RequestParam Long userId) {
        return quizService.startNewSession(userId);
    }

    @GetMapping("/question")
    public Question getRandomQuestion() {
        return quizService.getRandomQuestion();
    }

    @PostMapping("/submit")
    public String submitAnswer(@RequestParam Long userId, @RequestParam Long questionId, @RequestParam String answer) {
        quizService.submitAnswer(userId, questionId, answer);
        return "Answer submitted successfully.";
    }

    @GetMapping("/summary")
    public QuizSession getSessionDetails(@RequestParam Long userId) {
        return quizService.getSessionDetails(userId);
    }
}
