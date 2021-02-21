package cinema.project.service;

import cinema.project.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
