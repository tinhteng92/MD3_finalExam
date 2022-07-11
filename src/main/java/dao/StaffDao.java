package dao;

import connect_MySQL.Connect_MySQL;
import model.Department;
import model.Staff;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffDao implements IService<Staff> {
    private static final String INSERT_STAFF_SQL = "INSERT INTO staff (name,dateOfBirth,address,phoneNumber,email,iddp) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_STAFF_BY_ID = "SELECT name,dateOfBirth,address,phoneNumber,email,iddp from staff where id= ?";
    private static final String SELECT_STAFF_BY_NAME = "SELECT * from staff where name like concat('%',?,'%')";
    private static final String SELECT_ALL_STAFF= "SELECT * from staff";
    private static final String DELETE_STAFF_SQL = "DELETE from staff where id=?;";
    private static final String UPDATE_STAFF_SQL = "UPDATE staff set name = ?,dateOfBirth = ?,address = ?,phoneNumber = ?,email = ?,iddp= ? where id = ?;";

    DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List getAll() {
        List<Staff> staffs = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate dateOfBirth = LocalDate.parse(rs.getString("dateOfBirth"));
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                Department department = departmentDao.findById(rs.getInt("iddp"));

                staffs.add(new Staff(id, name, dateOfBirth, address, email, phoneNumber, department));
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return staffs;
    }

    @Override
    public boolean create(Staff staff) {
        boolean check = false;
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)){

            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getPhoneNumber());
            preparedStatement.setInt(6, staff.getDepartment().getId());
            check = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;
    }

    @Override
    public boolean edit(int id, Staff staff) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF_SQL);
            preparedStatement.setInt(7, staff.getId());
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, String.valueOf(staff.getDateOfBirth()));
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getEmail());
            preparedStatement.setString(5, staff.getPhoneNumber());
            preparedStatement.setInt(6, staff.getDepartment().getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }



    @Override
    public boolean delete(int id) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_SQL);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            printSQLException(e);
            return false;
        }
    }

    @Override
    public Staff findById(int id) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phoneNumber");
            Department department = departmentDao.findById(resultSet.getInt("iddp"));

            return new Staff(id, name, dateOfBirth, address, email, phoneNumber, department);

        } catch (SQLException e) {
            printSQLException(e);
        }
        return null;
    }

    public List<Staff> getAllByName(String name) {
        List<Staff> staffs = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameS = resultSet.getString("name");
                LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                Department department = departmentDao.findById(resultSet.getInt("iddp"));

                staffs.add(new Staff(id, nameS, dateOfBirth, address, email, phoneNumber, department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffs;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace();
                System.out.println("SQLState:" + ((SQLException) e).getSQLState());
                System.out.println("Error Code:" + ((SQLException) e).getSQLState());
                System.out.println("Message:" + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause:" + t);
                    t = t.getCause();
                }
            }
        }
    }
}
