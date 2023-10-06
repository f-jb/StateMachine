package se.kaiserbirch.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    @Test
    public void testCurrentDate() {
        Date date = new Date();
        LocalDate expectedDate = LocalDate.now();
        assertEquals(date.getDate(), expectedDate);
    }
}
