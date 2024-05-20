package dao;

import entity.Candidate;
import entity.Certificate;
import entity.Position;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

public interface CandidateDao extends Remote {
    public Map<Candidate, Long> listCandidateByCompanies() throws RemoteException;
    public Map<Candidate, Position> listCandidatesWithLongestWorking() throws RemoteException;
    public boolean addCandidate(Candidate candidate) throws RemoteException;
    public Map<Candidate, Set<Certificate>> listCandidatesWithCertificates() throws RemoteException;
}
