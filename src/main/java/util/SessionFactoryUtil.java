package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;

    @FunctionalInterface
    public interface Executor<T> {
        T execute(Session session);
    }

    public static <T> T transaction(Executor<T> executor) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return executor.execute(session);
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close(); // Чистит кеш и закрывает соединение с БД
        sessionFactory = null;
    }

}