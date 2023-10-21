package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.implementation.ClientImpl;
import com.majidim.easybankv4.easybankv4.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchClient")
public class SearchClientServlet extends HttpServlet {
    private ClientService clientService;

    @Override
    public void init() {
        this.clientService = new ClientService(new ClientImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientCode = request.getParameter("code");

        List<Client> clients = clientService.SearchByCode(clientCode);

        if (!clients.isEmpty()) {
            Client client = clients.get(0);
            String clientDetails = "Last Name: " + client.getNom() +
                    "\nFirst Name: " + client.getPrenom() +
                    "\nDate of Birth: " + client.getDateN() +
                    "\nPhone: " + client.getTel() +
                    "\nAddress: " + client.getAdress();

            response.setContentType("text/plain");
            response.getWriter().write(clientDetails);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

