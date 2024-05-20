package dao.Impl;

import entity.Candidate;
import entity.Certificate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class candidateImpl extends UnicastRemoteObject implements dao.CandidateDao{
    private EntityManager em;

    public candidateImpl() throws RemoteException {

        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }

    @Override
    public Map<Candidate, Long> listCandidateByCompanies() throws RemoteException {
        Map<Candidate, Long> result = new LinkedHashMap<>();
        String query = "SELECT c, COUNT(e) FROM Experience e JOIN e.candidate_id c GROUP BY c";
        List<?> list = em.createQuery(query).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            result.put((Candidate) arr[0], (Long) arr[1]);
        }
        return result;
    }

    @Override
    public Map<Candidate, Position> listCandidatesWithLongestWorking() throws RemoteException{
        Map<Candidate, Position> result = new LinkedHashMap<>();
        String query = "SELECT c, p FROM Experience e JOIN e.candidate_id c JOIN c.position p WHERE e.toDate - e.fromDate = (SELECT MAX(e1.toDate - e1.fromDate) FROM Experience e1 WHERE e1.candidate_id = c)";
        List<?> list = em.createQuery(query).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            result.put((Candidate) arr[0], (Position) arr[1]);
        }
        return result;
    }

    @Override
    public boolean addCandidate(Candidate candidate) throws RemoteException{
        if (!candidate.getCandidate_id()
                .matches("C\\d{3,}")) {
            return false;
        }

        try {
            em.getTransaction().begin();
            em.persist(candidate);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Map<Candidate, Set<Certificate>> listCandidatesWithCertificates() throws RemoteException{
        Map<Candidate, Set<Certificate>> result = new LinkedHashMap<>();
        String query = "SELECT c, ce FROM Certificate ce JOIN ce.candidate c";
        List<?> list = em.createQuery(query).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            Candidate c = (Candidate) arr[0];
            Certificate ce = (Certificate) arr[1];
            if (result.containsKey(c))
            {
                result.get(c).add(ce);
            }
            else
            {
                Set<Certificate> set = new HashSet<>();
                set.add(ce);
                result.put(c, set);
            }
        }
        return result;
    }


}
