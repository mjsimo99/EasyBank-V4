package com.majidim.easybankv4.easybankv4.servlets;

import java.io.*;
import java.util.HashMap;

import com.majidim.easybankv4.easybankv4.dto.Agence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.persistence.Persistence;

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


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager(); //represent the context
        Agence agence = new Agence();
        agence.setAdresse("without");
        agence.setNom("persist");
        agence.setTel("0649600623");
        try {
            em.getTransaction().begin();
            //em.persist(agence);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void destroy() {
    }
}