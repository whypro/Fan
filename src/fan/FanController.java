package fan;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import fan.FanModel.Direction;
import fan.FanModel.State;

public class FanController extends JFrame implements Runnable, ActionListener, ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5040358312656252116L;
	
	FanModel theFanModel;	
	FanView theFanView;
	JButton buttonOn, buttonOff;
	JSlider sliderSpeed;
	JPanel controlPanel;
	
	JButton buttonDirection;
	
	// ����ҶƬ����
	private int bladesNumber = 90;
	// ��һ��ҶƬ�ĵ�ǰλ��
	private int bladePos = 0;
	
	// ����״̬ 
	private State state = FanModel.State.OFF;

	private Direction direction = FanModel.Direction.CCW;
	
	// revolutions per second
	double speed = 0.5;
	// frames per second
	int FPS = 24;
	


	public FanController() {
		 
		// ���ؼ�
		buttonOn = new JButton("ON");
		buttonOff = new JButton("OFF");
		buttonDirection = new JButton("ChangeDirection");
		buttonOn.addActionListener(this);
		buttonOff.addActionListener(this);
		buttonDirection.addActionListener(this);
		buttonOn.setEnabled(true);
		buttonOff.setEnabled(false);
		
		// �ٶȻ���
		sliderSpeed = new JSlider(JSlider.HORIZONTAL, 10, 40, 25);
		//sliderSpeed.setMajorTickSpacing(10);
		//sliderSpeed.setPaintLabels(true);
		sliderSpeed.addChangeListener(this);
		
		// �������
		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(buttonOn, BorderLayout.EAST);
		controlPanel.add(buttonOff, BorderLayout.WEST);
		controlPanel.add(buttonDirection, BorderLayout.NORTH);
		controlPanel.add(sliderSpeed, BorderLayout.CENTER);
		
		speed = (double)sliderSpeed.getValue() / 100;
		FPS = (int) (speed * 360);
		
		theFanModel = new FanModel(
				bladesNumber, bladePos, 
				state, direction, speed);
		theFanView = new FanView(theFanModel);
		theFanModel.addObserver(theFanView);
		
		//theFanView.setBackground(Color.BLACK);
		//controlPanel.setBackground(Color.BLACK);
		//sliderSpeed.setBackground(Color.BLACK);

		//theFanView.update(theFanModel, null);

		setTitle("Fan");

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600); 
		// ȡ����Ļ��Ⱥ͸߶�
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth(); 
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight(); 
		// ������ʾ
		setLocation((int) (width - this.getWidth()) / 2, 
				(int) (height - this.getHeight()) / 2); 
		
		setLayout(new BorderLayout());
		add(theFanView, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		
		
		// setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonOn) {
			theFanModel.on();
			buttonOn.setEnabled(false);
			buttonOff.setEnabled(true);
			
		}
		else if (e.getSource() == buttonOff) {
			theFanModel.off();
			buttonOff.setEnabled(false);
			buttonOn.setEnabled(true);
			
			/*
			Image imageL = Toolkit.getDefaultToolkit().getImage("love.xxx"); 
			Image imageSL = imageL.getScaledInstance(theFanView.getWidth(), theFanView.getHeight(), Image.SCALE_DEFAULT);
			ImageIcon icon = new ImageIcon(imageSL);
			
			JLabel love = new JLabel();
			theFanView.setLayout(new BorderLayout());
			theFanView.add(love, BorderLayout.CENTER);
			love.setIcon(icon);
			*/

		}
		else if (e.getSource() == buttonDirection) {
			if (theFanModel.getDirection() == Direction.CW) {
				theFanModel.setDirection(Direction.CCW);
			} else if (theFanModel.getDirection() == Direction.CCW) {
				theFanModel.setDirection(Direction.CW);
			}
			
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == sliderSpeed) {
			int temp = sliderSpeed.getValue();
			if (temp == 0) {
				sliderSpeed.setValue(1);
				return;
			}
			speed = ((double) temp / 100);
			FPS = (int) (speed * 360);
			theFanModel.setSpeed(speed);
		}
		
	}

	@Override
	public void run() {
		try {
			while (true) {
				int bladePos = theFanModel.getBladePos();
				double speed = theFanModel.getSpeed();
				Direction direction = theFanModel.getDirection();
				State state = theFanModel.getState();
				if (state == State.ON) {
					switch (direction) {
					case CW:
						bladePos -= (int) (speed * 360 / FPS);
						//bladePos -= (int) (speed);
						theFanModel.setBladePos(bladePos);
						break;
					case CCW:
						bladePos += (int) (speed * 360 / FPS);
						//bladePos += (int) (speed);
						theFanModel.setBladePos(bladePos);
						break;
					}
					if (bladePos >= 360 || bladePos <= -360) {
						bladePos = bladePos % 360;
						theFanModel.setBladePos(bladePos);
					}
					theFanModel.radius++;
					theFanModel.changeModel();
				} else {

				}
				Thread.sleep(1000 / FPS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




