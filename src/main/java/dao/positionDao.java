package dao;

import entity.Candidate;
import entity.Position;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface positionDao extends Remote {
    public List<Position> listPosition(String name, double salaryFrom, double salaryTo)throws RemoteException;
    public Map<Position, Integer> listYearsOfExperienceByPosition(String candidateID)throws RemoteException;

}
