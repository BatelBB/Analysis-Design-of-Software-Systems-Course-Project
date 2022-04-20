package groupk.workers.data;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Employee {
    public static class WeeklyShift {
        public enum Day {
            Sunday,
            Monday,
            Tuesday,
            Wednesday,
            Thursday,
            Friday,
            Saturday
        }
        public enum Type {
            Morning,
            Evening
        }
        public Day day;
        public Type type;

        public WeeklyShift(int dayInt, int typeInt){
            day = Day.values()[dayInt];
            type = Type.values()[typeInt];
        }
        public Day getDay() {
            return day;
        }

        public Type getType() {
            return type;
        }
    }

    private String name;
    private String id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private BankAccount account;
    private WorkingConditions conditions;
    private Set<WeeklyShift> availableShifts;
    public enum Role{
        Logisitcs,
        HumanResources,
        Stocker,
        Cashier,
        LogisticsManager,
        Driver
    };
    private Role role;

    public Employee(String name, String id, String bank, int bankID, int bankBranch,
                    Date employmentStart, int salaryPerHour, int sickDaysUsed, int vacationDaysUsed, Role role){
        this.name = name;
        this.id = id;
        account = new BankAccount(bank, bankID, bankBranch);
        conditions = new WorkingConditions(employmentStart, salaryPerHour, sickDaysUsed, vacationDaysUsed);
        this.role = role;
        availableShifts = new HashSet<>();
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public BankAccount getAccount() { return account; }

    public WorkingConditions getConditions() { return conditions; }

    public Set<WeeklyShift> getAvailableShifts() { return availableShifts; }

    public Role getRole() { return role;}

    public void setAvailableShifts(Set<groupk.workers.service.dto.Employee.WeeklyShift> shiftPreferences) {
        for (groupk.workers.service.dto.Employee.WeeklyShift shift:shiftPreferences) {
            availableShifts.add(new WeeklyShift(shift.day.ordinal(), shift.type.ordinal()));
        }
    }
}
