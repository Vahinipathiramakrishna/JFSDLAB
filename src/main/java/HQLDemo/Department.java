package HQLDemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="department_table")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or any other strategy depending on your DB
    private int departmentId;

    @Column(name="dname", length=100, nullable=false)
    private String name;

    @Column(name="dlocation", length=100, nullable=true)
    private String location;

    @Column(name="hod_name", length=100, nullable=false)
    private String HoDname;

    // Getter and Setter methods
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHoDname() {
        return HoDname;
    }

    public void setHoDname(String HoDname) {
        this.HoDname = HoDname;
    }

    @Override
    public String toString() {
        return "Department [departmentId=" + departmentId + ", name=" + name + ", location=" + location + ", HoDname=" + HoDname + "]";
    }
}
