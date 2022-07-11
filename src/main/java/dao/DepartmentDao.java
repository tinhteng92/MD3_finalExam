package dao;

import connect_MySQL.Connect_MySQL;
import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements IService<Department>{
    private static final String SELECT_ALL_DEPARTMENT = "SELECT * from department";
    private static final String SELECT_DEPARTMENT_BY_ID = "SELECT * from department where iddp = ?";
    private static final String SELECT_DEPARTMENT_BY_NAME = "SELECT * from department where nameDp like concat('%',?,'%')";
    @Override
    public List getAll() {
        List<Department> departments = new ArrayList<>();

        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARTMENT)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int iddp = rs.getInt(1);
                String nameDp = rs.getString(2);
                departments.add(new Department(iddp, nameDp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }


    @Override
    public boolean create(Department department ) {
        return false;
    }

    @Override
    public boolean edit(int id, Department department) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Department findById(int id) {
        try (Connection connection = Connect_MySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int iddp = rs.getInt(1);

            String nameDp = rs.getString(2);
            Department department = new Department(iddp, nameDp);

            return department;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Department findByName(String name){
        Department department = null;
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            department = new Department(id, name);
            return department;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}
