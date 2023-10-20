package com.majidim.easybankv4.easybankv4.implementation;

import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.helper.HibernateUtil;
import com.majidim.easybankv4.easybankv4.interfeces.IClient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ClientImpl implements IClient {

    @Override
    public List<Client> SearchByCode(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE code = :code", Client.class)
                    .setParameter("code", code)
                    .list();
        }
    }

    @Override
    public boolean Delete(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client clientToDelete = session.get(Client.class, code);

            if (clientToDelete != null) {
                session.delete(clientToDelete);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        }
    }


    @Override
    public List<Client> Showlist() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client", Client.class).list();
        }
    }

    @Override
    public Optional<Client> Update(Client client) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return Optional.of(client);
        }
    }

    @Override
    public List<Client> SearchByMatricule(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Client WHERE code LIKE :code", Client.class)
                    .setParameter("code", "%" + code + "%")
                    .list();
        }
    }

    @Override
    public Optional<Personne> Add(Personne personne) {
        if (personne instanceof Client client) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(client);
                transaction.commit();
                return Optional.of(personne);
            }
        }
        return Optional.empty();
    }


}
