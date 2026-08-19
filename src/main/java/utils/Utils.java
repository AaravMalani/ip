package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import commands.CommandRegistry;
import exceptions.InvalidDateTimeException;
import exceptions.MissingArgumentException;

public class Utils {
    private static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy @ HH:mm");

    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(dateTime);
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_OUTPUT_FORMAT);
    }
}
