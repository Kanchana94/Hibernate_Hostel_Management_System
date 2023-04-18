package lk.ijse.orm.hms.bo.customer.impl;

import lk.ijse.orm.hms.bo.customer.StudentBO;
import lk.ijse.orm.hms.dao.DAOFactory;
import lk.ijse.orm.hms.dao.DAOTypes;
import lk.ijse.orm.hms.dao.custom.StudentDAO;
import lk.ijse.orm.hms.dto.StudentDTO;
import lk.ijse.orm.hms.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO= DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudent() throws IOException {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getStudentName(),
                    s.getStudentAddress(),
                    s.getContac_no(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()

        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }
}
