package window;


import javax.swing.JFrame;

import panels.Canvas;

public class FrameMain extends JFrame{
	public static final int canvasWidth = 800, canvasHeight = 625;
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	public FrameMain(int widthCanvas, int heightCanvas){
    	canvas = new Canvas(widthCanvas,heightCanvas);
    	setTitle("BATAILLE NAVALE++");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	add(canvas);
    	pack();
        setVisible(true);
        setResizable(false);
    }

	public Canvas getCanvas() {
		return canvas;
	}
}
