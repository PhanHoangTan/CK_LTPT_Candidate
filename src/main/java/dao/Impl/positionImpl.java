package dao.Impl;

import dao.positionDao;
import entity.Candidate;
import entity.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class positionImpl extends UnicastRemoteObject implements positionDao {
   private EntityManager em;

   public positionImpl() throws RemoteException {
       em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }
    @Override
    public List<Position> listPosition(String name, double salaryFrom, double salaryTo) throws RemoteException {
        return em.createQuery("SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary >= :salaryFrom AND p.salary <= :salaryTo", Position.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("salaryFrom", salaryFrom)
                .setParameter("salaryTo", salaryTo)
                .getResultList();
    }
    @Override

    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID) throws RemoteException{
        Map<Position, Integer> result = new LinkedHashMap<>();
        String query = "SELECT p, YEAR(e.toDate) - YEAR(e.fromDate) FROM Experience e JOIN e.candidate_id c JOIN e.position_id p WHERE c.candidate_id = :candidateID";
        List<?> list = em.createQuery(query).setParameter("candidateID", candidateID).getResultList();
        for (Object o : list)
        {
            Object[] arr = (Object[]) o;
            result.put((Position) arr[0], (Integer) arr[1]);
        }
        return result;
    }


}
