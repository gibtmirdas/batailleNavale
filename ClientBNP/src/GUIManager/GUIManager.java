/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUIManager;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import packet.Packet;

import panels.Canvas;
import panels.ContainerCartes;
import window.ConnectWindow;
import window.FrameMain;
import window.LauncherFrame;
import window.LauncherTabs;
import window.LoginFrame;
import window.PanelNotifiable;
import window.WinSubscribe;
import connection.ClientConnection;

/**
 * 
 * @author antho
 */
public final class GUIManager {
	private final LauncherFrame frame;
	private JPanel current_panel;
	private static GUIManager instance;

	private GUIManager() {
		this.frame = new LauncherFrame();
	}

	public static GUIManager getInstance() {
		if (instance == null) {
			instance = new GUIManager();
		}
		return instance;
	}

	public void launchConnectWindow(ClientConnection conn, String[][] list) {
		frame.cleanView();
		current_panel = new ConnectWindow(conn, list);
		frame.setView(current_panel);
	};

	public void launchMainFrame(int idPlayer) {
		System.out.println("------");
		frame.cleanView();
		frame.setSize(new Dimension(1000, 700));
		current_panel = new FrameMain(FrameMain.canvasWidth,
				FrameMain.canvasHeight, idPlayer);
		frame.setView(current_panel);
	};

	public void resize() {
		frame.setSize(FrameMain.canvasWidth + 1, FrameMain.canvasHeight + 100);
		frame.setSize(FrameMain.canvasWidth, FrameMain.canvasHeight + 100);
	}

	public void launchLoginFrame(ClientConnection conn) {
		frame.cleanView();
		frame.setTitle("login bataille navale");
		frame.setSize(new Dimension(600, 150));
		current_panel = new LoginFrame(conn);
		frame.setView(current_panel);
	};

	public void launchSubscribeFrame(ClientConnection conn) {
		frame.cleanView();
		frame.setTitle("subscribe");
		frame.setSize(new Dimension(600, 200));
		current_panel = new WinSubscribe(conn);
		frame.setView(current_panel);
	};

	public void launchShop() {
	};

	public void launchTab(ClientConnection conn) {
		frame.cleanView();
		frame.setResizable(true);
		frame.setSize(new Dimension(1000, 1000));
		current_panel = new LauncherTabs(1000, 1000, conn);
		frame.setView(current_panel);
	}

	/**
	 * 
	 * @return canvas of the current window if there's one
	 */
	public Canvas getCurrentCanvas() {
		if (current_panel instanceof FrameMain) {
			return ((FrameMain) current_panel).getCanvas();
		}
		return null;
	}

	/**
	 * 
	 * @return container cards
	 */
	public ContainerCartes getContainCard() {
		if (current_panel instanceof FrameMain) {
			return ((FrameMain) current_panel).getContainCarte();
		}
		return null;
	}

	public void buildAlertDialog(String title, String msg, boolean isOk) {
		int type = isOk ? JOptionPane.INFORMATION_MESSAGE
				: JOptionPane.ERROR_MESSAGE;
		JOptionPane.showMessageDialog(current_panel, msg, title, type);
	}
}
