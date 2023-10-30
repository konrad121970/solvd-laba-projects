package employees;

public class Employees {
    private Driver[] drivers;
    private Accountant[] accountants;

    public Employees(Driver[] drivers, Accountant[] accountants) {
        this.drivers = drivers;
        this.accountants = accountants;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(Driver[] drivers) {
        this.drivers = drivers;
    }

    public Accountant[] getAccountants() {
        return accountants;
    }

    public void setAccountants(Accountant[] accountants) {
        this.accountants = accountants;
    }
}
