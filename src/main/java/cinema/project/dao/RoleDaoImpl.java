package cinema.project.dao;

import cinema.project.exception.DataProcessingException;
import cinema.project.model.Role;
import cinema.project.model.RoleType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add role: " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(RoleType roleType) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Role r "
                    + "WHERE r.roleType = :roleType", Role.class)
                    .setParameter("roleType", roleType)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get role by type: " + roleType, e);
        }
    }
}
