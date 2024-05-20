package dao.Impl;

import org.junit.jupiter.api.*;

import dao.positionDao;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class positionImplTest {

    static positionDao positionDao;

    @BeforeAll
    static void setUp() throws RemoteException {
        positionDao = new positionImpl();
    }

    @AfterAll
    static void tearDown() {
        positionDao = null;
    }

    @Test
    void cauA1() throws RemoteException {
//        positionDao.listPosition("Analyst", 1000, 20000).forEach(System.out::println);
        assertEquals(1, positionDao.listPosition("Analyst", 1000, 20000).size());
    }
    @Test
    void caauA1() throws RemoteException {
//        positionDao.listPosition("Analyst", 1000, 20000).forEach(System.out::println);
        assertEquals(2, positionDao.listPosition("Analyst", 1000, 20000).size());
    }
    @Test
    void cauE1() throws RemoteException {
        positionDao.listYearsOfExperienceByPosition("C111").forEach((k, v) -> System.out.println(k + " " + v));
    }
    @Test
    void cauE2() throws RemoteException {
        assertNull(positionDao.listYearsOfExperienceByPosition("C111"), "listYearsOfExperienceByPosition() should not return null");
    }

}