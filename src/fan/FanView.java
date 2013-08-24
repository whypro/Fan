package fan;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


class FanView extends JPanel implements Observer {
	
	private static final long serialVersionUID = 1464706177075587023L;
	
	FanModel theFanModel;
	Color color[] = new Color[9];
	
	public FanView(FanModel theFanModel) {
		this.theFanModel = theFanModel;

		color[0] = new Color(255, 0, 0);
		color[1] = new Color(255, 165, 0);
		color[2] = new Color(255, 255, 0);
		color[3] = new Color(0, 255, 0);
		color[4] = new Color(0, 127, 255);
		color[5] = new Color(0, 0, 255);
		color[6] = new Color(139, 0, 255);
		color[7] = new Color(139, 139, 255);
		color[8] = new Color(255, 139, 255);
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawImage(imageS,xHeart,yHeart,null);

		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		int radius = (int) (Math.min(getWidth(), getHeight()) * 0.45);
		
		int x = xCenter - radius;
		int y = yCenter - radius;
		
		int bladesNumber = theFanModel.getBladesNumber();
		int bladePos = theFanModel.getBladePos();

		// 一个叶片移动的区域
		int bladeArea = (int) (360 / bladesNumber);
		// 一个叶片的大小
		int bladeArc = (int) (bladeArea / 1);

		for (int i = 0; i < bladesNumber; i++) {
			// 填充叶片	
			g.setColor(color[i % 9]);
			g.fillArc(x, y, 2 * radius, 2 * radius, bladePos + i
					* bladeArea, bladeArc);
		}
	}
	
    public void update(Observable o, Object arg) {
    	this.theFanModel = (FanModel) o;
    	repaint();
    }
}