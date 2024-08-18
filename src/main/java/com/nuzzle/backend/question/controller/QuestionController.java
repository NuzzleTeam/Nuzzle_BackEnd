package com.nuzzle.backend.question.controller;

import com.nuzzle.backend.question.domain.Question;
import com.nuzzle.backend.question.dto.AnswerRequest;
import com.nuzzle.backend.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/random-question")
    public ResponseEntity<Question> getRandomQuestion() {
        Question question = questionService.getRandomQuestion();
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    @PostMapping("/answer")
    public ResponseEntity<String> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        try {
            questionService.saveAnswer(answerRequest.getQuestionId(), answerRequest.getAnswer());
            return ResponseEntity.ok("Answer submitted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/answer-status/{questionId}")
    public ResponseEntity<String> getAnswerStatus(@PathVariable Long questionId) {
        boolean isAnswered = questionService.isQuestionAnswered(questionId);
        if (isAnswered) {
            return ResponseEntity.ok("Question has been answered.");
        } else {
            return ResponseEntity.ok("Question has not been answered yet.");
        }
    }

    @GetMapping("/hello")
    public String sayHello() {
        // 요청을 받으면 "Hello, World!" 문자열을 반환
        return "Hello, World!";
    }
}


