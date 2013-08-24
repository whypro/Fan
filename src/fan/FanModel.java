package fan;

import java.util.Observable;

public class FanModel extends Observable {

	// 风扇叶片数量
	private int bladesNumber;
	// 第一个叶片的当前位置
	private int bladePos;
	// 风扇状态 = {开, 关}
	public enum State {
		ON, OFF
	};
	private State state;
	// 旋转方向 = {顺时针, 逆时针}
	public enum Direction {
		CW, CCW
	};
	private Direction direction;
	// 风扇转速 revolutions per second
	private double speed;
	
	public int radius = 0;

	
	public FanModel() {
		bladesNumber = 4;
		bladePos = 0;
		state = State.OFF;
		direction = Direction.CW;
		speed = 1.0;
	}

	public FanModel(int bladesNumber, int bladePos, State state,
			Direction direction, double speed) {
		this.bladesNumber = bladesNumber;
		this.bladePos = bladePos;
		this.state = state;
		this.direction = direction;
		this.speed = speed;
	}

	public void on() {
		if (state == State.ON) {
			return;
		} else {
			state = State.ON;
		}
	}

	public void off() {
		if (state == State.OFF) {
			return;
		} else {
			state = State.OFF;
		}
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setBladesNumber(int bladesNumber) {
		this.bladesNumber = bladesNumber;
	}

	public int getBladesNumber() {
		return bladesNumber;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return state;
	}

	public void setBladePos(int bladePos) {
		this.bladePos = bladePos;
	}

	public int getBladePos() {
		return bladePos;
	}

	// 当数据改变时，由Controller调用此方法，通知各个Observer,刷新视图.
	public void changeModel() {
		setChanged(); // Indicates that the model has changed
		notifyObservers();
	}
}
