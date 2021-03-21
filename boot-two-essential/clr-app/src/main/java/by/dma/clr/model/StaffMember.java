package by.dma.clr.model;

import javax.swing.text.Position;

public class StaffMember {

    private String employeeId;
    private String firstName;
    private String lastName;
    private Position position;

    public StaffMember() {
    }

    public StaffMember(String employeeId, String firstName, String lastName, Position position) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "StaffMember{" +
               "employeeId='" + employeeId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", position=" + position +
               '}';
    }
}
