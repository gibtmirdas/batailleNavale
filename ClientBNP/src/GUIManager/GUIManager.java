/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUIManager;

import com.sun.java.swing.plaf.windows.resources.windows;
import connection.ClientConnection;
import javax.swing.JFrame;
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
        frame.setView(new LoginFrame(conn));       
    };
    public void launchSubscribeFrame(ClientConnection conn){
        frame.cleanView();
        frame.setTitle("subscribe");
        frame.setView(new WinSubscribe(conn));
    };
    public void launchShop(){};
    /**
     * 
     * @return canvas of the current window if there's one 
     */
    public Canvas getCurrentCanvas(){
        /*if(current_panel instanceof FrameMain){
            return current_panel.getCanvas();
        }*/
        return null;
    }
    
    
}
