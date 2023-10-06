package se.kaiserbirch.model;

import java.time.LocalDate;
import java.time.Period;

public class Date implements DateInterface {
    private final Period period;
    public Date(){
        period = Period.ZERO;
    }
    public Date(LocalDate localDate){
        this.period = Period.between(LocalDate.now(), localDate);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now().plus(period);
    }
}
