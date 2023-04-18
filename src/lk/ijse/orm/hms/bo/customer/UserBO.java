package lk.ijse.orm.hms.bo.customer;

import lk.ijse.orm.hms.bo.SuperBO;
import lk.ijse.orm.hms.dto.UserLoginDTO;
import lk.ijse.orm.hms.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserLoginDTO> getAllUser() throws IOException;

    boolean saveUser(UserLoginDTO dto) throws IOException;

    boolean updateUser(UserLoginDTO dto) throws IOException;

    boolean deleteUser(String id) throws IOException;

    UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException;
}