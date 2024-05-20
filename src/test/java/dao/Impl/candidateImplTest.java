package dao.Impl;

import dao.positionDao;
import entity.Position;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.*;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class candidateImplTest {
    static dao.CandidateDao candidateDao;

    @BeforeAll
    static void setUp() throws RemoteException {
        candidateDao = new candidateImpl();
    }

    @AfterAll
    static void tearDown() {
        candidateDao = null;
    }
    @Test
    void cauB() throws RemoteException {
//        candidateDao.listCandidateByCompanies().forEach((k, v) -> System.out.println(k + " " + v));
        assertEquals(7, candidateDao.listCandidateByCompanies().size());
    }
    @Test
    void cauB2() throws RemoteException {
        assertNotNull(candidateDao.listCandidateByCompanies(), "listCandidateByCompanies() should not return null");
    }
    @Test
    void cauB3() throws RemoteException {
        assertNull(candidateDao.listCandidateByCompanies(), "listCandidateByCompanies() should not return null");
    }
    @Test
    void cauC1() throws RemoteException {
//        candidateDao.listCandidatesWithLongestWorking().forEach((k, v) -> System.out.println(k + " " + v));
        assertEquals(3, candidateDao.listCandidatesWithLongestWorking().size());
    }
    @Test
    void cauC2() throws RemoteException {
        assertNotNull(candidateDao.listCandidatesWithLongestWorking(), "listCandidatesWithLongestWorking() should not return null");
    }
    @Test
    void cauD1() throws RemoteException {
        assertTrue(candidateDao.addCandidate(new entity.Candidate("C09", "Nguyen Van C", 1998, "Male", "khoi@gmail.com", "0987654321", "Good", new Position("P101"))));
    }
    @Test
    void cauD2() throws RemoteException {
        assertEquals(false,candidateDao.addCandidate(new entity.Candidate("C111", "Pham Dang Khoi", 2003, "Male", "khoi@gmail.com", "0987654321", "Good", new Position("P101"))));
    }
    @Test
    void cauD3() throws RemoteException {
        assertTrue(candidateDao.addCandidate(new entity.Candidate("C112", "Pham Dang Khoi", 2003, "Male", "khoi@gmail.com", "0987654321", "Good", new Position("P101"))));
    }
    @Test
    void cauF1() throws RemoteException {
        candidateDao.listCandidatesWithCertificates().forEach((k, v) -> System.out.println(k + " " + v + "\n"));
    }
    @Test
    void cauF2() throws RemoteException {
        assertNotNull(candidateDao.listCandidatesWithCertificates(), "listCandidatesWithCertificates() should not return null");
    }
    @Test
    void cauF3() throws RemoteException {
        assertNull(candidateDao.listCandidatesWithCertificates(), "listCandidatesWithCertificates() should not return null");
    }

}