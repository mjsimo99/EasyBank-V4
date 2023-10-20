package com.majidim.easybankv4.easybankv4.implementation;

import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.helper.HibernateUtil;
import com.majidim.easybankv4.easybankv4.interfeces.IEmploye;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class EmployeImpl implements IEmploye {

    @Override
    public Optional<Personne> Add(Personne personne) {
        if (personne instanceof Employe employe) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(employe);
                transaction.commit();
                return Optional.of(employe);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Employe> SearchByMatricule(String matricule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employe WHERE matricule = :matricule", Employe.class)
                    .setParameter("matricule", matricule)
                    .list();
        }
    }

    @Override
    public boolean Delete(String matricule) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Employe employeToDelete = session.get(Employe.class, matricule);

            if (employeToDelete != null) {
                session.delete(employeToDelete);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<Employe> ShowList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employe", Employe.class).list();
        }
    }

    @Override
    public List<Employe> SearchByEmail(String emailAdresse) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employe WHERE emailAdresse LIKE :emailAdresse", Employe.class)
                    .setParameter("emailAdresse", "%" + emailAdresse + "%")
                    .list();
        }
    }

    @Override
    public Optional<Employe> Update(Employe employe) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employe);
            transaction.commit();
            return Optional.of(employe);
        }
    }
}
