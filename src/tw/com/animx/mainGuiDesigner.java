package tw.com.animx;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class mainGuiDesigner {

	private static JTextArea ShowConsole;
	private JFrame frmGetanicomic;
	private static JTextArea textArea ;
	private static JTextField downCount;
	private static JButton btnStartdownload;
	/**
	 * Launch the application.
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.setProperty("file.encoding", "UTF-8");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGuiDesigner window = new mainGuiDesigner();
					window.frmGetanicomic.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedEncodingException 
	 * @throws MalformedURLException 
	 */
	public mainGuiDesigner() throws UnsupportedEncodingException, MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedEncodingException 
	 * @throws MalformedURLException 
	 */
	private void initialize() throws UnsupportedEncodingException, MalformedURLException {
		frmGetanicomic = new JFrame();
		frmGetanicomic.setTitle("get2aniComic");
		frmGetanicomic.setBounds(100, 100, 890, 605);
		frmGetanicomic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGetanicomic.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblDownloadlist = new JLabel("DownloadList:");
		lblDownloadlist.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		frmGetanicomic.getContentPane().add(lblDownloadlist, "2, 2");
		
		textArea = new JTextArea();
		textArea.setFont(new Font("微軟正黑體", Font.PLAIN, 13));
		JScrollPane js = new JScrollPane(textArea);
		frmGetanicomic.getContentPane().add(js, "2, 4, 8, 7, fill, fill");
		
		ImageIcon imgIcon = new ImageIcon(new URL("http://i.imgur.com/JFgiqR7.jpg"));
		imgIcon.setImage(imgIcon.getImage().getScaledInstance((int)imgIcon.getIconWidth()/2,(int)imgIcon.getIconWidth()/2, Image.SCALE_SMOOTH));
		JLabel lblShowImg = new JLabel(imgIcon);
		frmGetanicomic.getContentPane().add(lblShowImg, "12, 2, 5, 14, fill, fill");
		
		JLabel label = new JLabel("\u4E0B\u8F09\u5012\u6578\u5E7E\u8A71\uFF1A");
		label.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		frmGetanicomic.getContentPane().add(label, "2, 14, left, center");
		
		downCount = new JTextField();
		downCount.setText("1");
		frmGetanicomic.getContentPane().add(downCount, "4, 14, center, center");
		downCount.setColumns(10);
		
		btnStartdownload = new JButton("StartDownload");
		btnStartdownload.addActionListener(new startDownload());
		
		
		frmGetanicomic.getContentPane().add(btnStartdownload, "8, 14, left, top");
		
		JLabel desc = new JLabel("0\u8868\u793A\u5168\u90E8\u4E0B\u8F09");
		desc.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		frmGetanicomic.getContentPane().add(desc, "4, 16, center, center");
		
		JLabel lblConsole = new JLabel("Console");
		lblConsole.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		frmGetanicomic.getContentPane().add(lblConsole, "2, 18");
		
		ShowConsole = new JTextArea();
//		frmGetanicomic.getContentPane().add(ShowConsole, "2, 20, 19, 13");
		ShowConsole.setWrapStyleWord(true);
		ShowConsole.setLineWrap(true);
		ShowConsole.setColumns(2);
		ShowConsole.setEditable(false);
		ShowConsole.setFont(new Font("微軟正黑體", Font.PLAIN, 13))	;
		JScrollPane console = new JScrollPane(ShowConsole);
		console.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frmGetanicomic.getContentPane().add(console, "2, 20, 19, 13");
	
	}
	
	private class startDownload implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			if(textArea.getText().indexOf("http")<0){
				 JOptionPane.showMessageDialog(frmGetanicomic, "Download List 輸入有誤");
				 return;
			}
			btnStartdownload.setEnabled(false);
			btnStartdownload.setText("Downloading!!");
			ShowConsole.setText("");
			printLog("start Download!\n");
			String D_list = textArea.getText();		//下載清單
			int last = Integer.valueOf(downCount.getText());
			String[] comic = D_list.split("\n");
			get2AniPhoto dd = new get2AniPhoto(comic, last);
			Executor executor = Executors.newFixedThreadPool(1);
			executor.execute(dd);
		}
		
	}
	
	/**
	 * 印出 Log
	 * @param input
	 */
	public static void printLog(String input){
		ShowConsole.append( input + "\n");
		ShowConsole.setCaretPosition(ShowConsole.getDocument().getLength()); 
	}
	
	/**
	 * 下載完成控制
	 */
	public static void finishJob(){
		btnStartdownload.setEnabled(true);
		btnStartdownload.setText("startDownload");
		textArea.setText("");
		JOptionPane.showMessageDialog(null, "下載完成");
	}
}
