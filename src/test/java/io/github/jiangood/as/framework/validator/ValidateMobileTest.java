package io.github.jiangood.as.framework.validator;

import jakarta.validation.Payload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidateMobileTest {

    @Test
    public void testValidateMobileMyValidatorWithValidMobile() {
        ValidateMobile.MyValidator validator = new ValidateMobile.MyValidator();

        // Valid mobile numbers
        assertTrue(validator.isValid("13812345678", null));
        assertTrue(validator.isValid("15912345678", null));
        assertTrue(validator.isValid("18812345678", null));
        assertTrue(validator.isValid("17712345678", null));
        assertTrue(validator.isValid("13012345678", null));
    }

    @Test
    public void testValidateMobileMyValidatorWithInvalidMobile() {
        ValidateMobile.MyValidator validator = new ValidateMobile.MyValidator();

        // Invalid mobile numbers
        assertFalse(validator.isValid("12345678901", null)); // Doesn't match mobile pattern
        assertFalse(validator.isValid("1381234567", null));  // Too short
        assertFalse(validator.isValid("138123456789", null)); // Too long
        assertFalse(validator.isValid("abcdefg1234", null));  // Contains letters
        assertFalse(validator.isValid("1381234567a", null)); // Contains letter
    }

    @Test
    public void testValidateMobileMyValidatorWithNullAndEmpty() {
        ValidateMobile.MyValidator validator = new ValidateMobile.MyValidator();

        // Null and empty should be valid (as per the implementation)
        assertTrue(validator.isValid(null, null));
        assertTrue(validator.isValid("", null));
    }


}
