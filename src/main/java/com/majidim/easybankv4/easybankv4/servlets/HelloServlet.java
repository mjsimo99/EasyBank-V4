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
/*import com.majidim.easybankv4.easybankv4.HibernateImps.DemandeCreditImpl;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.newService.DemandeCreditService;
import com.majidim.easybankv4.easybankv4.newService.EmployeService;*/
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

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        System.out.println("before service call");
        ClientService clientService = new ClientService(new ClientImpl());
        System.out.println("after service call");
        
        Client client = new Client();
        client.setCode(generateRandomString(5));
        client.setTel("98989");
        client.setNom("bouzhar");
        client.setPrenom("prenom");
        client.setAdress("jhjhd");
        client.setDateN(LocalDate.now());
        client.setEmailAdresse("email");
        client.setDemendeCredits(null);
        clientService.create(client);
        System.out.println("after create");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AgenceService agenceService = new AgenceService(new AgenceImp());/*
            Agence agence = new Agence();
            agence.setCode(generateRandomString(4));
            agence.setNom("nom");
            agence.setAdresse("adresse");
            agence.setTel("tel");*/
        Agence agence = agenceService.findByCode("x").get();
        System.out.println(agence.getAdresse());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("_METHOD");

        /*if ("client".equals(method)){
            System.out.println("client");
            ClientService clientService = new ClientService(new ClientImpl());
            Client client = new Client();
            client.setCode(generateRandomString(5));
            client.setTel("98989");
            client.setNom("bouzhar");
            client.setPrenom("prenom");
            client.setAdress("jhjhd");
            client.setDateN(LocalDate.now());
            client.setEmailAdresse("email");
            client.setDemendeCredits(null);
            //clientService.create(client);
        }
        else if ("employer".equals(method)) {
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
        } else if("agence".equals(method)) {
            AgenceService agenceService = new AgenceService(new AgenceImp());

            Agence agence = new Agence();
            agence.setCode(generateRandomString(4));
            agence.setNom("nom");
            agence.setAdresse("adresse");
            agence.setTel("tel");
            agenceService.create(agence);

        } else if("demande".equals(method)) {
            EmployeService employeService = new EmployeService(new EmployeImpl());
            ClientService clientService = new ClientService(new ClientImpl());
            AgenceService agenceService = new AgenceService(new AgenceImp());
            Employe employe = employeService.findByMatricule("2cOM3").get();
            Agence agence = agenceService.findByCode("Y54w").get();
            Client client = clientService.findByCode("43q6e").get();
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

        }*/
        //DemandeCreditService demandeCreditService = new DemandeCreditService(new DemandeCreditImpl());
        //AgenceService agenceService = new AgenceService(new AgenceImp());
        //ClientService clientService = new ClientService(new ClientImpl());
        //Optional<Client> client = clientService.findByCode("1");
/*        if (client.isPresent()){
            System.out.println(client.get().getNom());
            System.out.println(client.get().getTel());
        }*/

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