package com.codeloon.ems.service;

import com.codeloon.ems.entity.User;
import com.codeloon.ems.model.UserBean;
import com.codeloon.ems.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserBean> getAllUsers() {
        List<UserBean> userBeans = new ArrayList<>();
        try {
            List<User> users = userRepository.findAll();
            UserBean userBean = null;
            for (User user : users) {
                userBean = new UserBean();
                BeanUtils.copyProperties(user, userBean);
                userBeans.add(userBean);
            }
        } catch (Exception ex) {
            log.error("Error occurred while retrieving all system users", ex);
        }
        return userBeans;
    }

    @Override
    public UserBean findByUsername(String userName) {
        UserBean userBean = new UserBean();
        try {
            Optional<User> user = userRepository.findByUsername(userName);
            BeanUtils.copyProperties(user.get(), userBean);
        } catch (Exception ex) {
            log.error("Error occurred while retrieving system user", ex);
        }
        return userBean;
    }
}
