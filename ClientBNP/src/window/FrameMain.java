package window;

import javax.swing.JPanel;

import panels.Canvas;

public class FrameMain extends JPanel {
	public static final int canvasWidth = 800, canvasHeight = 625;
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	public static final String title = "BATAILLE NAVALE++";

	public FrameMain(int widthCanvas, int heightCanvas) {
		canvas = new Canvas(widthCanvas, heightCanvas);
		add(canvas);
		setVisible(true);
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
