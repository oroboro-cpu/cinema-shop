package cinema.project.service;

import cinema.project.exception.AuthenticationException;
import cinema.project.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
