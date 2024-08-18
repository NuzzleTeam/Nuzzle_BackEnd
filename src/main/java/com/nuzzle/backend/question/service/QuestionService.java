package com.nuzzle.backend.question.service;

import com.nuzzle.backend.answer.domain.Answer;
import com.nuzzle.backend.answer.repository.AnswerRepository;
import com.nuzzle.backend.question.domain.Question;
import com.nuzzle.backend.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        return questions.isEmpty() ? null : questions.get(new Random().nextInt(questions.size()));
    }

    public boolean saveAnswer(Long questionId, String answerText) {
        if (answerText.length() > 30) {
            throw new IllegalArgumentException("Answer must be 30 characters or less.");
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setAnswer(answerText);

        answerRepository.save(answer);
        return true;
    }

    public boolean isQuestionAnswered(Long questionId) {
        return questionRepository.existsByQuestionId(questionId);
    }
}
