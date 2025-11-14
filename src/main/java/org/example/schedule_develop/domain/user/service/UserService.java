package org.example.schedule_develop.domain.user.service;

import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_FOUND_USER;
import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_VALID_LOGIN;
import static org.example.schedule_develop.common.exception.ErrorMessage.NOT_VALID_OWNER;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.entity.User;
import org.example.schedule_develop.common.exception.CustomException;
import org.example.schedule_develop.common.model.SessionUser;
import org.example.schedule_develop.domain.user.model.dto.UserDto;
import org.example.schedule_develop.domain.user.model.request.LoginRequest;
import org.example.schedule_develop.domain.user.model.request.UserCreateRequest;
import org.example.schedule_develop.domain.user.model.request.UserUpdateRequest;
import org.example.schedule_develop.domain.user.model.response.UserCreateResponse;
import org.example.schedule_develop.domain.user.model.response.UserDeleteResponse;
import org.example.schedule_develop.domain.user.model.response.UserReadResponse;
import org.example.schedule_develop.domain.user.model.response.UserUpdateResponse;
import org.example.schedule_develop.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponse createUser(UserCreateRequest request) {

        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        userRepository.save(user);
        UserDto dto = UserDto.from(user);

        return UserCreateResponse.from(dto);
    }

    public UserDeleteResponse deleteUser(long nowLoginUserId, long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        isOwner(nowLoginUserId, user.getId());
        userRepository.delete(user);
        UserDto dto = UserDto.from(user);

        return UserDeleteResponse.from(dto);
    }

    public UserUpdateResponse updateUser(long nowLoginUserId, long userId, UserUpdateRequest request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        isOwner(nowLoginUserId, user.getId());
        user.update(request);
        userRepository.save(user);
        UserDto dto = UserDto.from(user);

        return UserUpdateResponse.from(dto);
    }

    @Transactional(readOnly = true)
    public UserReadResponse getUser(long userId) {

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
        UserDto dto = UserDto.from(user);

        return UserReadResponse.from(dto);
    }

    @Transactional(readOnly = true)
    public SessionUser login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new CustomException(NOT_VALID_LOGIN);
        }
        return new SessionUser(user.getId(), user.getEmail());
    }


    void isOwner(long nowLoginUserId, long userId) {
        
        if (nowLoginUserId != userId) {
            throw new CustomException(NOT_VALID_OWNER);
        }
    }
}
