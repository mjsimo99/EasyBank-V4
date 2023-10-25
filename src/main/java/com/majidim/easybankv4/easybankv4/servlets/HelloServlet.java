package com.majidim.easybankv4.easybankv4.servlets;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.newService.AgenceService;
import com.majidim.easybankv4.easybankv4.newService.ClientService;
import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.newService.DemandeCreditService;
import com.majidim.easybankv4.easybankv4.newService.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/test")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeService employeService = new EmployeService(new EmployeImpl());
        ClientService clientService = new ClientService(new ClientImpl());
        AgenceService agenceService = new AgenceService(new AgenceImp());
        DemandeCreditService demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
        String method = req.getParameter("_METHOD");
        String code = req.getParameter("_CODE");
        if ("fake".equals(method)) {
            Client client = new Client();
            Employe employe = new Employe();
            Agence agence = new Agence();
            Optional<Client> optionalClient = clientService.findByCode("client");
            Optional<Employe> employeOptional = employeService.findByMatricule("employer");
            Optional<Agence> agenceOptional = agenceService.findByCode("agence");
            if (optionalClient.isPresent()) client = optionalClient.get();
            if (employeOptional.isPresent()) employe = employeOptional.get();
            if (agenceOptional.isPresent()) agence = agenceOptional.get();
            DemendeCredit demendeCreditFalse = new DemendeCredit();
            demendeCreditFalse.setNumero(code);
            demendeCreditFalse.setMontant(5000D);
            demendeCreditFalse.setRemarque("description");
            demendeCreditFalse.setDate(LocalDate.now());
            demendeCreditFalse.setDuree("14");
            demendeCreditFalse.setStatus("EnAttante");
            demendeCreditFalse.setSimulation(384.50585834838205D);
            demendeCreditFalse.setDate(LocalDate.now());
            demendeCreditFalse.setEmploye(employe);
            demendeCreditFalse.setAgence(agence);
            demendeCreditFalse.setClient(client);

            Optional<DemendeCredit> optionalDemendeCredit = demandeCreditService.create(demendeCreditFalse);
            if (optionalDemendeCredit.isPresent()) {
                System.out.println("is present");
            } else {
                System.out.println("not present");
            }


        }
    }

    public void destroy() {
    }

    public static String generateRandomString(int length) {
        // Characters that can be used in the random string
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // Generate a random index within the range of available characters
            int randomIndex = random.nextInt(characters.length());
            // Append the character at the random index to the random string
            randomString.append(characters.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
/*
if ("client".equals(method)) {
            System.out.println("client");
            EmployeService employeService = new EmployeService(new EmployeImpl());
            Optional<Employe> opt = employeService.findByMatricule("2cOM3");
            if (opt.isPresent()) {
                Employe agence = opt.get();
                System.out.println("nom " + agence.getNom());
                System.out.println("nom " + agence.getMatricule());
            }else{
                System.out.println("not found");
            }
        } else if ("employer".equals(method)) {
            System.out.println("employer");
            EmployeService employeService = new EmployeService(new EmployeImpl());
            Employe employe = new Employe();
            employe.setMatricule(generateRandomString(5));
            employe.setNom("hibernate");
            employe.setTel("0999000");
            employe.setAdress("adresse");
            employe.setPrenom("prenom");
            employe.setEmailAdresse("email");
            employe.setDateN(LocalDate.now());
            employe.setDateRecrutement(LocalDate.now());
            employeService.create(employe);
        } else if ("agence".equals(method)) {
            AgenceService agenceService = new AgenceService(new AgenceImp());

            Agence agence = new Agence();
            agence.setCode(generateRandomString(4));
            agence.setNom("nom");
            agence.setAdresse("adresse");
            agence.setTel("tel");
            agenceService.create(agence);

        } else if ("demande".equals(method)) {
            EmployeService employeService = new EmployeService(new EmployeImpl());
            ClientService clientService = new ClientService(new ClientImpl());
            AgenceService agenceService = new AgenceService(new AgenceImp());
            Agence agence = agenceService.findByCode("Y54w").get();
            Employe employe = employeService.findByMatricule("2cOM3").get();
            Client client = clientService.findByCode("AoCgl").get();
            DemandeCreditService demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
            DemendeCredit demendeCredit = new DemendeCredit();
            demendeCredit.setNumero(generateRandomString(5));
            demendeCredit.setMontant(300D);
            demendeCredit.setRemarque("description");
            demendeCredit.setDate(LocalDate.now());
            demendeCredit.setDuree("5");
            demendeCredit.setStatus(CreditStatus.EnAttante);
            demendeCredit.setDate(LocalDate.now());
            demendeCredit.setEmploye(employe);
            demendeCredit.setAgence(agence);
            demendeCredit.setClient(client);
            demandeCreditService.create(demendeCredit);

        }
* */