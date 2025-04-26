package com.desafiofullstask.votacao.service;

import com.desafiofullstask.votacao.entity.Session;
import com.desafiofullstask.votacao.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;


    public Session findSessionById(Integer id) {
        return sessionRepository.findById(id).orElse(null);
    }

    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    public void delete(Integer id) {
        sessionRepository.deleteById(id);
    }

    public void changeStatus(Integer id, int status) {
        Session session = sessionRepository.findById(id).orElse(null);
        if (session != null) {
            session.setStatus(status);
            sessionRepository.save(session);
        }
    }
}
