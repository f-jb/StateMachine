package se.kaiserbirch.model;

import java.time.LocalDate;
import java.time.Period;

public class Date implements DateInterface {
    private final Period offsetDate;

    public Date() {
        offsetDate = Period.ZERO;
    }

    public Date(LocalDate localDate) {
        this.offsetDate = Period.between(LocalDate.now(), localDate);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now().plus(offsetDate);
    }
}
