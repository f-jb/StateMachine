package se.kaiserbirch.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Time implements TimeInterface {
    private final Duration offsetTime;

    public Time() {
        this.offsetTime = Duration.ZERO;
    }

    public Time(LocalTime localTime) {
        this.offsetTime = Duration.between(LocalTime.now(), localTime);
    }

    @Override
    public LocalTime getTime() {
        return LocalTime.now().plus(offsetTime).truncatedTo(ChronoUnit.SECONDS);
    }
}
