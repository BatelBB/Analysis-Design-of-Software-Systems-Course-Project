package groupk.shared.presentation.command;

import groupk.shared.presentation.CommandRunner;
import groupk.shared.service.Response;
import groupk.shared.service.dto.Employee;

public class AddShiftPreference implements Command {
    @Override
    public String name() {
        return "add shift preference";
    }

    @Override
    public String description() {
        return "add day and time an employee can work";
    }

    @Override
    public boolean isMatching(String line) {
        return line.startsWith("add shift preference");
    }

    @Override
    public void execute(String[] command, CommandRunner runner) {
        if (command.length != 4) {
            System.out.println("Error: All arguments must be supplied.");
            System.out.println("Usage:");
            System.out.println("> add shift preference <shift>");
            return;
        }

        Employee.ShiftDateTime shift;
        try {
            shift = CommandRunner.parseShiftDateTime(command[3]);
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: shift %s\n", e.getMessage());
            return;
        }

        Response<Employee> updated = runner.getService().addEmployeeShiftPreference(runner.getSubject(), runner.getSubject(), shift);
        if (updated.isError()) {
            System.out.printf("Error: %s\n", updated.getErrorMessage());
            return;
        }
        System.out.println("Updated employee shift preference.");
    }
}