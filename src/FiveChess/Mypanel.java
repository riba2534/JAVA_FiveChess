/*重写panel时间来实现更换背景的效果*/
package FiveChess;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Mypanel extends JPanel {

	BufferedImage image;

	public Mypanel() {
		super();
		try {
			image = ImageIO.read(getClass().getResource("begin.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		}
	}

}
