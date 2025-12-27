package fpt.kiennt169.springboot.dtos.submissions;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Schema(description = "Exam result: Score, Total Questions, Pass/Fail")
public record ExamResultResponseDTO(
    
    @Schema(description = "Submission ID", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID submissionId,
    
    @Schema(description = "Total questions in quiz", example = "10")
    Integer totalQuestions,
    
    @Schema(description = "Score achieved", example = "70.0")
    Double score,
    
    @Schema(description = "Pass/Fail status", example = "true")
    Boolean passed
) {}
