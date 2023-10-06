package se.kaiserbirch.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {
    @Test
    public void getTimeTest() {
        Time time = new Time();
        LocalTime expectedTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        assertEquals(time.getTime(), expectedTime);
    }


}