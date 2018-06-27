package FiveChess;

public class Bot {
	private char[][] qipan = new char[15][15];
	private char[][] j = new char[4][9];
	Lin[] fenl_1 = new Lin[225];
	{
		for (int a = 0; a < 225; a++) {
			fenl_1[a] = new Lin();
		}
	}
	Lin[] fenl_2 = new Lin[225];
	{
		for (int a = 0; a < 225; a++) {
			fenl_2[a] = new Lin();
		}
	}
	char who, notwho;

	public Bot(char[][] qipan, char who) {
		this.who = who;
		this.qipan = qipan;
		// for(int i =0 ;i<15;i++) {
		// for(int j = 0;j<15;j++) {
		// System.out.print(i);
		// System.out.print(j);
		// System.out.println(qipan[i][j]);
		// }
		// }
		if (who == 'w') {
			this.who = 'w';
			notwho = 'b';
		} else {
			this.who = 'b';
			notwho = 'w';
		}
	}

	public int[] getXY() {
		int k, h;
		dafen();
		for (k = 0; k < 15; k++) {
			for (h = 0; h < 15; h++) {
				if (qipan[k][h] != '\0') {// 已经落了子的地方的分数为0
					fenl_1[k * 15 + h].setFraction(0);
					fenl_2[k * 15 + h].setFraction(0);
				}
			}
		} // 设置棋盘上已经使用的位置
		Qksort QuickSort_fenl1 = new Qksort(fenl_1);
		Qksort QuickSort_fenl2 = new Qksort(fenl_2);
		fenl_1 = QuickSort_fenl1.getQksort();
		fenl_2 = QuickSort_fenl2.getQksort();
		// for(int g = 224;g>=0;g--) {
		// System.out.print(fenl_1[224].getFraction());
		// System.out.print(fenl_1[224].getX());
		// System.out.println(fenl_1[224].getY());
		// System.out.print(fenl_2[224].getFraction());
		// System.out.print(fenl_2[224].getX());
		// System.out.println(fenl_2[224].getY());
		// }
		// System.out.println(fenl_1[224].getFraction());
		// System.out.println(fenl_2[224].getFraction());
		// System.out.println(fenl_2[223].getFraction());
		int[] xy = new int[2];
		if (fenl_1[224].getFraction() > fenl_2[224].getFraction()) {
			xy[0] = fenl_1[224].getX();
			xy[1] = fenl_1[224].getY();
			// System.out.println("1x="+xy[0]+"y="+xy[1]);
			return xy;
		} else if (fenl_1[224].getFraction() < fenl_2[224].getFraction()) {
			xy[0] = fenl_2[224].getX();
			xy[1] = fenl_2[224].getY();
			// System.out.println("2x="+xy[0]+"y="+xy[1]);
			return xy;
		}
		xy[0] = fenl_1[224].getX();
		xy[1] = fenl_1[224].getY();
		// System.out.println("3x="+xy[0]+"y="+xy[1]);
		return xy;
	}

	public void dafen() {
		int a, b, i = 0;
		chushuhua_list();
		for (a = 0; a < 15; a++) {
			for (b = 0; b < 15; b++) {
				getJ(a, b, 'w');
				fenl_1[i].setX(a);
				fenl_1[i].setY(b);
				fenl_1[i].setFraction(getFen('w'));
				getJ(a, b, 'b');
				fenl_2[i].setX(a);
				fenl_2[i].setY(b);
				fenl_2[i].setFraction(getFen('b'));
				i++;
			}
		}
	}

	public void chushuhua_list() {
		int i;
		for (i = 0; i < 225; i++) {
			fenl_1[i].setFraction(0);
			fenl_2[i].setFraction(0);
		}
	}

