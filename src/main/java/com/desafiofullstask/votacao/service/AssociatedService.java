package com.desafiofullstask.votacao.service;

import com.desafiofullstask.votacao.entity.Associated;
import com.desafiofullstask.votacao.repository.AssociatedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociatedService {

    private static final Logger logger = LoggerFactory.getLogger(AssociatedService.class);

    @Autowired
    private AssociatedRepository associatedRepository;

    public Associated findAssociatedById(Integer id) {
        try {
            return associatedRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error finding associated by id: {}", e.getMessage());
            return null;
        }

    }

    public Associated save(Associated associated) {
        try {
            return associatedRepository.save(associated);
        } catch (Exception e) {
            logger.error("Error saving associated: {}", e.getMessage());
            return null;
        }
    }

    public void delete(Integer id) {
        try {
            associatedRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting associated: {}", e.getMessage());
        }

    }
}
