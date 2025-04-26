package com.desafiofullstask.votacao.repository;

import com.desafiofullstask.votacao.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query(value = "SELECT * FROM dp_session WHERE created_time <= :now AND status = 1", nativeQuery = true)
    List<Session> findSessionsToChangeStatus(@Param("now") Timestamp now);
}
