package gnwf.vo;

import java.io.Serializable;

public class PicXY implements Serializable{
	private static final long serialVersionUID = -1808264719834695864L;
	
	private int dx1;
	private int dy1;
	private int dx2;
	private int dy2;
	public int getDx1() {
		return dx1;
	}
	public void setDx1(int dx1) {
		this.dx1 = dx1;
	}
	public int getDy1() {
		return dy1;
	}
	public void setDy1(int dy1) {
		this.dy1 = dy1;
	}
	public int getDx2() {
		return dx2;
	}
	public void setDx2(int dx2) {
		this.dx2 = dx2;
	}
	public int getDy2() {
		return dy2;
	}
	public void setDy2(int dy2) {
		this.dy2 = dy2;
	}
	
	public PicXY(){
		
	}
	public PicXY(int dx1, int dy1, int dx2, int dy2) {
		this.dx1 = dx1;
		this.dy1 = dy1;
		this.dx2 = dx2;
		this.dy2 = dy2;
	}
	@Override
	public String toString() {
		return "PicXY [dx1=" + dx1 + ", dy1=" + dy1 + ", dx2=" + dx2 + ", dy2="
				+ dy2 + "]";
	}
}
