package lk.ijse.orm.hms.bo.customer.impl;

import lk.ijse.orm.hms.bo.customer.UserBO;
import lk.ijse.orm.hms.dao.DAOFactory;
import lk.ijse.orm.hms.dao.DAOTypes;
import lk.ijse.orm.hms.dao.custom.UserDAO;
import lk.ijse.orm.hms.dto.UserLoginDTO;
import lk.ijse.orm.hms.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO= DAOFactory.getInstance().getDAO(DAOTypes.USER);
    @Override
    public List<UserLoginDTO> getAllUser() throws IOException {
        List<UserLogin> all = userDAO.getAll();
        ArrayList<UserLoginDTO> allUser = new ArrayList<>();

        for(UserLogin u: all){
            allUser.add(new UserLoginDTO(
                    u.getUser_id(),
                    u.getUser_name(),
                    u.getPassword()
            ));
        }

        return allUser;
    }

    @Override
    public boolean saveUser(UserLoginDTO dto) throws IOException {
        return userDAO.save(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean updateUser(UserLoginDTO dto) throws IOException {
        return userDAO.update(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()


        ));
    }

    @Override
    public boolean deleteUser(String id) throws IOException {
        return userDAO.delete(id);
    }

    @Override
    public UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException {
        return userDAO.search(id);
    }
}
