package com.desafiofullstask.votacao.controller;


import com.desafiofullstask.votacao.entity.Session;
import com.desafiofullstask.votacao.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/{id}")
    public ResponseEntity<Session> get(
            @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(sessionService.findSessionById(id));
    }

    @PostMapping
    public ResponseEntity<Session> save(
            @RequestBody Session session
    ) {
        return ResponseEntity.ok().body(sessionService.save(session));
    }

    @PutMapping
    public ResponseEntity<Session> update(
            @RequestBody Session session
    ) {
        return ResponseEntity.ok().body(sessionService.save(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Integer id
    ) {
        sessionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
