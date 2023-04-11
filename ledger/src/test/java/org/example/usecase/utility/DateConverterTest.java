package org.example.usecase.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateConverterTest {
    private DateConverter dateConverter = new DateConverter();
    @Test
    void test_rocToAd_with_valid_input() {
        // Arrange
        String rocDate = "1120315";

        // Act
        String result = dateConverter.rocToAd(rocDate);

        // Assert
        Assertions.assertEquals("20230315", result);
    }

    @Test
    void test_rocToAd_with_non_numeric_string() {
        // Arrange
        String rocDate = "A120315";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> {
            dateConverter.rocToAd(rocDate);
        });
    }

    @Test
    void test_adToRoc_with_valid_input() {
        // Arrange
        String rocDate = "20230315";

        // Act
        String result = dateConverter.adToRoc(rocDate);

        // Assert
        Assertions.assertEquals("1120315", result);
    }

    @Test
    void test_adToRoc_with_non_numeric_string() {
        // Arrange
        String rocDate = "A20230315";

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> {
            dateConverter.adToRoc(rocDate);
        });
    }

    @Test
    void test_adToRoc_with_invalid_date() {
        // Arrange
        String rocDate = "20230332";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dateConverter.adToRoc(rocDate);
        });
    }

    @Test
    void test_adToRoc_with_invalid_month() {
        // Arrange
        String rocDate = "20231315";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dateConverter.adToRoc(rocDate);
        });
    }

    @Test
    void test_adToRoc_with_invalid_year() {
        // Arrange
        String rocDate = "00000315";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dateConverter.adToRoc(rocDate);
        });
    }

    @Test
    void test_adToRoc_with_invalid_date_format() {
        // Arrange
        String rocDate = "2023031";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            dateConverter.adToRoc(rocDate);
        });
    }

}