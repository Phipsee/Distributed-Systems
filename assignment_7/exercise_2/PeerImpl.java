package exercise_2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeerImpl extends UnicastRemoteObject implements Peer {

	protected PeerImpl() throws RemoteException {
	}
	
	public static Peer myPeer;
	
	private Peer leftPeer;
	private Peer rightPeer;
	public Long id;
	
	
	private static final long serialVersionUID = -2093316910639547613L;

	public static void main(String[] args) {
		try {
			getInstance().join();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void beTheSuperPeer() {
		try {
			UnicastRemoteObject.unexportObject(getInstance(), true);
			Peer stub = (Peer) UnicastRemoteObject.exportObject(getInstance(), 0);

			Registry registry = LocateRegistry.createRegistry(3434);
			registry.rebind("Peer", stub);

			stub.setLeftNeighbour(stub);
			stub.setRightNeighbour(stub);
			System.out.println("NOW I AM THE SUPERPEER");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Peer getLeftNeighbour() throws RemoteException {
		return leftPeer;
	}

	@Override
	public Peer getRightNeighbour() throws RemoteException {
		return rightPeer;
	}

	@Override
	public Peer join() throws RemoteException {

		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(3434);

			Peer stub = (Peer) registry.lookup("Peer");
			
			Peer oldLeft = stub.getLeftNeighbour();
			stub.setLeftNeighbour(getInstance());
			getInstance().setLeftNeighbour(oldLeft);
			getInstance().setRightNeighbour(stub);
			oldLeft.setRightNeighbour(getInstance());
			
			System.out.println("To my left"+getLeftNeighbour());
			System.out.println("To my right"+getRightNeighbour());
			
			List<Long> ids = new ArrayList<>();
			countPeers(ids);
		}catch (RemoteException | NotBoundException e) {
			System.out.println("No superPeer found");
			beTheSuperPeer();
		}
	return null;
	}

	@Override
	public Peer setLeftNeighbour(Peer left) throws RemoteException {
		leftPeer = left;
		return leftPeer;
	}

	@Override
	public Peer setRightNeighbour(Peer right) throws RemoteException {
		rightPeer = right;
		return rightPeer;
	}
	
	private static Peer getInstance() {
		if(myPeer == null) {
			try {
				myPeer = new PeerImpl();
				myPeer.setId(new Date().getTime());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return myPeer;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long setId(Long id) throws RemoteException {
		this.id = id;
		return this.id;
	}
	
	@Override
	public String toString() {
		return "Peer - "+getId();
	}

	@Override
	public void countPeers(List<Long> ids) throws RemoteException {
		if(ids.contains(getId())) {
			System.out.println("Nodes in network: "+ids.size());
			return;
		}
		ids.add(getId());
		getLeftNeighbour().countPeers(ids);
	}
}
