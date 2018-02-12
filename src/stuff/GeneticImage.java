package stuff;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GeneticImage {

	BufferedImage img;
	
	ArrayList<Triangle> triangles;
	
	public GeneticImage() {
		triangles = new ArrayList<Triangle>();
		
		int width = 200;
		int height = 200;
		
		for (int i = 0; i < 100; i ++) {
			triangles.add(new Triangle(
					(int)(width * Math.random()),
					(int)(height * Math.random()),
					(int)(width * Math.random()),
					(int)(height * Math.random()),
					(int)(width * Math.random()),
					(int)(height * Math.random()),
					Color.getHSBColor((float)Math.random(), (float)Math.random(), (float)Math.random())
					));
		}
		
		img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render() {
		img.getGraphics().dispose();
		for (Triangle t : triangles) {
			int[] dxs = { 5 - (int)(10 * Math.random()), 5 - (int)(10 * Math.random()), 5 - (int)(10 * Math.random()) };
			int[] dys = { 5 - (int)(10 * Math.random()), 5 - (int)(10 * Math.random()), 5 - (int)(10 * Math.random()) };
			t.changeXY(dxs, dys);
		}
		for(Triangle t : triangles) {
			t.draw(img.getGraphics());
		}
	}
	
	public BufferedImage getImage() {
		return img;
	}
}