	public void getJ(int x, int y, char who) { // x,y为坐标,*temp为当前棋局,M为打分棋子(w为白子打分，b为黑子打分)
		int i = 0;// j[0]记录上下棋子分布情况,j[1]记录左右棋子分布情况,j[2]记录右上左下棋子分布情况,j[3]记录左上右下棋子分布情况
		if (x < 15 && y < 15) {// 上下
			initJ(0, who);
			// printf("%d\n",j[0][4]);
			int temp_y = y;
			for (i = 4; temp_y > 0 && i > 0; i--) {// 上
				temp_y -= 1;
				if (qipan[x][temp_y] == who) {
					j[0][i - 1] = who;
				} else if (qipan[x][temp_y] == notwho) {
					j[0][i - 1] = notwho; // 有黑子看作边界
				}
			}
			// printf("%d\n",i);
			for (; i > 0; i--) {
				j[0][i - 1] = 'c';// c表示边界
			}
			temp_y = y;
			for (i = 4; temp_y < 14 && i < 8; i++) {// 下
				temp_y += 1;
				if (qipan[x][temp_y] == who) {
					j[0][i + 1] = who;
				} else if (qipan[x][temp_y] == notwho) {
					j[0][i + 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i < 8; i++) {
				j[0][i + 1] = 'c';// 2表示边界
			}
		}

		// 左右
		if (x < 15 && y < 15) {
			int temp_x = x;
			initJ(1, who);
			for (i = 4; temp_x > 0 && i > 0; i--) {// 上
				temp_x -= 1;
				if (qipan[temp_x][y] == who) {
					j[1][i - 1] = who;
				} else if (qipan[temp_x][y] == notwho) {
					j[1][i - 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i > 0; i--) {
				j[1][i - 1] = 'c';// 2表示边界
			}
			temp_x = x;
			for (i = 4; temp_x < 14 && i < 8; i++) {// 下
				temp_x += 1;
				if (qipan[temp_x][y] == who) {
					j[1][i + 1] = who;
				} else if (qipan[temp_x][y] == notwho) {
					j[1][i + 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i < 8; i++) {
				j[1][i + 1] = 'c';// 2表示边界
			}
		}

		// j[2]记录右上左下棋子分布情况
		if (x < 15 && y < 15) {
			int temp_x = x, temp_y = y;
			initJ(2, who);
			for (i = 4; temp_x < 14 && temp_y > 0 && i > 0; i--) {// 右上
				temp_x += 1;
				temp_y -= 1;
				if (qipan[temp_x][temp_y] == who) {
					j[2][i - 1] = who;
				} else if (qipan[temp_x][temp_y] == notwho) {
					j[2][i - 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i > 0; i--) {
				j[2][i - 1] = 'c';// 2表示边界
			}
			temp_x = x;
			temp_y = y;
			for (i = 4; temp_x > 0 && temp_y < 14 && i < 8; i++) {// 左下
				temp_x -= 1;
				temp_y += 1;
				if (qipan[temp_x][temp_y] == who) {
					j[2][i + 1] = who;
				} else if (qipan[temp_x][temp_y] == notwho) {
					j[2][i + 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i < 8; i++) {
				j[2][i + 1] = 'c';// 2表示边界
			}
		}

		// j[3]记录左上右下棋子分布情况
		if (x < 15 && y < 15) {
			int temp_x = x, temp_y = y;
			initJ(3, who);
			for (i = 4; temp_x > 0 && temp_y > 0 && i > 0; i--) {// 左上
				temp_x -= 1;
				temp_y -= 1;
				if (qipan[temp_x][temp_y] == who) {
					j[3][i - 1] = who;
				} else if (qipan[temp_x][temp_y] == notwho) {
					j[3][i - 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i > 0; i--) {
				j[3][i - 1] = 'c';// 2表示边界
			}
			temp_x = x;
			temp_y = y;
			for (i = 4; temp_x < 14 && temp_y < 14 && i < 8; i++) {// 右下
				temp_x += 1;
				temp_y += 1;
				if (qipan[temp_x][temp_y] == who) {
					j[3][i + 1] = who;
				} else if (qipan[temp_x][temp_y] == notwho) {
					j[3][i + 1] = notwho; // 有黑子看作边界
				}
			}
			for (; i < 8; i++) {
				j[3][i + 1] = 'c';// 2表示边界
			}
		}
		// for(int l=0;l<4;l++) {
		// System.out.print(who+":("+x+","+y+")");
		// for(int h = 0;h<9;h++) {
		// System.out.print("j["+l+"]["+h+"]="+j[l][h]);
		// }
		// System.out.println();
		// }
	}

	private void initJ(int o, char who) {
		int p;
		for (p = 0; p < 9; p++) {
			j[o][p] = '0';
		}
		j[o][4] = who;
	}

	public long getFen(char who) {
		if (who == 'b') {
			notwho = 'w';
		}
		int fenshu = 0;
		int a = 0;
		for (a = 0; a < 4; a++) {
			if (j[a][4] == who && j[a][5] == who && j[a][6] == who && j[a][7] == who && j[a][8] == who
					|| j[a][3] == who && j[a][4] == who && j[a][5] == who && j[a][6] == who && j[a][7] == who
					|| j[a][2] == who && j[a][3] == who && j[a][4] == who && j[a][5] == who && j[a][6] == who
					|| j[a][1] == who && j[a][2] == who && j[a][3] == who && j[a][4] == who && j[a][5] == who
					|| j[a][0] == who && j[a][1] == who && j[a][2] == who && j[a][3] == who && j[a][4] == who) {
				// 成5
				fenshu = 100000 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && j[a][6] == who && j[a][7] == who && j[a][8] == '0'
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && j[a][5] == who && j[a][6] == who
							&& j[a][7] == '0'
					|| j[a][1] == '0' && j[a][2] == who && j[a][3] == who && j[a][4] == who && j[a][5] == who
							&& j[a][6] == '0'
					|| j[a][0] == '0' && j[a][1] == who && j[a][2] == who && j[a][3] == who && j[a][4] == who
							&& j[a][5] == '0') {
				// 活4
				fenshu = 10000 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && j[a][6] == who && j[a][7] == who
					&& (j[a][8] == notwho || j[a][8] == 'c')
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && j[a][5] == who && j[a][6] == who
							&& (j[a][7] == notwho || j[a][7] == 'c')
					|| j[a][1] == '0' && j[a][2] == who && j[a][3] == who && j[a][4] == who && j[a][5] == who
							&& (j[a][6] == notwho || j[a][6] == 'c')
					|| j[a][0] == '0' && j[a][1] == who && j[a][2] == who && j[a][3] == who && j[a][4] == who
							&& (j[a][5] == notwho || j[a][5] == 'c')
					|| (j[a][3] == notwho || j[a][3] == 'c') && j[a][4] == who && j[a][5] == who && j[a][6] == who
							&& j[a][7] == '0' && j[a][8] == '0'
					|| (j[a][2] == notwho || j[a][2] == 'c') && j[a][3] == who && j[a][4] == who && j[a][5] == who
							&& j[a][6] == '0' && j[a][7] == '0'
					|| (j[a][1] == notwho || j[a][1] == 'c') && j[a][2] == who && j[a][3] == who && j[a][4] == who
							&& j[a][5] == '0' && j[a][6] == '0'
					|| (j[a][0] == notwho || j[a][0] == 'c') && j[a][1] == who && j[a][2] == who && j[a][3] == who
							&& j[a][4] == '0' && j[a][5] == '0') {
				// 冲4
				fenshu = 1000 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && j[a][6] == who && j[a][7] == '0'
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && j[a][5] == who && j[a][6] == '0'
					|| j[a][1] == '0' && j[a][2] == who && j[a][3] == who && j[a][4] == who && j[a][5] == '0') {
				// 活3
				fenshu = 1000 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && j[a][6] == who
					&& (j[a][7] == notwho || j[a][7] == 'c')
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && j[a][5] == who
							&& (j[a][6] == notwho || j[a][6] == 'c')
					|| j[a][1] == '0' && j[a][2] == who && j[a][3] == who && j[a][4] == who
							&& (j[a][5] == notwho || j[a][5] == 'c')
					|| (j[a][3] == notwho || j[a][3] == 'c') && j[a][4] == who && j[a][5] == who && j[a][6] == who
							&& j[a][7] == '0'
					|| (j[a][2] == notwho || j[a][2] == 'c') && j[a][3] == who && j[a][4] == who && j[a][5] == who
							&& j[a][6] == '0'
					|| (j[a][1] == notwho || j[a][1] == 'c') && j[a][2] == who && j[a][3] == who && j[a][4] == who
							&& j[a][5] == '0') {
				// 冲3
				fenshu = 100 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && j[a][6] == '0'
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && j[a][5] == '0') {
				// 活二
				fenshu = 100 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == who && (j[a][6] == notwho || j[a][6] == 'c')
					|| j[a][2] == '0' && j[a][3] == who && j[a][4] == who && (j[a][5] == notwho || j[a][5] == 'c')
					|| (j[a][3] == notwho || j[a][3] == 'c') && j[a][4] == who && j[a][5] == who && j[a][6] == '0'
					|| (j[a][2] == notwho || j[a][2] == 'c') && j[a][3] == who && j[a][4] == who && j[a][5] == '0') {
				// 眠二
				fenshu = 10 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && j[a][5] == '0') {
				// 活一
				fenshu = 10 + fenshu;
				continue;
			}
			if (j[a][3] == '0' && j[a][4] == who && (j[a][5] == notwho || j[a][5] == 'c')
					|| (j[a][3] == notwho || j[a][3] == 'c') && j[a][4] == who && j[a][5] == '0') {
				// 眠一
				fenshu = 1 + fenshu;
				continue;
			}
		}
		return (fenshu);
	}
}
