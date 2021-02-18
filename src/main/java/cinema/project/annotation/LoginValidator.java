package cinema.project.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements
        ConstraintValidator<LoginConstraint, String> {
    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null && (contactField.length() > 8) && (contactField.length() < 14);
    }
}
