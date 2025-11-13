package org.example.schedule_develop.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.schedule_develop.common.entity.User;
import org.example.schedule_develop.domain.user.model.dto.UserDto;
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

        User user = new User(request.getUsername(), request.getEmail());
        userRepository.save(user);
        UserDto dto = UserDto.from(user);

        return UserCreateResponse.from(dto);
    }

    public UserDeleteResponse deleteUser(long userId) {

        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        UserDto dto = UserDto.from(user);

        return UserDeleteResponse.from(dto);
    }

    public UserUpdateResponse updateUser(long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        user.update(request);
        userRepository.save(user);
        UserDto dto = UserDto.from(user);

        return UserUpdateResponse.from(dto);
    }

    @Transactional(readOnly = true)
    public UserReadResponse getUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        UserDto dto = UserDto.from(user);

        return UserReadResponse.from(dto);
    }

}
