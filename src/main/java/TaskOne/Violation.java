package TaskOne;

import java.util.Date;
import java.util.Objects;

public class Violation {

    private Date date;
    private String firstName;
    private String lastName;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Double.compare(violation.fineAmount, fineAmount) == 0 && Objects.equals(date, violation.date) && Objects.equals(firstName, violation.firstName) && Objects.equals(lastName, violation.lastName) && type.equals(violation.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, firstName, lastName, type, fineAmount);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private double fineAmount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
