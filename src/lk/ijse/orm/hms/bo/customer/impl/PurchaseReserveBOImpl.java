package lk.ijse.orm.hms.bo.customer.impl;

import lk.ijse.orm.hms.bo.customer.PurchaseReserveBO;
import lk.ijse.orm.hms.dto.ReservationDTO;
import lk.ijse.orm.hms.dto.RoomDTO;
import lk.ijse.orm.hms.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PurchaseReserveBOImpl implements PurchaseReserveBO {
    @Override
    public boolean purchaseReserveSave(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean UpdateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public RoomDTO searchRooms(String id) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public boolean checkRoomIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        return null;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        return null;
    }

    @Override
    public List getStudentIds() throws IOException {
        return null;
    }

    @Override
    public List getRoomIds() throws IOException {
        return null;
    }
}
