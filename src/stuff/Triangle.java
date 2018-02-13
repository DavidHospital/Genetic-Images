package stuff;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle {

	int[] xs;
	int[] ys;
	
	float r, g, b, a;
	
	public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, float r, float g, float b, float a) {
		this.xs = new int[3];
		xs[0] = x1;
		xs[1] = x2;
		xs[2] = x3;
		this.ys = new int[3];
		ys[0] = y1;
		ys[1] = y2;
		ys[2] = y3;
		
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public static Triangle RandomTriangle(int width, int height) {
		Triangle triangle = new Triangle(
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(float)(Math.random()),
				(float)(Math.random()),
				(float)(Math.random()),
				(float)(Math.random())
				);
		return triangle;
	}
	
	public static Triangle ClearTriangle(int width, int height) {
		Triangle triangle = new Triangle(
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(int)(width * Math.random()),
				(int)(height * Math.random()),
				(float)(0.0),
				(float)(0.0),
				(float)(0.0),
				(float)(0.0)
				);
		return triangle;
	}
	
	public void randomXY() {
		int[] dxs = {
				(int)(Math.random() * 10 - 5),
				(int)(Math.random() * 10 - 5),
				(int)(Math.random() * 10 - 5)
		};
		int[] dys = {
				(int)(Math.random() * 10 - 5),
				(int)(Math.random() * 10 - 5),
				(int)(Math.random() * 10 - 5)
		};
		changeXY(dxs, dys);
	}
	
	public void randomColor() {
		float dr = (float)(Math.random() * 0.1 - 0.05);
		float dg = (float)(Math.random() * 0.1 - 0.05);
		float db = (float)(Math.random() * 0.1 - 0.05);
		float da = (float)(Math.random() * 0.1 - 0.05);
		changeColor(dr, dg, db, da);
	}
	
	public void changeXY(int[] dxs, int[] dys) {
		for (int i = 0; i < 3; i++) {
			xs[i] += dxs[i];
			ys[i] += dys[i];
		}
	}
	
	public void changeColor(float dr, float dg, float db, float da) {
		r = Math.max(0, Math.min(1, r + dr));
		g = Math.max(0, Math.min(1, g + dg));
		b = Math.max(0, Math.min(1, b + db));
		a = Math.max(0, Math.min(1, a + da));
	}
	
	public void draw(Graphics graphics) {
		graphics.setColor(new Color(r, g, b, a));
		graphics.fillPolygon(xs, ys, 3);
	}
	
	public Triangle clone() {
		return new Triangle(xs[0], ys[0], xs[1], ys[1], xs[2], ys[2], r, g, b, a);
	}
}
