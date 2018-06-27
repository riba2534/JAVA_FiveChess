package FiveChess;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
	
	// 画线的JPanel
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);// 设置第一条线的颜色
		for (int i = 0; i < 15; i++) {
			g.drawLine(50 + 44 * i, 50, 50 + 44 * i, 666);// 画第一条线 点(50,50) 到点 (100,100)
			g.drawLine(50, 50 + 44 * i, 666, 50 + 44 * i);// 画第一条线 点(50,50) 到点 (100,100)
		}
		//画棋盘上的小黑点
		g.setColor(Color.BLACK);
		g.fillOval(44 * 4 + 1, 44 * 4 + 1, 10, 10);
		g.fillOval(44 * 4 + 1, 44 * 12 + 1, 10, 10);
		g.fillOval(44 * 12 + 1, 44 * 4 + 1, 10, 10);
		g.fillOval(44 * 12 + 1, 44 * 12 + 1, 10, 10);
		g.fillOval(44 * 8 + 1, 44 * 8 + 1, 10, 10);
	}

}
