package fr.diginamic.aqiprojectbackend.service.account;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.account.in.UserStatusDtoIn;
import fr.diginamic.aqiprojectbackend.dto.account.out.UserStatusDtoOut;
import fr.diginamic.aqiprojectbackend.entity.account.UserAccount;
import fr.diginamic.aqiprojectbackend.entity.account.UserStatus;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.account.UserAccountRepository;
import fr.diginamic.aqiprojectbackend.repository.account.UserStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.utils.Dtos.buildHttpStatusDtoOut;

/** User status service */
@Service
@Validated
public class UserStatusService {
    /** User status repository */
    private final UserStatusRepository userStatusRepository;
    /** User account repository */
    private final UserAccountRepository userAccountRepository;

    /**
     * Constructor with parameters.
     * @param userAccountRepository User account repository
     */
    public UserStatusService(UserStatusRepository userStatusRepository,
                             UserAccountRepository userAccountRepository) {
        this.userStatusRepository = userStatusRepository;
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * Create user status
     * @param body HTTP request body (user status)
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createUserStatus(UserStatusDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            userStatusRepository.save(buildUserStatusFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Read user status
     * @param id User status identifier
     * @return HTTP response (user status)
     */
    public ResponseEntity<UserStatusDtoOut> readUserStatus(int id){
        final UserStatus userStatus =
                userStatusRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final UserStatusDtoOut userStatusDtoOut =
                buildUserStatusDtoOutFrom(userStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userStatusDtoOut);
    }

    /**
     * Update user status
     * @param id User status identifier
     * @param body HTTP request body (user status)
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateUserStatus(int id, UserStatusDtoIn body, String path){
        final UserStatus userStatus = userStatusRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userStatus.setLabel(body.label());
        userStatus.setExplanation(body.explanation());
        userStatus.setMemo(body.memo());
        userStatus.setBeginDate(body.beginDate());
        userStatus.setEndDate(body.endDate());
        final List<UserAccount> userAccounts =
                userAccountRepository.findAllById(body.userAccountIds());
        userStatus.setUserAccounts(userAccounts);
        HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * Delete user status
     * @param id User status identifier
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    deleteUserStatus(int id, String path){
        final UserStatus userStatus = userStatusRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userStatusRepository.delete(userStatus);
        HttpStatus httpStatus = HttpStatus.OK;
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(buildHttpStatusDtoOut(httpStatus, path));
    }

    /**
     * List user statuses
     * @return HTTP response (user statuses)
     */
    public ResponseEntity<List<UserStatusDtoOut>> listUserStatuses(){
        final List<UserStatus> userStatuses =
                userStatusRepository.findAll();
        final List<UserStatusDtoOut> userStatusDtoOutList = userStatuses
                .stream()
                .map(this::buildUserStatusDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userStatusDtoOutList);
    }

    /**
     * Build user status from HTTP request body (user status)
     * @param body HTTP request body (user status)
     * @return User status
     */
    private UserStatus buildUserStatusFrom(UserStatusDtoIn body){
        final List<UserAccount> userAccounts =
                userAccountRepository.findAllById(body.userAccountIds());
        return new UserStatus(body.label(),
                body.explanation(),
                body.memo(),
                body.beginDate(),
                body.endDate(),
                userAccounts);
    }

    /**
     * Build userStatus as HTTP response from userStatus
     * @param userStatus UserStatus
     * @return HTTP response (userStatus)
     */
    private UserStatusDtoOut buildUserStatusDtoOutFrom(UserStatus userStatus){
        final List<Integer> userAccountIds = userStatus
                .getUserAccounts()
                .stream()
                .map(UserAccount::getId)
                .toList();
        return new UserStatusDtoOut(userStatus.getId(),
                userStatus.getLabel(),
                userStatus.getExplanation(),
                userStatus.getMemo(),
                userStatus.getBeginDate(),
                userStatus.getEndDate(),
                userAccountIds);
    }
}
