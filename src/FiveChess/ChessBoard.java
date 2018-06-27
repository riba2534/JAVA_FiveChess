package FiveChess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.awt.Font;

public class ChessBoard extends JFrame {

	boolean isVisible = true;
	char[][] qipan = new char[15][15];// 棋盘数组
	int gx, gy, connt = 1;
	Stack chessman = new Stack(509);// 存储下棋步数
	Win who = new Win();

	public ChessBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.UTILITY);
		setBounds(new Rectangle(0, 0, 714, 714));
		setTitle("AI五子棋----人机对战模式         提示：鼠标左键落子,右键悔棋,祝玩的开心");
		setResizable(false);
		ChessBoardPanel chessBoardPanel = new ChessBoardPanel();
		getContentPane().add(chessBoardPanel);// 绘制棋盘并添加到面板中
		chessBoardPanel.setLayout(null);
		// ************************************************
		this.addMouseListener(new MouseAdapter() { // 匿名内部类，鼠标事件
			public void mousePressed(MouseEvent e) { // 鼠标完成点击事件
				if (e.getButton() == MouseEvent.BUTTON1) { // e.getButton就会返回点鼠标的那个键，左键还是右健，3代表右键
					int x = e.getX(); // 得到鼠标x坐标
					int y = e.getY(); // 得到鼠标y坐标
					System.out.println("鼠标当前点击位置的坐标是" + x + "," + y);
					if ((28 < x && x < 688) && (56 < y && y < 719)) {// ((28 < x && x < 688) && (56 < y && y < 716))
						gx = (int) (x - 29) / 44;// 28
						gy = (int) (y - 60) / 44;// 56
						if (qipan[gx][gy] == '\0') {
							if (connt % 2 == 1) {
								steQipan(gx, gy, 'b');
							} else {
								steQipan(gx, gy, 'w');
							}
							connt++;
							Bot xb = new Bot(qipan, 'w');
							int[] xy = new int[2];
							xy = xb.getXY();
							steQipan(xy[0], xy[1], 'w');
							connt++;
							repaint();
							winDow();// 判断输赢
						}
					}
					System.out.println("鼠标当前点击位置的坐标是" + gx + "," + gy);
					repaint();
					winDow();// 判断输赢

				} else if (e.getButton() == MouseEvent.BUTTON3) {// 点击右键进行悔棋操作
					isVisible = true;
					if (!chessman.isEmpty()) {
						gy = chessman.pop();
						gx = chessman.pop();// 出栈
						steQipan(gx, gy, '\0');
						System.out.println("悔棋位置是" + gx + "," + gy);
						gy = chessman.pop();
						gx = chessman.pop();// 出栈
						steQipan(gx, gy, '\0');
						System.out.println("悔棋位置是" + gx + "," + gy);
						connt--;
						connt--;
					}
					repaint();
				}
			}
		});
	}

	public void paint(Graphics g) {
		BufferedImage bi = new BufferedImage(716, 716, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = bi.createGraphics();
		Graphics2D g2d = (Graphics2D) g2;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 抗锯齿效果
		super.paint(g2d);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (isVisible && qipan[i][j] == 'b') {
					g2d.setColor(Color.BLACK);
					g2d.fillOval(34 + 44 * i, 44 * j + 62, 34, 34);
					// System.out.println("BLACK");
				} else if (isVisible && qipan[i][j] == 'w') {
					g2d.setColor(Color.RED);
					g2d.fillOval(34 + 44 * i, 44 * j + 62, 34, 34);
					// System.out.println("RED");
				}
			}
		}
		g.drawImage(bi, 0, 0, this);
	}

	public void steQipan(int gx, int gy, char who) {
		qipan[gx][gy] = who;
		if (who != '\0') {
			chessman.push(gx);// 入栈
			chessman.push(gy);
		}
		System.out.println(qipan[gx][gy]);
	}

	public void steQipanInit() {
		for (int a = 0; a < 15; a++) {
			for (int b = 0; b < 15; b++) {
				qipan[a][b] = '\0';
			}
		}
		// 清空栈
		while (chessman.isEmpty()) {
			chessman.pop();
		}
	}

	public void winDow() {
		if (who.win(qipan) == 'b') {
			JOptionPane.showMessageDialog(this, "哇！你赢了,太厉害了!");
			steQipanInit();
		} else if (who.win(qipan) == 'w') {
			JOptionPane.showMessageDialog(this, "很不幸,电脑获得胜利!");
			steQipanInit();
		}
		repaint();
	}

}