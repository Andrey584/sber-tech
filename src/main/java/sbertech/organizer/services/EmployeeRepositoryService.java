package sbertech.organizer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sbertech.organizer.beans.EmployeeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;

@Service
public class EmployeeRepositoryService {
    @Value("classpath:storage.xml")
    private Resource storage;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public EmployeeRepositoryService() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(EmployeeList.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        unmarshaller = context.createUnmarshaller();
    }

    public void write(EmployeeList employeeList) {
        try {
            StringWriter writer = new StringWriter();
            marshaller.marshal(employeeList, writer);
            marshaller.marshal(employeeList, storage.getFile());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public EmployeeList read() {
        try {
            File file = storage.getFile();
            if (file.length() == 0) {
                return new EmployeeList();
            }
            return (EmployeeList) unmarshaller.unmarshal(file);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void update() {

    }
}
