package com.desafiofullstask.votacao.repository;

import com.desafiofullstask.votacao.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query(value = """
            SELECT
                count(*)
            FROM
                dp_vote
            WHERE
                discuss_id = :discussId
            AND
                vote = 'sim';""", nativeQuery = true)
    int countVotesYesByDiscussId(@Param("discussId") Integer discussId);

    @Query(value = """
            SELECT
                count(*)
            FROM
                dp_vote
            WHERE
                discuss_id = :discussId
            AND
                vote = 'nao';""", nativeQuery = true)
    int countVotesNoByDiscussId(@Param("discussId") Integer discussId);

    Optional<Vote> findByAssociatedCpfAndDiscussId(String associatedCpf, Integer discussId);
}
