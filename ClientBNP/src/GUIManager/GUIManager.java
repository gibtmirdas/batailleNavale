/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUIManager;

import connection.ClientConnection;
import java.awt.Dimension;
import javax.swing.JPanel;
import panels.Canvas;
import window.ConnectWindow;
import window.FrameMain;
import window.LauncherFrame;
import window.LoginFrame;
import window.WinSubscribe;

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
    public static GUIManager getInstance(){
        if(instance == null){
            instance = new GUIManager();
        }
        return instance;
    }
    
    public void launchConnectWindow(ClientConnection conn){
        frame.cleanView();
        frame.setView(new ConnectWindow(conn));
    };
    
    public void launchMainFrame(){
        frame.cleanView();
        frame.setView(new FrameMain(FrameMain.canvasWidth, FrameMain.canvasHeight));
    };
    
    public void launchLoginFrame(ClientConnection conn){
        frame.cleanView();
        frame.setTitle("login bataille navale");
        
        frame.setSize(new Dimension(600,150));
        frame.setView(new LoginFrame(conn));       
    };
    public void launchSubscribeFrame(ClientConnection conn){
        frame.cleanView();
        frame.setTitle("subscribe");
        frame.setPreferredSize(new Dimension(300,200));

        frame.setView(new WinSubscribe(conn));
    };
    
    public void launchShop(){/*not done yet*/};
    /**
     * 
     * @return canvas of the current window if there's one 
     */
    public Canvas getCurrentCanvas(){
        if(current_panel instanceof FrameMain){
            return ((FrameMain) current_panel).getCanvas();
        }
        return null;
    }
    
    
}
