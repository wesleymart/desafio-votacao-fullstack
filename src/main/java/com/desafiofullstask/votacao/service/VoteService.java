package com.desafiofullstask.votacao.service;

import com.desafiofullstask.votacao.entity.Vote;
import com.desafiofullstask.votacao.repository.VoteRepository;
import com.desafiofullstask.votacao.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public String save(Vote vote) {
        if (ValidationUtil.validateCPF(vote.getAssociatedCpf())) {
            Random random = new Random();
            int randomNumber = random.nextInt(2) + 1;

            if (randomNumber == 1) {
                Optional<Vote> existVote = voteRepository.findByAssociatedCpfAndDiscussId(vote.getAssociatedCpf(), vote.getDiscussId());
                if(existVote.isPresent()){
                    return "CPF_ALREADY_VOTED";
                }else{
                    voteRepository.save(vote);
                    return "ABLE_TO_VOTE";
                }
            } else {
                return "UNABLE_TO_VOTE";
            }
        } else {
            return "CPF_INVALID";
        }
    }

    public Vote findVoteById(Integer id) {
        return voteRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        voteRepository.deleteById(id);
    }
}
