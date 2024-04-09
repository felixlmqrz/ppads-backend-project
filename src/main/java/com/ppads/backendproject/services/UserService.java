package com.ppads.backendproject.services;

import com.ppads.backendproject.models.User;
import com.ppads.backendproject.models.UserLogin;
import com.ppads.backendproject.repositories.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senhaCriptografada = encoder.encode(user.getPassword());
        user.setPassword(senhaCriptografada);
        return userRepository.save(user);
    }

    private String passwordCrypt(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(password);
    }

    public Optional<User> userUpdate(User user) {

        if (userRepository.findById(user.getId()).isPresent()) {

            Optional<User> findUser = userRepository.findByEmail(user.getEmail());

            if ((findUser.isPresent()) && (findUser.get().getId() != user.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already registered", null);

            user.setPassword(passwordCrypt(user.getPassword()));

            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public Optional<UserLogin> login(Optional<UserLogin> userLogin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> usuarioPresente = userRepository.findByEmail(userLogin.get().getEmail());

        if (usuarioPresente.isPresent()) {
            if (encoder.matches(userLogin.get().getPassword(), usuarioPresente.get().getPassword())) {
                String auth = userLogin.get().getEmail() + ":" + userLogin.get().getPassword();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);

                userLogin.get().setToken(authHeader);
                userLogin.get().setName(usuarioPresente.get().getName());
                userLogin.get().setPassword(usuarioPresente.get().getPassword());

                return userLogin;
            }
        }
        return null;
    }

}
