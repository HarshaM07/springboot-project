package com.example.quiz.quizapp.service;
//
//import com.example.quizapp.entity.Question;
//import com.example.quizapp.entity.QuizSession;
//import com.example.quizapp.repository.QuestionRepository;
//import com.example.quizapp.repository.QuizSessionRepository;
import com.example.quiz.quizapp.entity.Question;
import com.example.quiz.quizapp.entity.QuizSession;
import com.example.quiz.quizapp.repository.QuestionRepository;
import com.example.quiz.quizapp.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSession startNewSession(Long userId) {
        QuizSession session = quizSessionRepository.findByUserId(userId);
        if (session == null) {
            session = new QuizSession();
            session.setUserId(userId);
            session.setTotalQuestionsAnswered(0);
            session.setCorrectAnswers(0);
        }
        return quizSessionRepository.save(session);
    }

    public Question getRandomQuestion() {
        long count = questionRepository.count();
        if (count == 0) {
            throw new RuntimeException("No questions available.");
        }
        int index = new Random().nextInt((int) count);
        return questionRepository.findAll().get(index);
    }

    public void submitAnswer(Long userId, Long questionId, String answer) {
        Optional<Question> questionOpt = questionRepository.findById(questionId);
        if (questionOpt.isEmpty()) {
            throw new RuntimeException("Question not found.");
        }

        Question question = questionOpt.get();
        QuizSession session = quizSessionRepository.findByUserId(userId);

        if (session == null) {
            throw new RuntimeException("Session not found. Start a new session.");
        }

        session.setTotalQuestionsAnswered(session.getTotalQuestionsAnswered() + 1);

        if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
            session.setCorrectAnswers(session.getCorrectAnswers() + 1);
        }

        quizSessionRepository.save(session);
    }

    public QuizSession getSessionDetails(Long userId) {
        return quizSessionRepository.findByUserId(userId);
    }
}
