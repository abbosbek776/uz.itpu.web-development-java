package org.itpu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import org.itpu.entity.User;

public class MainSimple {
    public static void main(String[] args) {
        // configure from properties
        Configuration configure = new Configuration().configure();
        configure.addAnnotatedClass(User.class);
        // instantiate SessionFactory
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // get link to stats
        Statistics stats = sessionFactory.getStatistics();

        // save 2 users
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user1 = new User("U-1");
        User user2 = new User("U-2");
        session.save(user1);
        session.save(user2);
        session.getTransaction().commit();
        session.close();
        sessionFactory.getCache().evictAllRegions(); // clean up all cache
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        stats.setStatisticsEnabled(true);
        System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
        printStats(stats, 0); // 0-0-0

        // flow U-1: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user1FromDatabase = session.get(User.class, user1.getId());
        printData(user1FromDatabase, stats, 1); // 0-1-1
        // get U-1: from session, 2lvl cache is not touched
        User user1FromSession = session.get(User.class, user1.getId());
        printData(user1FromSession, stats, 2); // 0-1-1
        session.getTransaction().commit();

        // clearing 1LVL cache
        session.clear();

        // flow U-1: check Session, check 2LVL, mark Hit entity from 2Lvl cache
        session = sessionFactory.openSession();
        session.beginTransaction();
        User hitFrom2LvlUser1 = session.get(User.class, user1.getId());
        session.getTransaction().commit();
        printData(hitFrom2LvlUser1, stats, 3); // 1-1-1


        // flow U-2: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user2FromDb = session.get(User.class, user2.getId());
        session.getTransaction().commit();
        printData(user2FromDb, stats, 4); // 1-2-2

        // flow U-2: check Session, check 2LVL, mark Hit
        session = sessionFactory.openSession();
        session.beginTransaction();
        User hitFrom2LvlUser2 = session.get(User.class, user2.getId());
        session.getTransaction().commit();
        printData(hitFrom2LvlUser2, stats, 5); // 2-2-2

        // clean up all cache
        sessionFactory.getCache().evictAllRegions();

        // flow U-1: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User fromDbUser1 = session.get(User.class, user1.getId());
        session.getTransaction().commit();
        printData(fromDbUser1, stats, 6); // 2-3-3

        // flow U-2: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User fromDbUser2 = session.get(User.class, user2.getId());
        session.getTransaction().commit();
        printData(fromDbUser2, stats, 7); // 2-4-4

        // flow U-1: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User from2LvlUser1 = session.get(User.class, user1.getId());
        session.getTransaction().commit();
        printData(from2LvlUser1, stats, 8); // 3-4-4

        // flow U-2: check in session, check in 2LVL, mark missing in 2LVL, select from DB, PUT to 2LVL, PUT to SESSION
        session = sessionFactory.openSession();
        session.beginTransaction();
        User from2LvlUser2 = session.get(User.class, user2.getId());
        session.getTransaction().commit();
        printData(from2LvlUser2, stats, 9); // 4-4-4

        System.out.println("EXIT");

        session.close();
        sessionFactory.close();
    }

    private static void printStats(Statistics stats, int i) {
        System.out.println("***** " + i + " *****");
        System.out.println("Fetch Count=" + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
        System.out.println("");
    }

    private static void printData(User user, Statistics stats, int count) {
        System.out.println(count + ":: Id=" + user.getId());
        printStats(stats, count);
    }
}
