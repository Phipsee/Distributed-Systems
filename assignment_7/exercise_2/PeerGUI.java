package exercise_2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class PeerGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	JLabel leftPeerTxt, rightPeerTxt, peerId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeerGUI frame = new PeerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void update() {
		try {
			leftPeerTxt.setText("" + PeerImpl.getInstance().getLeftNeighbour().getId());
			rightPeerTxt.setText("" + PeerImpl.getInstance().getRightNeighbour().getId());
			peerId.setText("" + PeerImpl.getInstance().getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public PeerGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnConnect = new JButton("Connect");

		JLabel lblLeftPeer = new JLabel("Left Peer");

		JLabel lblRightPeer = new JLabel("Right Peer");

		leftPeerTxt = new JLabel(" ");

		rightPeerTxt = new JLabel(" ");

		JButton btnUpdate = new JButton("Update");
		

		JLabel lblMyId = new JLabel("My Id");

		peerId = new JLabel(" ");
		
		JButton btnLeave = new JButton("Leave");
	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblLeftPeer)
								.addComponent(leftPeerTxt))
							.addGap(113))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLeave)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMyId)
								.addComponent(peerId))
							.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rightPeerTxt)
								.addComponent(lblRightPeer))
							.addGap(49))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnConnect)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdate)
							.addContainerGap(102, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLeftPeer)
						.addComponent(lblRightPeer)
						.addComponent(lblMyId))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(leftPeerTxt)
						.addComponent(rightPeerTxt)
						.addComponent(peerId))
					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConnect)
						.addComponent(btnUpdate)
						.addComponent(btnLeave))
					.addGap(35))
		);
		contentPane.setLayout(gl_contentPane);

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PeerImpl.getInstance().join();
					update();
				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});

		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PeerImpl.getInstance().leave();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
