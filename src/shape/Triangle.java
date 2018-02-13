package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.GeneticImage;

public class Triangle extends Shape {
	
	double[] xs;
	double[] ys;
	
	Triangle(double x1, double y1, double x2, double y2, double x3, double y3, float r, float g, float b, float a) {
		super(r, g, b, a);
		this.xs = new double[3];
		xs[0] = x1;
		xs[1] = x2;
		xs[2] = x3;
		this.ys = new double[3];
		ys[0] = y1;
		ys[1] = y2;
		ys[2] = y3;
	}
	
	public static Triangle RandomTriangle() {
		Random r = new Random();
		Triangle triangle = new Triangle(
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextFloat(),
				r.nextFloat(),
				r.nextFloat(),
				r.nextFloat()
				);
		return triangle;
	}
	
	public static Triangle ClearTriangle() {
		Random r = new Random();
		Triangle triangle = new Triangle(
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble(),
				0.0f,
				0.0f,
				0.0f,
				0.0f
				);
		return triangle;
	}
	
	@Override
	public void mutate(float mutateRate) {
		Random r = new Random();
		if (r.nextFloat() < mutateRate) {
			if (r.nextBoolean()) {
				randomXY(r);
			} else {
				randomColor(r);
			}
		} else {
			if (r.nextBoolean()) {
				changeXY(r);
			} else {
				changeColor(r);
			}
		}
	}
	
	private void randomXY(Random r) {
		for (int i = 0; i < xs.length; i ++) {
			xs[i] = r.nextDouble();
			ys[i] = r.nextDouble();
		}
	}
	
	private void changeXY(Random rand) {
		for (int i = 0; i < xs.length; i++) {
			xs[i] += rand.nextGaussian() * 0.01f;
			ys[i] += rand.nextGaussian() * 0.01f;
		}
	}
	
	@Override
	public void draw(GeneticImage gi, Graphics graphics) {
		graphics.setColor(new Color(r, g, b, a));
		int[] xs = new int[this.xs.length];
		int[] ys = new int[this.ys.length];
		for (int i = 0; i < xs.length; i ++) {
			xs[i] = (int)Math.round(this.xs[i] * gi.getImage().getWidth());
			ys[i] = (int)Math.round(this.ys[i] * gi.getImage().getHeight());
		}
		graphics.fillPolygon(xs, ys, 3);
	}
	
	@Override
	public Triangle clone() {
		return new Triangle(xs[0], ys[0], xs[1], ys[1], xs[2], ys[2], r, g, b, a);
	}
}
