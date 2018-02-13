package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame  {
	
	private boolean isRunning = true;
	private int fps = 1000;
	private int windowWidth = 800;
	private int windowHeight = 800;
	
	private BufferedImage backBuffer;
	private Insets insets;
	
	
	BufferedImage img;
	GeneticImage test;
	long bestScore;
	long maxScore;
	
	private Main() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
	private void run() {
		initialize();
		
		while(isRunning) 
        { 
            long time = System.currentTimeMillis(); 

            render(); 

            time = (1000 / fps) - (System.currentTimeMillis() - time); 

            if (time > 0) 
            { 
                try 
                { 
                	Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
        } 

        setVisible(false); 
	}
	
	private void initialize() {
		setTitle("Genetic Images");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right,
				insets.top + windowHeight + insets.bottom);
		
		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
		
		try {
			img = ImageIO.read(new File("res/test1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		test = new GeneticImage(img.getWidth(), img.getHeight(), GeneticImage.SHAPE_CIRCLES);
		bestScore = test.fitness(img);
		maxScore = GeneticImage.MaxFitness(img);
	}
	
	private void render() {		
		Graphics g = getGraphics();
		Graphics bgg = backBuffer.getGraphics();
		
		bgg.setColor(Color.white);
		bgg.fillRect(0, 0, windowWidth, windowHeight);
		
		GeneticImage test2 = test.clone();
		test2.mutate();
		test2.render();
		long score = test2.fitness(img);
		
		if (score < bestScore) {
			bestScore = score;
			test = test2;
		}
				
		setTitle(String.format("Fitness: %.2f%%", 100.0 - 100.0 * bestScore / maxScore));
		
		test.render();
		bgg.drawImage(test.getImage(), 0, 0, img.getWidth(), img.getHeight(), null);
		bgg.drawImage(test2.getImage(), 0, img.getHeight(), img.getWidth(), img.getHeight(), null);
		bgg.drawImage(img, img.getWidth(), 0, img.getWidth(), img.getHeight(), null);
		
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	public static void main (String[] args) {
		// Initialize Window
		
		Main game = new Main();
		game.run();
		System.exit(0);
	}
}