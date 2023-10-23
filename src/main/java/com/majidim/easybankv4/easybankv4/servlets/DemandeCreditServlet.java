package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.implementation.DemendeCreditImpl;
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
        this.demandeCreditService = new DemandeCreditService(new DemendeCreditImpl());
        this.employeService = new EmployeService(new EmployeImpl());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add" -> addDemandeCredit(request, response);
            default -> listEmploye(request,response);
        }
    }
    private void listEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employe> employes = employeService.ShowList();
        request.setAttribute("employes", employes);


        request.getRequestDispatcher("/view/simulation/simulation.jsp").forward(request, response);
    }
    private void addDemandeCredit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double simulation = Double.parseDouble(request.getParameter("simulation"));
        String numero = request.getParameter("numero");
        double montant = Double.parseDouble(request.getParameter("montant"));
        String duree = request.getParameter("duree");
        String remarque = request.getParameter("remarque");
        String status = request.getParameter("status");
        String agenceCode = request.getParameter("agence_code");
        String employeMatricule = request.getParameter("employe_matricule");
        String clientCode = request.getParameter("client_code");

        DemendeCredit demandeCredit = new DemendeCredit();
        demandeCredit.setNumero(numero);
        demandeCredit.setDate(LocalDate.now());
        demandeCredit.setMontant(montant);
        demandeCredit.setDuree(duree);
        demandeCredit.setRemarque(remarque);
        demandeCredit.setStatus(CreditStatus.valueOf(status));
        demandeCredit.setAgence(new Agence(agenceCode, null, null, null, null, null, null));
        demandeCredit.setEmploye(new Employe(null, null, null, null, null, employeMatricule, null, null));
        demandeCredit.setClient(new Client(clientCode, null, null, null, null, null));
        demandeCredit.setSimulation(simulation);

        Optional<DemendeCredit> addedDemandeCredit = demandeCreditService.Add(demandeCredit);
        if (addedDemandeCredit.isPresent()) {
            request.setAttribute("successMessage", "Demande de crédit ajoutée avec succès!");
        } else {
            request.setAttribute("errorMessage", "Échec de l'ajout de la demande de crédit");
        }

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
