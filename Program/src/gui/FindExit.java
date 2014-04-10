package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import dao.MazeReader;
import domain.Maze;
import domain.Path;
import domain.PathFinder;

import java.awt.Font;

/** <h1> FindExit</h1>
 * 	@author Åsa Wegelius
 * 	A JFrame that shows the shortest path for a maze given from a csv file. It also tells how many milliseconds it took
 * 	to find the solution. */
public class FindExit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindExit frame = new FindExit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FindExit() {
		PathFinder tester = new PathFinder();
		Maze maze = new MazeReader().getMaces("files\\bigMaze.csv").get(0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Find the exit.");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.white);
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		MazeWriter canvas = new MazeWriter(maze);
		canvas.setSize(600, 600);
		panel_1.add(canvas);


		long start_time = System.currentTimeMillis();
		Path path =  tester.shortestPath(maze);
		long end_time = System.currentTimeMillis();
		long diff = end_time-start_time;
		
		canvas.setPath(path);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JLabel lblbtm = new JLabel();
		lblbtm.setText("The time to find the exit was " + diff + " milliseconds");
		lblbtm.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_2.add(lblbtm);
	}

}
