package poly.customer;

import java.time.LocalDate;
import java.util.Objects;

public final class RegularCustomer extends AbstractCustomer {

    private final LocalDate lastOrderDate;

    public RegularCustomer(String id, String name,
                           int bonusPoints, LocalDate lastOrderDate) {

        super(id, name, bonusPoints);
        this.lastOrderDate = lastOrderDate;
    }

    @Override
    public void collectBonusPointsFrom(Order order) {

        if (order.getTotal() >= 100 && calculateIfDurationLessThanMonthSinceLast(order.getDate())) {
            bonusPoints += order.getTotal() * 1.5;
        }
        else if (order.getTotal() >= 100) {
            bonusPoints += order.getTotal();
        }

    }

    public boolean calculateIfDurationLessThanMonthSinceLast(LocalDate date) {
        return lastOrderDate.isAfter(date.minusMonths(1));
    }
    public LocalDate getLocalDate() {
        return this.lastOrderDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        RegularCustomer other = (RegularCustomer) obj;

        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(bonusPoints, other.bonusPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public String asString() {
        String customerAsString = "";
        customerAsString += id + ";" + name + ";" + bonusPoints + ";" + lastOrderDate;
        return customerAsString;
    }

}