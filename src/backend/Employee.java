package backend;

public class Employee {
    private int id;
    private String name;
    private String department;
    private String contact;

    public Employee() {}

    public Employee(int id, String name, String department, String contact) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.contact = contact;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public String getContact() {
        return contact;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
}
