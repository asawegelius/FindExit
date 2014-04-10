import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MazeReader;
import domain.Maze;
import domain.Path;
import domain.PathFinder;
import gui.MazeWriter;

import java.util.ArrayList;


public class GuiTest extends JFrame {

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
					GuiTest frame = new GuiTest();
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
	public GuiTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		MazeReader reader = new MazeReader();
		ArrayList<Maze> mazes = reader.getMaces("files\\labyrinths.csv");
		System.out.println();
		MazeWriter canvas = new MazeWriter(mazes.get(0));
		contentPane.add(canvas, BorderLayout.CENTER);
		

		
		PathFinder tester = new PathFinder();
		Maze maze = new MazeReader().getMaces("files\\labyrinths.csv").get(0);
		long start_time = System.currentTimeMillis();
		Path path =  tester.shortestPath(maze);
		long end_time = System.currentTimeMillis();
		long difference = end_time-start_time;

		System.out.println("start_time: " + start_time);
		System.out.println("end_time: " + end_time);
		System.out.println("time it took: " + difference);

		
		
		System.out.println(path.getDist());

		canvas.setPath(path);
		//tester.addForks(maze, path);
		
	}

}
