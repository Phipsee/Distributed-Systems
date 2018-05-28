package exercise_2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Peer extends Remote {

	public Long getId() throws RemoteException;

	public Long setId(Long id) throws RemoteException;

	public Peer getLeftNeighbour() throws RemoteException;

	public Peer getRightNeighbour() throws RemoteException;

	public Peer join() throws RemoteException;

	public Peer setLeftNeighbour(Peer left) throws RemoteException;

	public Peer setRightNeighbour(Peer right) throws RemoteException;

	public void countPeers(List<Long> ids) throws RemoteException;

	public void leave() throws RemoteException;
}
