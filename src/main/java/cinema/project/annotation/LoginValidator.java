package cinema.project.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, String> {
    @Override
    public boolean isValid(String login, ConstraintValidatorContext validatorContext) {
        return login != null && (login.length() > 8) && (login.length() < 14);
    }
}
