package ar.edu.utn.frc.tup.lciii.utils.validations.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator  implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword arg0) {

    }

    /**
     *
     * Todas las clases que hay en este metodo son de passay (LengthRule, UppercaseCharacterRule, etc),
     * estas clases vienen con validaciones ya.
     * PasswordData es un objeto de passay, recibe el string de la password y  devuelve un resultado
     */

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
//                new UppercaseCharacterRule(1),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // new CharacterRule(EnglishCharacterData.LowerCase, 1), no debería agregar esta nueva regla ya que estaría generando problemas para los nuevos usuarios que se creen
//                new DigitCharacterRule(1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
//                new SpecialCharacterRule(1),
                new CharacterRule(EnglishCharacterData.Special, 1),
//                new NumericalSequenceRule(3, false),
//                new AlphabeticalSequenceRule(3, false),
//                new QwertySequenceRule(3, false),
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        String.join(",", validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
