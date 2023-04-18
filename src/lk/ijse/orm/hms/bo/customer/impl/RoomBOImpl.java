package lk.ijse.orm.hms.bo.customer.impl;

import lk.ijse.orm.hms.bo.customer.RoomBO;
import lk.ijse.orm.hms.dao.DAOFactory;
import lk.ijse.orm.hms.dao.DAOTypes;
import lk.ijse.orm.hms.dao.custom.RoomDAO;
import lk.ijse.orm.hms.dto.RoomDTO;
import lk.ijse.orm.hms.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOTypes.ROOM);
    @Override
    public List<RoomDTO> getAllRooms() throws IOException {
        List<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for(Room r: all){
            allRooms.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()
            ));
        }

        return allRooms;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()

        ));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }
}
