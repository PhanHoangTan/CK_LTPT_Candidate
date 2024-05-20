package Server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.text.Format;
import java.util.List;
import java.util.Map;

import dao.Impl.candidateImpl;
import dao.Impl.positionImpl;
import dao.positionDao;
import dao.CandidateDao;
import entity.Position;


public class server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(2951)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                System.out.println("Client IP: " + socket.getInetAddress().getHostAddress());
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}
class ClientHandler implements Runnable {
    private Socket socket;
    private CandidateDao candidateDao;
    private positionDao positionDao;


    public ClientHandler(Socket socket) throws RemoteException {
        this.socket = socket;
        candidateDao = new candidateImpl();
        positionDao = new positionImpl();

    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            int choice = 0;
            while (true)
            {
                try {
                    choice = dis.readInt();
                } catch (EOFException e) {
                    System.out.println("End of stream reached unexpectedly");
                    break;
                }
                switch (choice)
                {
                    case 1:
                        String name = dis.readUTF();
                        double salaryFrom = dis.readDouble();
                        double salaryTo = dis.readDouble();
                        System.out.println("Cau A: " + name + " " + salaryFrom + " " + salaryTo);
                        List<Position> positionList = positionDao.listPosition(name, salaryFrom, salaryTo);
                        oos.reset();  // Reset the stream before writing the object
                        oos.writeObject(positionList);
                        positionList.forEach(System.out::println);
                        break;
                    case 2:
                        String candidateID = dis.readUTF();
                        System.out.println("Cau B: " + candidateID);
                        oos.writeObject(positionDao.listYearsOfExperienceByPosition(candidateID));
                        break;
                    case 3:
                        System.out.println("Cau C");
                        break;
                    case 4:
                        System.out.println("Cau D");
                        break;
                    case 5:
                        System.out.println("Cau E");
                        break;
                    case 6:
                        System.out.println("Cau F");
                        break;
                    case 7:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
