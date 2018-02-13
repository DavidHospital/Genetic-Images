package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import main.GeneticImage;

public class Circle extends Shape {

	double x;
	double y;
	double radius;
	
	Circle(double x, double y, double radius, float r, float g, float b, float a) {
		super(r, g, b, a);
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public void draw(GeneticImage gi, Graphics graphics) {
		graphics.setColor(new Color(r, g, b, a));
		int width = gi.getImage().getWidth();
		int height = gi.getImage().getHeight();
		graphics.fillOval(
				(int)((x - r) * width),
				(int)((y - r) * height),
				(int)((r * 2) * width),
				(int)((r * 2) * height)
				);
	}

	@Override
	public void mutate(float mutateRate) {
		Random r = new Random();
		if (r.nextFloat() < mutateRate) {
			if (r.nextBoolean()) {
				randomXYR(r);
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
	
	private void randomXYR(Random r) {
		x = r.nextDouble();
		y = r.nextDouble();
		radius = r.nextDouble() * 0.5;
	}
	
	private void changeXY(Random rand) {
		x += rand.nextGaussian() * 0.01f;
		y += rand.nextGaussian() * 0.01f;
		radius += rand.nextGaussian() * 0.01f;
	}
	
	@Override
	public Circle clone() {
		return new Circle(x, y, radius, r, g, b, a);
	}
	
	
}
