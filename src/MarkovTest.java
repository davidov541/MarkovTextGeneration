import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
/**
 * Dummy class for the sole purpose of testing the Markov project.
 *
 * @author Dane Bennington.
 *         Created Oct 10, 2007.
 */
public class MarkovTest {

	public static Markov mine;
	public static JFrame mainframe = new JFrame();
	public static JTextArea output = new JTextArea();
	public static JSlider sanity = new JSlider();
	public static JFileChooser file = new JFileChooser();
	public static JMenuBar menuBar;
	public static boolean refresh = false;
	/**
	 * Creates a new Markov for testing purposes, and inserts a text file to be Markovized.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setSize(800, 800);
		mainframe.setVisible(true);
		mainframe.setLayout(null);
		mainframe.setTitle("Markov Generator by Dane Bennington");
		
		class myButton extends JButton implements ActionListener{
			
		myButton(){
			JButton goButton = new JButton();
		}
		
		public void actionPerformed(ActionEvent e) {
				mine = new Markov("Text Files\\oz.txt", sanity.getValue(), 820,143);
				refresh = true;
			}
		}

		sanity.setVisible(true);
		sanity.setMaximum(5);
		sanity.setMinimum(1);
		sanity.setMajorTickSpacing(1);
		sanity.setValue(3);
		sanity.setPaintTicks(true);
		sanity.setSnapToTicks(true);
		sanity.setPaintLabels(true);
		mainframe.add(sanity);
		sanity.setSize(new Dimension(400, 80));
		sanity.isEnabled();
		sanity.setLocation((int)(mainframe.getLocation().getX() + (mainframe.getWidth()/2)) - 200,(int)( mainframe.getLocation().getY() + (mainframe.getHeight()/8)));

		JLabel label = new JLabel("Coherence");
		label.setLocation(sanity.getX() + (sanity.getWidth()/2) - 30, sanity.getY() + sanity.getHeight());
		label.setVisible(true);
		label.setSize(80, 10);
		mainframe.add(label);
		
		myButton go = new myButton();
		go.setLocation((int)(mainframe.getLocation().getX() + (mainframe.getWidth()/2)) - 50,(int)( mainframe.getLocation().getY() + (mainframe.getHeight()/4)));
		mainframe.add(go);
		go.addActionListener(go);
		go.setSize(new Dimension(100, 40));
		go.setVisible(true);
		go.setText("Markovize!");

		JScrollPane scroll = new JScrollPane(output);
		output.setSize(mainframe.getWidth(), (int)(mainframe.getHeight()* 0.65));
		scroll.setVisible(true);
		mainframe.add(output);
		output.setVisible(true);
		output.setEditable(false);
		output.setLocation((int)(mainframe.getLocation().getX()),(int)(mainframe.getLocation().getY() + mainframe.getHeight() - output.getHeight()));
		for(;;){
			if(refresh == true){
				output.setText("");
				output.append(mine.readOut().toString());
				refresh = false;
			}
		}
	}
}
