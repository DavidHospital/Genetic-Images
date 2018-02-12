package stuff;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle {

	int[] xs;
	int[] ys;
	
	Color color;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
		this.xs = new int[3];
		xs[0] = x1;
		xs[1] = x2;
		xs[2] = x3;
		this.ys = new int[3];
		ys[0] = y1;
		ys[1] = y2;
		ys[2] = y3;
		
		this.color = color;
	}
	
	public void changeXY(int[] dxs, int[] dys) {
		for (int i = 0; i < 3; i++) {
			xs[i] += dxs[i];
			ys[i] += dys[i];
		}
	}
	
	public void changeColor(Color color) {
		this.color = color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillPolygon(xs, ys, 3);
	}
}
