package lk.ijse.orm.hms.dao.custom;

import lk.ijse.orm.hms.dao.CrudDAO;
import lk.ijse.orm.hms.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    public List getRoomIds() throws IOException;
}
