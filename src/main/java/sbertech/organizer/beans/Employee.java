package sbertech.organizer.beans;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlType(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {

    private String number;
    private String name;
    private String position;
    private String organization;
    private String address;
    @XmlElementWrapper(name = "phoneNumbers", nillable = true)
    @XmlElement(name = "phoneNumber")
    private List<String> phoneNumbers;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t").append("Табельный номер: " + number)
                .append("\n")
                .append("\t").append("Фамилия И.О. : " + name)
                .append("\n")
                .append("\t").append("Должность: " + position)
                .append("\n")
                .append("\t").append("Организация: " + organization)
                .append("\n")
                .append("\t").append("Адрес: " + address)
                .append("\n")
                .append("\t").append("Список телефонов: " + phoneNumbers.toString().replace("[", "").replace("]", ""))
                .append("\n\t------------------");

        return sb.toString();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


}
