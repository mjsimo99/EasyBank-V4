package com.majidim.easybankv4.easybankv4.servlets;

import java.io.*;
import java.util.Optional;
import java.util.Random;

import com.majidim.easybankv4.easybankv4.HibernateImps.AgenceImp;
import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.dto.Agence;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.service.ClientService;
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
/*
* demendeCredit.setAgence();
        demendeCredit.setMontant();
        demendeCredit.setNumero();
        demendeCredit.setEmploye();
        demendeCredit.setRemarque(); */

        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager(); //represent the context*/
/*        Agence agence = new Agence();
        agence.setAdresse("without");
        agence.setNom("persist");
        agence.setTel(generateRandomString(3));
        agence.setCode(generateRandomString(10));
        AgenceService agenceService = new AgenceService(new AgenceImp());
        agenceService.create(agence);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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