package shape;

import java.awt.Graphics;
import java.util.Random;

import main.GeneticImage;

public abstract class Shape {
	
	protected float r;
	protected float g;
	protected float b;
	protected float a;
	
	public static Shape RandomTriangle() {
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
	
	public static Shape ClearTriangle() {
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
	
	public static Shape RandomCircle() {
		Random r = new Random();
		Circle circle = new Circle(
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble() * 0.5,
				r.nextFloat(),
				r.nextFloat(),
				r.nextFloat(),
				r.nextFloat()
				);
		return circle;
	}
	
	public static Shape ClearCircle() {
		Random r = new Random();
		Circle circle = new Circle(
				r.nextDouble(),
				r.nextDouble(),
				r.nextDouble() * 0.5,
				0.0f,
				0.0f,
				0.0f,
				0.0f
				);
		return circle;
	}
	
	public Shape(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public abstract void draw(GeneticImage gi, Graphics graphics);
	public abstract void mutate(float mutationRate);
	public abstract Shape clone();
	
	protected void randomColor(Random rand) {
		r = rand.nextFloat();
		g = rand.nextFloat();
		b = rand.nextFloat();
		a = rand.nextFloat();
	}
	
	protected void changeColor(Random rand) {
		r = (float) Math.max(0, Math.min(1, r + rand.nextGaussian()));
		g = (float) Math.max(0, Math.min(1, g + rand.nextGaussian()));
		b = (float) Math.max(0, Math.min(1, b + rand.nextGaussian()));
		a = (float) Math.max(0, Math.min(1, a + rand.nextGaussian()));
	}

}
