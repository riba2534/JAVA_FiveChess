package FiveChess;

/* 参数：字符数组
 * 返回：字符w或b或'\0'
 */
public class Win {
	private char[][] qipan = new char[15][15];
	private int a, b;

	public char win(char[][] qipan) {
		this.qipan = qipan;
		for (a = 0; a < 15; a++) {
			for (b = 0; b < 15; b++) {
				if (win_1(a, b, 0, a + 5, b + 5, 'b') || win_2(a, b, 0, a + 5, b + 5, 'b')
						|| win_3(a, b, 0, a + 5, b + 5, 'b') || win_4(a, b, 0, a + 5, b + 5, 'b')) {
					System.out.println("黑子胜利！");
					return 'b';
				}
				if (win_1(a, b, 0, a + 5, b + 5, 'w') || win_2(a, b, 0, a + 5, b + 5, 'w')
						|| win_3(a, b, 0, a + 5, b + 5, 'w') || win_4(a, b, 0, a + 5, b + 5, 'w')) {
					System.out.println("白子胜利！");
					return 'w';
				}
			}
		}
		return '\0';
	}

	// wx是x+5;
	// wy是y+5
	private boolean win_1(int x, int y, int w, int wx, int wy, int who) {
		w = 0;
		for (int a = 0; a < 5; a++) {
			if (x < 15 && x < wx && qipan[x][y] == who) {
				x++;
				w++;
				// System.out.println(w);
				if (w >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean win_2(int x, int y, int w, int wx, int wy, int who) {
		w = 0;
		for (int a = 0; a < 5; a++) {
			if (y < 15 && y < wy && qipan[x][y] == who) {
				y++;
				w++;
				if (w >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean win_3(int x, int y, int w, int wx, int wy, int who) {
		w = 0;
		for (int a = 0; a < 5; a++) {
			if (x >= 0 && y < 15 && x > (wx - 10) && y < wy && qipan[x][y] == who) {
				x--;
				y++;
				w++;
				if (w >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean win_4(int x, int y, int w, int wx, int wy, int who) {
		w = 0;
		for (int a = 0; a < 5; a++) {
			if (x < 15 && y < 15 && x < wx && y < wy && qipan[x][y] == who) {
				x++;
				y++;
				w++;
				if (w >= 5) {
					return true;
				}
			}
		}
		return false;
	}
}
