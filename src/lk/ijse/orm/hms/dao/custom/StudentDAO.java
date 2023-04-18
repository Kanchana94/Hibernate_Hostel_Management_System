package lk.ijse.orm.hms.dao.custom;

import lk.ijse.orm.hms.dao.CrudDAO;
import lk.ijse.orm.hms.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {

    public List<String> getStudentIds() throws IOException;
}
