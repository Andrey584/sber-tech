package sbertech.organizer.beans;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "employeeList")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeList implements Serializable {
    @XmlElement(name = "employee")
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employees;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employees = employeeList;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список пользователей: ");
        for (Employee employee : employees) {
            sb.append("\n").append(employee);
        }
        return sb.toString();
    }
}
