package controller;


import dao.DepartmentDao;
import dao.StaffDao;
import model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/staff")
public class StaffServlet extends HttpServlet {
    StaffDao staffDao = new StaffDao();
    DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createStaff(request, response);
                    break;
                case "edit":
                    editStaff(request, response);
                    break;
                case "delete":
                    deleteStaff(request, response);
                    break;

            }
        }catch (SQLException | ParseException ex){
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    showDeleteForm(request, response);
                    break;
                case "search":
                    searchStaffByName(request,response);
                    break;
                default:
                    listStaff(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Staff> listStaff = staffDao.getAll();

        request.setAttribute("listStaff", listStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setAttribute("department", departmentDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff existingStaff= staffDao.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");
        request.setAttribute("staff", existingStaff);
        dispatcher.forward(request,response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff existingStaff = staffDao.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/delete.jsp");
        request.setAttribute("staff", existingStaff);
        dispatcher.forward(request, response);
    }

    private void createStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        String name = request.getParameter("name");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        int iddp = Integer.parseInt(request.getParameter("department"));
        Staff staff = new Staff(name, dateOfBirth, address, phoneNumber, email, departmentDao.findById(iddp));
        staffDao.create(staff);
        listStaff(request,response);

    }

    private void editStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        int iddp = (departmentDao.findByName(request.getParameter("department"))).getId();
        Staff staff = new Staff(id, name, dateOfBirth, address, phoneNumber, email, departmentDao.findById(iddp));
        staffDao.edit(id, staff);
        listStaff(request,response);
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffDao.delete(id);
        listStaff(request,response);
    }

    private void searchStaffByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        RequestDispatcher dispatcher;
        String search = request.getParameter("search");
        request.setAttribute("listStaff", staffDao.getAllByName(search));
        dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request, response);
    }

}
