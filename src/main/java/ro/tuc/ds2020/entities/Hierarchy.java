package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Hierarchy  implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boss_id", referencedColumnName = "id")
    private Employee boss;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subordinate_id", referencedColumnName = "id")
    private Employee subordinate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public Hierarchy() {
    }

    public Hierarchy(Employee boss, Employee subordinate, Department department) {
        this.boss = boss;
        this.subordinate = subordinate;
        this.department = department;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public Employee getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(Employee subordinate) {
        this.subordinate = subordinate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
