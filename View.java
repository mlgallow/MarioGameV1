import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	static Image[] mario_images = null;
	Image background;

	View(Controller c, Model m) {
		if(mario_images == null)
		{
			mario_images = new Image[5];
			model = m;
			c.setView(this);

			try {
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
				background = ImageIO.read(new File("super_mario_backgrounds_edit.png"));
			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

//Part 14 Project 1 adding color to the background of the game------------------

	public void paintComponent(Graphics g)
	{

		//Clear the screen
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());

		//Draw background image
		//model.scrollPos/-3 allows the image to move to the left as mario moves to the right
		g.drawImage(background,(model.scrollPos/-3) - 80, 0, null);

		// To be moved to model later (Drawing the ground)
		//g.setColor(new Color(139,69,19));
		//g.fillRect(0, 595, 900,700);

		// Sets the color of the bricks to black
		g.setColor(new Color(190, 79, 58));
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			// Draws a rectangle
			g.drawRect(b.x - model.scrollPos, b.y, b.w, b.h);
			g.fillRect(b.x - model.scrollPos, b.y, b.w, b.h);
		}

		//20 slows down how fast he runs and 5 is the number of frames we have to work with
		int marioFrame = (Math.abs(model.mario.x/20)) % 5;
		if(marioFrame > 5){
			marioFrame = 0;
		}
		// Draw/animate mario
		g.drawImage(mario_images[marioFrame], model.mario.x - model.scrollPos, model.mario.y, null);
	}	
}
