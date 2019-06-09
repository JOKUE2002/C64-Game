import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements MouseListener {

	int fieldSize = 20;
	int yOffset = 20;
	int w = fieldSize * 18 + 3 * fieldSize;
	int h = fieldSize * 18 + yOffset;

	boolean[][] state = new boolean[16][16];

	ArrayList<boolean[][]> stateList = new ArrayList<>();
	ArrayList<String> stateTitles = new ArrayList<>();

	public Frame() {

		setDefaultCloseOperation(3);
		setSize(w, h);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();
//		
//		System.out.println(0/2 * 2 + 0%2);
//		System.out.println(1/2 * 2 + 1%2);
//		System.out.println(2/2 * 2 + 2%2);
//		System.out.println(3/2 * 2 + 3%2);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);

		g.setColor(Color.white);
		for (int i = 1; i < 18; i++) {
			g.drawLine(fieldSize, fieldSize * i + yOffset, fieldSize * 17, fieldSize * i + yOffset);
		}

		for (int i = 1; i < 18; i++) {
			g.drawLine(fieldSize * i, fieldSize + yOffset, fieldSize * i, fieldSize * 17 + yOffset);
		}

		g.drawString("ADD", fieldSize * 18, yOffset + 2 * fieldSize);
		g.drawString("SHOW", fieldSize * 18, yOffset + 3 * fieldSize);
		g.drawString("EDIT", fieldSize * 18, yOffset + 4 * fieldSize);
		g.drawString("RESET", fieldSize * 18, yOffset + 5 * fieldSize);
		g.drawString("EXPORT", fieldSize * 18, yOffset + 16 * fieldSize);

		g.setColor(Color.YELLOW);
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				if (state[x][y]) {
					g.fillRect(x * fieldSize + fieldSize, y * fieldSize + fieldSize + yOffset, fieldSize, fieldSize);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() < 17 * fieldSize) {
			int fieldX = (e.getX()) / fieldSize - 1;
			int fieldY = (e.getY() - yOffset) / fieldSize - 1;

			System.out.println("X: " + fieldX + " - Y: " + fieldY);

			state[fieldX][fieldY] = !state[fieldX][fieldY];
			repaint();
		} else {
//			g.drawString("ADD", fieldSize * 18, yOffset + 1 * fieldSize);
//			g.drawString("SHOW", fieldSize * 18, yOffset + 3 * fieldSize);
//			g.drawString("EDIT", fieldSize * 18, yOffset + 5 * fieldSize);
//			g.drawString("RESET", fieldSize * 18, yOffset + 7 * fieldSize);
//			g.drawString("EXPORT", fieldSize * 18, yOffset + 16 * fieldSize);

			if (e.getY() < yOffset + 2 * fieldSize) {
				System.out.println("add");
				String name = JOptionPane.showInputDialog("Name the icon created");
				stateTitles.add(name);
				safeAdd();
				resetState();
			} else if (e.getY() < yOffset + 3 * fieldSize) {
				System.out.println("remove");

				int c = JOptionPane.showOptionDialog(null, "Which one to remove?", "Choose remove",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, stateTitles.toArray(), 0);
				if (c != JOptionPane.CLOSED_OPTION) {
					stateTitles.remove(c);
					stateList.remove(c);
				}
			} else if (e.getY() < yOffset + 4 * fieldSize) {
				System.out.println("edit");
				int c = JOptionPane.showOptionDialog(null, "Which one to edit?", "Choose edit",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, stateTitles.toArray(), 0);
				if (c != JOptionPane.CLOSED_OPTION) {
					state = stateList.get(c);
				}
			} else if (e.getY() < yOffset + 5 * fieldSize) {
				System.out.println("reset");
				resetState();
				stateList.clear();
			} else if (e.getY() < yOffset + 17 * fieldSize && e.getY() > yOffset + 15 * fieldSize) {
				System.out.println("export");

				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				int res = jfc.showSaveDialog(null);

				int line = Integer.valueOf(JOptionPane.showInputDialog("Startline", "1000"));

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(jfc.getSelectedFile()));

					/*
					 * 01 FOR I=0 TO 26: READ X: POKE 828+I,X: NEXT I 02 FOR I=0 TO 26: READ X: POKE
					 * 828+I,X: NEXT I 03 DATA 169,000,160,208,133,095,132,096 04 DATA
					 * 169,000,160,224,133,090,132,091  05 DATA 169,000,160,064,133,088,132,089 06
					 * DATA 076,191,163 07 POKE 56334,PEEK(56334) AND 254 08 POKE 1,PEEK(1) AND 251
					 * 09 SYS 828 10 POKE 1,PEEK(1) OR 4 11 POKE 56334,PEEK(56334) OR 1 12 POKE
					 * 53272,PEEK(53272) AND 240 OR 12 13 FOR A=12800 TO 13535: READ ZE: POKE A,ZE:
					 * NEXT A
					 * 
					 * 14f. DATA IMAGES (4 lines per image)
					 * 
					 * 14 + 4 * ImageCount RETURN
					 */

					int endRAMIndex = 12800 - 1 + 8 * 4 * stateList.size();

					bw.write(line + " FOR I=0 TO 26: READ X: POKE 828+I,X: NEXT I\n");
					line++;
					bw.write(line + " DATA 169,000,160,208,133,095,132,096\n");
					line++;
					bw.write(line + " DATA 169,000,160,224,133,090,132,091\n");
					line++;
					bw.write(line + " DATA 169,000,160,064,133,088,132,089\n");
					line++;
					bw.write(line + " DATA 076,191,163\n");
					line++;
					bw.write(line + " POKE 56334,PEEK(56334) AND 254\n");
					line++;
					bw.write(line + " POKE 1,PEEK(1) AND 251\n");
					line++;
					bw.write(line + " SYS 828\n");
					line++;
					bw.write(line + " POKE 1,PEEK(1) OR 4\n");
					line++;
					bw.write(line + " POKE 56334,PEEK(56334) OR 1\n");
					line++;
					bw.write(line + " POKE 53272,PEEK(53272) AND 240 OR 12\n");
					line++;
					bw.write(line + " FOR A=12800 TO " + endRAMIndex + ": READ ZE: POKE A,ZE: NEXT A\n");
					for (int i = 0; i < stateList.size(); i++) {
						line++;
						bw.write(line + " REM " + stateTitles.get(i) + "\n");
						for (int j = 0; j < 4; j++) {
							line++;
							String dataString = "";
							for (int k = 7; k >= 0; k--) {
								int sum = 0;
								for (int b = 0; b < 8; b++) {//
									sum += (stateList.get(i)[(j % 2) * 8 + b][k]) ? (int) Math.pow(2, b) : 0;
								}
								dataString += "" + sum + ",";
								System.out.println(sum);
							}
							dataString = dataString.substring(0, dataString.length() - 1);
							bw.write(line + " DATA " + dataString + "\n");
						}
					}
					line++;
					bw.write(line + " RETURN");

					bw.flush();
					bw.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private void safeAdd() {
		boolean[][] tmp = new boolean[16][16];
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				tmp[x][y] = state[x][y];
			}
		}

		stateList.add(tmp);
	}

	private void resetState() {
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				state[x][y] = false;
			}
		}
	}

	// UNUSED
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
