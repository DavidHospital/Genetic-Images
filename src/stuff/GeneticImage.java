package stuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GeneticImage {

	BufferedImage img;
	
	ArrayList<Triangle> triangles;
	
	public GeneticImage(int width, int height) {
		triangles = new ArrayList<Triangle>();
		
		for (int i = 0; i < 50; i ++) {
			triangles.add(Triangle.RandomTriangle(width, height));
		}
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render() {
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.WHITE);
		imgG.fillRect(0, 0, img.getWidth(), img.getHeight());
		for(Triangle t : triangles) {
			t.draw(imgG);
		}
	}
	
	public void mutate() {
		Random r = new Random();
		int index = r.nextInt(triangles.size());
		
		if (r.nextDouble() < 0.05) {
			triangles.set(index, Triangle.RandomTriangle(img.getWidth(), img.getHeight()));
		}
		
		if (r.nextBoolean()) {
			triangles.get(index).randomXY();
		} else {
			triangles.get(index).randomColor();
		}
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
		for (int i = 0; i < this.triangles.size(); i ++) {
			result.triangles.set(i, this.triangles.get(i).clone());
		}
		return result;
	}
}
