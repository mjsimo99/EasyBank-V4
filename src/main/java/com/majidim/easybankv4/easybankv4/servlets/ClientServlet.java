package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.implementation.ClientImpl;
import com.majidim.easybankv4.easybankv4.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService;

    public ClientServlet() {
        this.clientService = new ClientService(new ClientImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add" -> addClient(request, response);
            case "edit" -> editClient(request, response);
            case "update" -> updateClient(request, response);
            case "delete" -> deleteClient(request, response);
            case "search" -> searchClients(request, response);

            default -> listClients(request, response);
        }
    }
    private void searchClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Client> searchResults = clientService.SearchByMatricule(query);
        request.setAttribute("clients", searchResults);
        request.getRequestDispatcher("/view/client/clientlist.jsp").forward(request, response);
    }

    private void listClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Client> clients = clientService.Showlist();

        request.setAttribute("clients", clients);
        request.getRequestDispatcher("/view/client/clientlist.jsp").forward(request, response);

    }


    private void editClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            List<Client> clients = clientService.SearchByCode(code);
            if (!clients.isEmpty()) {
                Client client = clients.get(0);
                request.setAttribute("client", client);
                request.getRequestDispatcher("/view/client/clientedit.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/client");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/client");
        }
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if (code != null) {
            boolean deleted = clientService.Delete(code);
            if (deleted) {
                response.sendRedirect(request.getContextPath() + "/client?success=delete-success");
            } else {
                response.sendRedirect("/client?error=delete-failed");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/client");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateN = LocalDate.parse(request.getParameter("dateN"));
        String tel = request.getParameter("tel");
        String adress = request.getParameter("adress");

        Client client = new Client(code, nom, prenom, dateN, tel, adress);

        Optional<Client> updatedClient = clientService.Update(client);

        if (updatedClient.isPresent()) {
            request.setAttribute("successMessage", "Client updated successfully!");
            request.getRequestDispatcher("/view/client/clientedit.jsp").forward(request, response);
        } else {
            response.sendRedirect("/client?error=update-failed");
        }
    }



    private void addClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateN = LocalDate.parse(request.getParameter("dateN"));
        String tel = request.getParameter("tel");
        String adress = request.getParameter("adress");

        Client client = new Client(code, nom, prenom, dateN, tel, adress);

        try {
            Optional<Personne> addedClient = clientService.Add(client);
            if (addedClient.isPresent()) {
                request.setAttribute("successMessage", "Client added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add client");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Client with the same code already exists");
        }

        request.getRequestDispatcher("/view/client/addclient.jsp").forward(request, response);
    }


}
