package lk.ijse.orm.hms.bo.customer;

import lk.ijse.orm.hms.bo.SuperBO;
import lk.ijse.orm.hms.dto.StudentDTO;

import java.io.IOException;
import java.util.List;

public interface StudentBO extends SuperBO {
    List<StudentDTO> getAllStudent() throws IOException;

    boolean saveStudent(StudentDTO dto) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;

}
