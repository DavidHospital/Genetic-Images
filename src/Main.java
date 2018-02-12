
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import stuff.GeneticImage;

@SuppressWarnings("serial")
public class Main extends JFrame  {
	
	private boolean isRunning = true;
	private int fps = 60;
	private int windowWidth = 800;
	private int windowHeight = 600;
	
	private BufferedImage backBuffer;
	private Insets insets;
	
	
	GeneticImage test;
	
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
		
		
		test = new GeneticImage();

	}
	
	private void render() {		
		Graphics g = getGraphics();
		Graphics bgg = backBuffer.getGraphics();
		
		test.render();
		BufferedImage img = test.getImage();
		bgg.drawImage(img, 0, 0, 200, 200, null);
		
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	public static void main (String[] args) {
		// Initialize Window
		
		Main game = new Main();
		game.run();
		System.exit(0);
	}
}