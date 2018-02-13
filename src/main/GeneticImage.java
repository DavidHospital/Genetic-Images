package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import shape.Shape;
import shape.Triangle;

public class GeneticImage {

	
	public static final int SHAPE_TRIANGLES = 0;
	public static final int SHAPE_CIRCLES = 1;
	
	BufferedImage img;
	
	ArrayList<Shape> shapes;
	
	public static long MaxFitness(BufferedImage img) {
		int score = 0;
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color c = new Color(img.getRGB(i, j));
				score += (int)Math.max(Math.abs(c.getRed() - 0), Math.abs(c.getRed() - 255));
				score += (int)Math.max(Math.abs(c.getGreen() - 0), Math.abs(c.getGreen() - 255));
				score += (int)Math.max(Math.abs(c.getBlue() - 0), Math.abs(c.getBlue() - 255));
			}
		}
		return score;
	}
	
	private GeneticImage(int width, int height) {	
		shapes = new ArrayList<Shape>();
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public GeneticImage(int width, int height, int shapeType) {
		this(width, height);
		
		for (int i = 0; i < 100; i ++) {
			if (shapeType == SHAPE_TRIANGLES) {
				shapes.add(Triangle.RandomTriangle());
			} else if (shapeType == SHAPE_CIRCLES) {
				shapes.add(Triangle.RandomCircle());
			}
		}
	}
	
	public void render() {
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.WHITE);
		imgG.fillRect(0, 0, img.getWidth(), img.getHeight());
		for(Shape s : shapes) {
			s.draw(this, imgG);
		}
	}
	
	public void mutate() {
		Random r = new Random();
		int index = r.nextInt(shapes.size());
		
		shapes.get(index).mutate(0.05f);
	}
	
	public long fitness(BufferedImage img) {
		int score = 0;
		for (int i = 0; i < this.img.getWidth(); i++) {
			for (int j = 0; j < this.img.getHeight(); j++) {
				Color c1 = new Color(this.img.getRGB(i, j));
				Color c2 = new Color(img.getRGB(i, j));
				score += (int)Math.abs(c1.getRed() - c2.getRed());
				score += (int)Math.abs(c1.getGreen() - c2.getGreen());
				score += (int)Math.abs(c1.getBlue() - c2.getBlue());
			}
		}
		return score;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public GeneticImage clone() {
		GeneticImage result = new GeneticImage(img.getWidth(), img.getHeight());
		for (int i = 0; i < this.shapes.size(); i ++) {
			result.shapes.add(this.shapes.get(i).clone());
		}
		return result;
	}
}
