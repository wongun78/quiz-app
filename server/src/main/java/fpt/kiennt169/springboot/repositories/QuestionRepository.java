package fpt.kiennt169.springboot.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fpt.kiennt169.springboot.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID>, JpaSpecificationExecutor<Question> {

    /**
     * Find question by ID with answers eagerly loaded.
     * Uses DISTINCT to prevent duplicate rows from joins.
     */
    @Query("SELECT DISTINCT q FROM Question q " +
           "LEFT JOIN FETCH q.answers " +
           "WHERE q.id = :id AND q.isDeleted = false")
    Optional<Question> findById(@Param("id") UUID id);
 
    /**
     * Find all questions with pagination and specification.
     * Uses DISTINCT to prevent duplicates from Many-to-Many relationships.
     */
    @Query("SELECT DISTINCT q FROM Question q " +
           "LEFT JOIN FETCH q.answers " +
           "WHERE q.isDeleted = false")
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    /**
     * Find all questions for a quiz with answers eagerly loaded.
     * Uses DISTINCT to avoid cartesian product from Many-to-Many + One-to-Many JOINs.
     * 
     * @param quizId the quiz ID
     * @return list of questions with answers loaded
     */
    @Query("SELECT DISTINCT q FROM Question q " +
           "LEFT JOIN FETCH q.answers " +
           "JOIN q.quizzes quiz " +
           "WHERE quiz.id = :quizId " +
           "AND q.isDeleted = false")
    List<Question> findByQuizzesId(@Param("quizId") UUID quizId);
}
