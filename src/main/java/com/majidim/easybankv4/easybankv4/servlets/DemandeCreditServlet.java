package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.newService.AgenceService;
import com.majidim.easybankv4.easybankv4.newService.ClientService;
import com.majidim.easybankv4.easybankv4.newService.DemandeCreditService;
import com.majidim.easybankv4.easybankv4.newService.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@WebServlet("/demandeCredit")
public class DemandeCreditServlet extends HttpServlet {
    private DemandeCreditService demandeCreditService;
    private EmployeService employeService;
    private AgenceService agenceService;
    private ClientService clientService;

    @Override
    public void init() {
        this.demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
        this.employeService = new EmployeService(new EmployeImpl());
        this.agenceService = new AgenceService(new AgenceImp());
        this.clientService = new ClientService(new ClientImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add" -> addDemandeCredit(request,response);
            default -> listEmploye(request,response);
        }
    }
    private void listEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employe> employes = employeService.getAll();
        System.out.println("listEmploye : "+employes.size());
        request.setAttribute("employes", employes);
        request.getRequestDispatcher("/view/simulation/simulation.jsp").forward(request, response);
    }


    private void addDemandeCredit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Double montant = Double.parseDouble(request.getParameter("montant"));
            String duree = request.getParameter("duree");
            String remarque = request.getParameter("remarque");
            String status = request.getParameter("status");
            String agenceCode = request.getParameter("agenceCode");
            String employeMatricule = request.getParameter("employeMatricule");
            String clientCode = request.getParameter("clientCode");

            DemendeCredit demandeCredit = new DemendeCredit();
            demandeCredit.setNumero(generateRandomString(6));
            demandeCredit.setDate(LocalDate.now());
            demandeCredit.setMontant(montant);
            demandeCredit.setDuree(duree);
            demandeCredit.setRemarque(remarque);
            demandeCredit.setStatus(status);

            Optional<Agence> agence = agenceService.findByCode(agenceCode);
            Optional<Employe> employe = employeService.findByMatricule(employeMatricule);
            Optional<Client> client = clientService.findByCode(clientCode);

            if (agence.isPresent() && employe.isPresent() && client.isPresent()) {
                demandeCredit.setAgence(agence.get());
                demandeCredit.setEmploye(employe.get());
                demandeCredit.setClient(client.get());

                Optional<DemendeCredit> addedDemandeCredit = demandeCreditService.create(demandeCredit);

                if (addedDemandeCredit.isPresent()) {
                    request.setAttribute("successMessage", "Demande de crédit ajoutée avec succès!");
                } else {
                    request.setAttribute("errorMessage", "Échec de l'ajout de la demande de crédit.");
                }
            } else {
                request.setAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout de la demande de crédit.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout de la demande de crédit.");
        }

        listEmploye(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
