package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.implementation.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.implementation.EmployeImpl;
import com.majidim.easybankv4.easybankv4.service.DemandeCreditService;
import com.majidim.easybankv4.easybankv4.service.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/demandeCredit")
public class DemandeCreditServlet extends HttpServlet {
    private DemandeCreditService demandeCreditService;
    private EmployeService employeService;


    @Override
    public void init() {
        this.demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
        this.employeService = new EmployeService(new EmployeImpl());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add" -> populateEmployeeList(request);
            default -> listEmploye(request,response);
        }
    }
    private void listEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employe> employes = employeService.ShowList();
        request.setAttribute("employes", employes);


        request.getRequestDispatcher("/view/simulation/simulation.jsp").forward(request, response);
    }

    private void populateEmployeeList(HttpServletRequest request) {
        List<Employe> employeeList = employeService.ShowList();
        request.setAttribute("employeeList", employeeList);
    }
    private void showDemandeCreditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        populateEmployeeList(request);
        request.getRequestDispatcher("/view/simulation/simulation.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
