package com.martishyn.processingserver.service.validation;

import com.martishyn.processingserver.domain.Application;
import com.martishyn.processingserver.domain.Status;
import com.martishyn.processingserver.domain.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IncomingApplicationValidationService implements ApplicationValidationService {

    private static final Pattern NAME_VALIDATION_PATTERN = Pattern.compile("[a-zA-Z]+");
    private Matcher matcher;

    @Override
    public boolean isValid(Application application) {
        Student studentFromApplication = application.getStudent();
        if (hasValidId(application.getApplicationId()) && hasValidId(studentFromApplication.getId()) && hasValidName(studentFromApplication.getFirstName()) && hasValidName(studentFromApplication.getLastName()) && hasValidAge(studentFromApplication.getBirthDate())
                && hasValidGradesValues(studentFromApplication.getGrades())) {
            return true;
        }
        return false;
    }

    private boolean hasValidAge(LocalDate dateOfBirth){
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears() >= 18;
    }

    public boolean hasValidId(Long id) {
        return id != null && id > 0;
    }

    private boolean hasValidName(String name) {
        matcher = NAME_VALIDATION_PATTERN.matcher(name);
        return (name != null && !name.isEmpty() && matcher.matches());
    }

    private boolean hasValidEmail(String email) {
        return email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$\n");
    }

    private boolean hasValidGradesValues(List<Integer> grades){
        IntPredicate gradePredicate = (val) -> val < 100 && val > 0;
        return grades.stream()
                .mapToInt(Integer::intValue)
                .allMatch(gradePredicate);
    }

    private boolean hasValidStatus(Status status) {
        return status.getStatus().equals(Status.PENDING.getStatus());
    }
}
