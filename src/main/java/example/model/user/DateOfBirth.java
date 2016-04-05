package example.model.user;

import example.fundamentals.DateText;
import example.model.user.validation.OnRegister;
import example.model.user.validation.OnUpdate;

import javax.validation.constraints.AssertTrue;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Created by Masuda on 2016/04/02.
 */
public class DateOfBirth {

    LocalDate date ;
    String source;

    boolean valid = true;

    public DateOfBirth(String source) {
        this.source = source;

        DateText dateText = new DateText(source);
        try {
            date = dateText.toLocalDate();
        } catch ( DateTimeException exception) {
            valid = false;
        }

        System.out.println("source="+source);
        System.out.println("date="+date);
        System.out.println("valid="+valid);
    }

    @AssertTrue(message = "形式が不正です", groups = {OnRegister.class, OnUpdate.class})
    public boolean isValid() {
        if(source.equals("")) return true;
        return valid;
    }

    @Override
    public String toString() {
        if(valid) return date.toString();
        return source;
    }
}
