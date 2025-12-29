package fpt.kiennt169.springboot.mappers;

import fpt.kiennt169.springboot.dtos.questions.QuestionResponseDTO;
import fpt.kiennt169.springboot.dtos.quizzes.QuizDetailResponseDTO;
import fpt.kiennt169.springboot.dtos.quizzes.QuizRequestDTO;
import fpt.kiennt169.springboot.dtos.quizzes.QuizResponseDTO;
import fpt.kiennt169.springboot.entities.Quiz;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T20:24:44+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public QuizResponseDTO toResponseDTO(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        String description = null;
        Integer durationMinutes = null;
        Boolean active = null;

        id = quiz.getId();
        title = quiz.getTitle();
        description = quiz.getDescription();
        durationMinutes = quiz.getDurationMinutes();
        active = quiz.getActive();

        Integer totalQuestions = quiz.getQuestions() != null ? quiz.getQuestions().size() : 0;

        QuizResponseDTO quizResponseDTO = new QuizResponseDTO( id, title, description, durationMinutes, active, totalQuestions );

        return quizResponseDTO;
    }

    @Override
    public QuizDetailResponseDTO toDetailResponseDTO(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        UUID id = null;
        String title = null;
        String description = null;
        Integer durationMinutes = null;
        Boolean active = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        id = quiz.getId();
        title = quiz.getTitle();
        description = quiz.getDescription();
        durationMinutes = quiz.getDurationMinutes();
        active = quiz.getActive();
        createdAt = quiz.getCreatedAt();
        updatedAt = quiz.getUpdatedAt();

        List<QuestionResponseDTO> questions = mapQuestionsWithoutQuizzes(quiz.getQuestions());

        QuizDetailResponseDTO quizDetailResponseDTO = new QuizDetailResponseDTO( id, title, description, durationMinutes, active, questions, createdAt, updatedAt );

        return quizDetailResponseDTO;
    }

    @Override
    public Quiz toEntity(QuizRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Quiz quiz = new Quiz();

        quiz.setActive( requestDTO.active() );
        quiz.setDescription( requestDTO.description() );
        quiz.setDurationMinutes( requestDTO.durationMinutes() );
        quiz.setTitle( requestDTO.title() );

        return quiz;
    }

    @Override
    public void updateEntityFromDTO(QuizRequestDTO requestDTO, Quiz quiz) {
        if ( requestDTO == null ) {
            return;
        }

        if ( requestDTO.active() != null ) {
            quiz.setActive( requestDTO.active() );
        }
        if ( requestDTO.description() != null ) {
            quiz.setDescription( requestDTO.description() );
        }
        if ( requestDTO.durationMinutes() != null ) {
            quiz.setDurationMinutes( requestDTO.durationMinutes() );
        }
        if ( requestDTO.title() != null ) {
            quiz.setTitle( requestDTO.title() );
        }
    }
}
