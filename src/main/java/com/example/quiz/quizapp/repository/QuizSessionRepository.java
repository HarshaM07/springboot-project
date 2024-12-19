package com.example.quiz.quizapp.repository;


import com.example.quiz.quizapp.entity.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
    QuizSession findByUserId(Long userId);
}
