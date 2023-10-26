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
            String employeMatricule = request.getParameter("employeMatricule");
            String clientCode = request.getParameter("client_code");
            String simulation = request.getParameter("simulation");

            DemendeCredit demandeCredit = new DemendeCredit();
            demandeCredit.setNumero(generateRandomString(6));
            demandeCredit.setDate(LocalDate.now());
            demandeCredit.setMontant(montant);
            demandeCredit.setDuree(duree);
            demandeCredit.setRemarque(remarque);
            demandeCredit.setStatus("EnAttente");
            demandeCredit.setSimulation(Double.valueOf(simulation));

            Optional<Employe> employe = employeService.findByMatricule(employeMatricule);

            Optional<Agence> agence = agenceService.findByCode(String.valueOf(1));
            Optional<Client> client = clientService.findByCode(clientCode);

            demandeCredit.setAgence(agence.orElse(null));
            demandeCredit.setEmploye(employe.orElse(null));
            demandeCredit.setClient(client.orElse(null));

            Optional<DemendeCredit> addedDemandeCredit = demandeCreditService.create(demandeCredit);

            if (addedDemandeCredit.isPresent()) {
                request.setAttribute("successMessage", "Demande de crédit ajoutée avec succès!");
                System.out.println("Demande de crédit ajoutée avec succès: " + addedDemandeCredit.get().getNumero());
            } else {
                request.setAttribute("errorMessage", "Échec de l'ajout de la demande de crédit.");
                System.out.println("Échec de l'ajout de la demande de crédit.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Échec de l'ajout de la demande de crédit");
            System.out.println("Une erreur s'est produite lors de l'ajout de la demande de crédit: " + e.getMessage());
        }

        request.getRequestDispatcher("/view/simulation/simulation.jsp").forward(request, response);
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
