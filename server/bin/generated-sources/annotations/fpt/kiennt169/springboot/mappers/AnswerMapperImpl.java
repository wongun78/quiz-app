package fpt.kiennt169.springboot.mappers;

import fpt.kiennt169.springboot.dtos.answers.AnswerRequestDTO;
import fpt.kiennt169.springboot.dtos.answers.AnswerResponseDTO;
import fpt.kiennt169.springboot.entities.Answer;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T20:24:44+0700",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerResponseDTO toResponseDTO(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        UUID id = null;
        String content = null;
        Boolean isCorrect = null;

        id = answer.getId();
        content = answer.getContent();
        isCorrect = answer.getIsCorrect();

        AnswerResponseDTO answerResponseDTO = new AnswerResponseDTO( id, content, isCorrect );

        return answerResponseDTO;
    }

    @Override
    public Answer toEntity(AnswerRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setContent( requestDTO.content() );
        answer.setIsCorrect( requestDTO.isCorrect() );

        return answer;
    }
}
