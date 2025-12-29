package fpt.kiennt169.springboot.mappers;

import fpt.kiennt169.springboot.dtos.answers.AnswerRequestDTO;
import fpt.kiennt169.springboot.dtos.answers.AnswerResponseDTO;
import fpt.kiennt169.springboot.dtos.questions.QuestionRequestDTO;
import fpt.kiennt169.springboot.dtos.questions.QuestionResponseDTO;
import fpt.kiennt169.springboot.entities.Answer;
import fpt.kiennt169.springboot.entities.Question;
import fpt.kiennt169.springboot.enums.QuestionTypeEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T20:24:44+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public QuestionResponseDTO toResponseDTO(Question question) {
        if ( question == null ) {
            return null;
        }

        UUID id = null;
        String content = null;
        QuestionTypeEnum type = null;
        Integer score = null;
        List<AnswerResponseDTO> answers = null;

        id = question.getId();
        content = question.getContent();
        type = question.getType();
        score = question.getScore();
        answers = answerListToAnswerResponseDTOList( question.getAnswers() );

        List<QuestionResponseDTO.QuizInfoDTO> quizzes = mapQuizzesToDTO(question.getQuizzes());

        QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO( id, content, type, score, quizzes, answers );

        return questionResponseDTO;
    }

    @Override
    public Question toEntity(QuestionRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Question question = new Question();

        question.setContent( requestDTO.content() );
        question.setScore( requestDTO.score() );
        question.setType( requestDTO.type() );

        return question;
    }

    @Override
    public void updateEntityFromDTO(QuestionRequestDTO requestDTO, Question question) {
        if ( requestDTO == null ) {
            return;
        }

        if ( question.getAnswers() != null ) {
            List<Answer> list = answerRequestDTOListToAnswerList( requestDTO.answers() );
            if ( list != null ) {
                question.getAnswers().clear();
                question.getAnswers().addAll( list );
            }
        }
        else {
            List<Answer> list = answerRequestDTOListToAnswerList( requestDTO.answers() );
            if ( list != null ) {
                question.setAnswers( list );
            }
        }
        if ( requestDTO.content() != null ) {
            question.setContent( requestDTO.content() );
        }
        if ( requestDTO.score() != null ) {
            question.setScore( requestDTO.score() );
        }
        if ( requestDTO.type() != null ) {
            question.setType( requestDTO.type() );
        }
    }

    protected List<AnswerResponseDTO> answerListToAnswerResponseDTOList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerResponseDTO> list1 = new ArrayList<AnswerResponseDTO>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerMapper.toResponseDTO( answer ) );
        }

        return list1;
    }

    protected List<Answer> answerRequestDTOListToAnswerList(List<AnswerRequestDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Answer> list1 = new ArrayList<Answer>( list.size() );
        for ( AnswerRequestDTO answerRequestDTO : list ) {
            list1.add( answerMapper.toEntity( answerRequestDTO ) );
        }

        return list1;
    }
}
