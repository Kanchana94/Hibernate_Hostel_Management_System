package lk.ijse.orm.hms.dao;

import lk.ijse.orm.hms.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.orm.hms.dao.custom.impl.RoomDAOImpl;
import lk.ijse.orm.hms.dao.custom.impl.StudentDAOImpl;
import lk.ijse.orm.hms.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOTypes type){
        switch (type){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            case RESERVATION:
                return (T) new ReservationDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
            default:
                return null;
        }
    }
}