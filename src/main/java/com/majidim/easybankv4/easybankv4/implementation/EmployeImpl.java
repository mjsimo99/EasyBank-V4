package com.majidim.easybankv4.easybankv4.implementation;



import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.helper.DatabaseConnection;
import com.majidim.easybankv4.easybankv4.interfeces.IEmploye;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class EmployeImpl implements IEmploye {



    private static final String ADD_EMPLOYE = "INSERT INTO Employes (matricule, dateRecrutement, emailadresse, nom, prenom, dateN, tel, adress) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SEARCH_BY_MATRICULE = "SELECT * FROM Employes WHERE matricule=?";
    private static final String DELETE_EMPLOYE = "DELETE FROM Employes WHERE matricule=?";
    private static final String SHOW_ALL_EMPLOYEES = "SELECT * FROM Employes";
    private static final String SEARCH_BY_EMAIL = "SELECT * FROM Employes WHERE emailAdresse LIKE ?";

    private static final String UPDATE_EMPLOYE = "UPDATE Employes SET dateRecrutement=?, emailadresse=?, nom=?, prenom=?, dateN=?, tel=?, adress=? WHERE matricule=?";

    @Override
    public Optional<Personne> Add(Personne personne) {
        if (personne instanceof Employe employe) {
            Connection connection = DatabaseConnection.getConn();
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYE)) {
                preparedStatement.setString(1, employe.getMatricule());
                preparedStatement.setObject(2, employe.getDateRecrutement());
                preparedStatement.setString(3, employe.getEmailAdresse());
                preparedStatement.setString(4, employe.getNom());
                preparedStatement.setString(5, employe.getPrenom());
                preparedStatement.setObject(6, employe.getDateN());
                preparedStatement.setString(7, employe.getTel());
                preparedStatement.setString(8, employe.getAdress());

                preparedStatement.executeUpdate();
                return Optional.of(employe);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Employe> SearchByMatricule(String matricule) {
        List<Employe> resultList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConn();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_MATRICULE)) {
            preparedStatement.setString(1, matricule);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employe employe = createEmployeFromResultSet(resultSet);
                resultList.add(employe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public boolean Delete(String matricule) {
        Connection connection = DatabaseConnection.getConn();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYE)) {
            preparedStatement.setString(1, matricule);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employe> ShowList() {
        List<Employe> resultList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConn();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL_EMPLOYEES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employe employe = createEmployeFromResultSet(resultSet);
                resultList.add(employe);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<Employe> SearchByEmail(String emailAdresse) {
        Connection connection = DatabaseConnection.getConn();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_EMAIL)) {
            preparedStatement.setString(1,"%" + emailAdresse + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Employe> resultList = new ArrayList<>();

            while (resultSet.next()) {
                Employe employe = createEmployeFromResultSet(resultSet);
                resultList.add(employe);
            }
            resultSet.close();
            return resultList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employe> Update(Employe employe) {
        Connection connection = DatabaseConnection.getConn();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYE)) {
            preparedStatement.setObject(1, employe.getDateRecrutement());
            preparedStatement.setString(2, employe.getEmailAdresse());
            preparedStatement.setString(3, employe.getNom());
            preparedStatement.setString(4, employe.getPrenom());
            preparedStatement.setObject(5, employe.getDateN());
            preparedStatement.setString(6, employe.getTel());
            preparedStatement.setString(7, employe.getAdress());
            preparedStatement.setString(8, employe.getMatricule());

            int rowsUpdated = preparedStatement.executeUpdate();
            return (rowsUpdated > 0) ? Optional.of(employe) : Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private Employe createEmployeFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employe(
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getDate("dateN").toLocalDate(),
                resultSet.getString("tel"),
                resultSet.getString("adress"),
                resultSet.getString("matricule"),
                resultSet.getDate("dateRecrutement").toLocalDate(),
                resultSet.getString("emailAdresse")

        );
    }
}