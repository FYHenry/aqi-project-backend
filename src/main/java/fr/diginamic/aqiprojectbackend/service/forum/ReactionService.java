package fr.diginamic.aqiprojectbackend.service.forum;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.forum.in.ReactionDtoIn;
import fr.diginamic.aqiprojectbackend.dto.forum.out.ReactionDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.forum.Message;
import fr.diginamic.aqiprojectbackend.entity.forum.Reaction;
import fr.diginamic.aqiprojectbackend.entity.forum.ReactionType;
import fr.diginamic.aqiprojectbackend.exception.BadRequestException;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.MessageRepository;
import fr.diginamic.aqiprojectbackend.repository.forum.ReactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

@Service
@Validated
public class ReactionService {
    /** Reaction repository */
    private final ReactionRepository reactionRepository;
    /** Message repository */
    private final MessageRepository messageRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor with parameters.
     * @param reactionRepository Reaction repository
     * @param messageRepository Message repository
     * @param userAccountRepository User account repository
     */
    public ReactionService(ReactionRepository reactionRepository,
                           MessageRepository messageRepository,
                           UserAccountRepository userAccountRepository) {
        this.reactionRepository = reactionRepository;
        this.messageRepository = messageRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Create reaction
     * @param body HTTP request body (reaction)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createReaction(ReactionDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            reactionRepository.save(buildReactionFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }

    /**
     * Read reaction
     * @param id Reaction identifier
     * @return HTTP response (reaction)
     */
    public ResponseEntity<ReactionDtoOut> readReaction(int id){
        final Reaction reaction =
                reactionRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final ReactionDtoOut reactionDtoOut =
                buildReactionDtoOutFrom(reaction);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reactionDtoOut);
    }

    /**
     * Update reaction
     * @param id Reaction identifier
     * @param body HTTP request body (reaction)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateReaction(int id, ReactionDtoIn body, String path){
        final Reaction reaction = reactionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        final Message message = messageRepository
                .findById(body.messageId())
                .orElseThrow(EntityNotFoundException::new);
        reaction.setMessage(message);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccount())
                .orElseThrow(EntityNotFoundException::new);
        reaction.setUserAccount(userAccount);
        ReactionType reactionType;
        if(body.reactionType().contentEquals("ZERO")){
            reactionType = ReactionType.ZERO;
        } else if (body.reactionType().contentEquals("MINUS_ONE")) {
            reactionType = ReactionType.MINUS_ONE;
        } else if (body.reactionType().contentEquals("PLUS_ONE")) {
            reactionType = ReactionType.PLUS_ONE;
        } else {
            throw new BadRequestException();
        }
        reaction.setReactionType(reactionType);
        reactionRepository.save(reaction);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete reaction
     * @param id Reaction identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteReaction(int id, String path){
        final Reaction reaction = reactionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        reactionRepository.delete(reaction);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List reactions
     * @return HTTP response (reactions)
     */
    public ResponseEntity<List<ReactionDtoOut>> listReactions(){
        final List<Reaction> reactions =
                reactionRepository.findAll();
        final List<ReactionDtoOut> reactionDtoOutList = reactions
                .stream()
                .map(this::buildReactionDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(reactionDtoOutList);
    }

    /**
     * Build reaction from HTTP request body (reaction)
     * @param body HTTP request body (reaction)
     * @return User account
     */
    private Reaction buildReactionFrom(ReactionDtoIn body) {
        final Message message = messageRepository
                .findById(body.messageId())
                .orElseThrow(EntityNotFoundException::new);
        final UserAccount userAccount = userAccountRepository
                .findById(body.userAccount())
                .orElseThrow(EntityNotFoundException::new);
        ReactionType reactionType;
        if(body.reactionType().contentEquals("ZERO")){
            reactionType = ReactionType.ZERO;
        } else if (body.reactionType().contentEquals("MINUS_ONE")) {
            reactionType = ReactionType.MINUS_ONE;
        } else if (body.reactionType().contentEquals("PLUS_ONE")) {
            reactionType = ReactionType.PLUS_ONE;
        } else {
            throw new BadRequestException();
        }
        return new Reaction(message, userAccount, reactionType);
    }

    /**
     * Build reaction as HTTP response from reaction
     * @param reaction reaction
     * @return HTTP response (reaction)
     */
    private ReactionDtoOut
    buildReactionDtoOutFrom(Reaction reaction){
        return new ReactionDtoOut(reaction.getId(),
                reaction.getMessage().getId(),
                reaction.getUserAccount().getId(),
                reaction.getReactionType().toString());
    }
}
