package hw3;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class hw3 {

	JFrame frmYelpbk;
	private String[] mainCategories = {"ActiveLife", "Arts '/&'Entertainment", "Automotive", "CarRental", "Cafes", "Beauty &Spas", "ConvenienceStores", "Dentists", "Doctors", "Drugstores", "DepartmentStores", "Education", "Event Planning &Services","Flowers &Gifts","Food","Health &Medical", "HomeServices", "Home &Garden", "Hospitals", "Hotels &Travel", "HardwareStores", "Grocery", "MedicalCenters", "Nurseries &Gardening", "Nightlife2", "Restaurants", "Shopping", "Transportation"};
	private JPanel panelcat;
	private JPanel panelsub;
	private ArrayList<JCheckBox> subCatList;
	private JRootPane rootPane;
	private JTextField textVote;
	private JTextField textStars;
	private JTextField textFriends;
	private JTextField textAvgStar;
	private JTextField textReviewno;
	private JTable Resultuser;
	private boolean flagCat = true, flagCheckin = true, flagReview = true;
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;
	private JComboBox<String> comboBox3;
	private JComboBox<String> comboBox4;
	private JComboBox<String> comboBox5;
	private JComboBox<String> comboBox7;
	private JComboBox<String> comboBox8;
	private JComboBox<String> comboBox9;
	private JComboBox<String> comboBox10;
	private JComboBox<String> comboBox11;
	private JComboBox<String> comboBoxFromYear;
	private JComboBox<String> comboBoxFromHour;
	private JComboBox<String> comboBoxToYear;
	private JComboBox<String> comboBoxToMonth;
	private boolean flagStar = true;
	private JTextArea textAreaSQLBusiness ;
	private JComboBox<String> comboBoxYear;
	private JComboBox<String> comboBoxMonth;
	private JTextArea textSqlQuery2;
	private JTable ResultBusiness;
	private JComboBox<String> comboBox12;
	private JLabel lblBusinessName;
	private JLabel lblUserName;
	private boolean flagReviewtableup = false;
	private JComboBox<String> comboBoxFromDay ;
	private JComboBox<String> comboBoxToDay ;
	

	private static String dbname = "bob123";
	private static String dbpass = "peace";
	private JTextField textFieldVote;
	private JTextField textFieldReviewStars;
	private JComboBox<String> comboBoxVotes;
	private JComboBox<String> comboBoxReviewStars;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw3 window = new hw3();
					window.frmYelpbk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hw3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmYelpbk = new JFrame();
		frmYelpbk.setTitle("YELP_BK");
		frmYelpbk.setBounds(100, 100, 734, 726);
		frmYelpbk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmYelpbk.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelBusiness = new JPanel();
		panelBusiness.setName("main_category");
		panelBusiness.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBusiness.setSize(new Dimension(50, 50));
		tabbedPane.addTab("Business", null, panelBusiness, null);
			panelBusiness.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setName("Main Category");
			scrollPane.setMaximumSize(new Dimension(200, 100));
			scrollPane.setViewportBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
			scrollPane.setSize(new Dimension(50, 100));
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelBusiness.add(scrollPane);
			
			panelcat = new JPanel();
			panelcat.setMaximumSize(new Dimension(200, 300));
			panelcat.setSize(new Dimension(5, 10));
			panelcat.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			panelcat.setBounds(new Rectangle(0, 0, 50, 100));
			panelcat.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			scrollPane.setViewportView(panelcat);
			scrollPane.setBounds(10, 11, 200, 229);;
			panelcat.setLayout(new BoxLayout(panelcat, BoxLayout.Y_AXIS));
			
			JCheckBox chckbxCategory = new JCheckBox(mainCategories[0]);
			panelcat.add(chckbxCategory);
			
			JCheckBox cbMainCategory_1 = new JCheckBox(mainCategories[1]);
			panelcat.add(cbMainCategory_1);
			
			JCheckBox cbMainCategory_2 = new JCheckBox(mainCategories[2]);
			panelcat.add(cbMainCategory_2);
			
			JCheckBox cbMainCategory_3 = new JCheckBox(mainCategories[3]);
			panelcat.add(cbMainCategory_3);
			JCheckBox cbMainCategory_4 = new JCheckBox(mainCategories[4]);
			panelcat.add(cbMainCategory_4);
			JCheckBox cbMainCategory_5 = new JCheckBox(mainCategories[5]);
			panelcat.add(cbMainCategory_5);
			JCheckBox cbMainCategory_6 = new JCheckBox(mainCategories[6]);
			panelcat.add(cbMainCategory_6);
			JCheckBox cbMainCategory_7 = new JCheckBox(mainCategories[7]);
			panelcat.add(cbMainCategory_7);
			JCheckBox cbMainCategory_8 = new JCheckBox(mainCategories[8]);
			cbMainCategory_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panelcat.add(cbMainCategory_8);
			JCheckBox cbMainCategory_9 = new JCheckBox(mainCategories[9]);
			panelcat.add(cbMainCategory_9);
			JCheckBox cbMainCategory_10 = new JCheckBox(mainCategories[10]);
			panelcat.add(cbMainCategory_10);
			JCheckBox cbMainCategory_11 = new JCheckBox(mainCategories[11]);
			panelcat.add(cbMainCategory_11);
			JCheckBox cbMainCategory_12 = new JCheckBox(mainCategories[12]);
			panelcat.add(cbMainCategory_12);
			JCheckBox cbMainCategory_13 = new JCheckBox(mainCategories[13]);
			panelcat.add(cbMainCategory_13);
			JCheckBox cbMainCategory_14 = new JCheckBox(mainCategories[14]);
			panelcat.add(cbMainCategory_14);
			JCheckBox cbMainCategory_15 = new JCheckBox(mainCategories[15]);
			panelcat.add(cbMainCategory_15);
			JCheckBox cbMainCategory_16 = new JCheckBox(mainCategories[16]);
			panelcat.add(cbMainCategory_16);
			JCheckBox cbMainCategory_17 = new JCheckBox(mainCategories[17]);
			panelcat.add(cbMainCategory_17);
			JCheckBox cbMainCategory_18 = new JCheckBox(mainCategories[18]);
			panelcat.add(cbMainCategory_18);
			JCheckBox cbMainCategory_19 = new JCheckBox(mainCategories[19]);
			panelcat.add(cbMainCategory_19);
			JCheckBox cbMainCategory_20 = new JCheckBox(mainCategories[20]);
			panelcat.add(cbMainCategory_20);
			JCheckBox cbMainCategory_21 = new JCheckBox(mainCategories[21]);
			panelcat.add(cbMainCategory_21);
			JCheckBox cbMainCategory_22 = new JCheckBox(mainCategories[22]);
			panelcat.add(cbMainCategory_22);
			JCheckBox cbMainCategory_23 = new JCheckBox(mainCategories[23]);
			panelcat.add(cbMainCategory_23);
			JCheckBox cbMainCategory_24 = new JCheckBox(mainCategories[24]);
			panelcat.add(cbMainCategory_24);
			JCheckBox cbMainCategory_25 = new JCheckBox(mainCategories[25]);
			panelcat.add(cbMainCategory_25);
			JCheckBox cbMainCategory_26 = new JCheckBox(mainCategories[26]);
			panelcat.add(cbMainCategory_26);
			JCheckBox cbMainCategory_27 = new JCheckBox(mainCategories[27]);
			panelcat.add(cbMainCategory_27);
			
			
			
			
			JButton btngetSub = new JButton(">>");
			btngetSub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getSubcategories(arg0);
				}
			});
			btngetSub.setBounds(219, 96, 60, 47);
			panelBusiness.add(btngetSub);
			
			JScrollPane scrollPanesub = new JScrollPane();
			scrollPanesub.setBounds(289, 11, 414, 229);
			panelBusiness.add(scrollPanesub);
			
			panelsub = new JPanel();
			panelsub.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			scrollPanesub.setViewportView(panelsub);
			GroupLayout gl_panelsub = new GroupLayout(panelsub);
			gl_panelsub.setHorizontalGroup(
				gl_panelsub.createParallelGroup(Alignment.LEADING)
					.addGap(0, 221, Short.MAX_VALUE)
			);
			gl_panelsub.setVerticalGroup(
				gl_panelsub.createParallelGroup(Alignment.LEADING)
					.addGap(0, 229, Short.MAX_VALUE)
			);
			panelsub.setLayout(gl_panelsub);
			
			JPanel panelCheckin = new JPanel();
			panelCheckin.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			panelCheckin.setBounds(10, 251, 233, 124);
			panelBusiness.add(panelCheckin);
			
			comboBox1 = new JComboBox<String>();
			comboBox1.addItem(">");
			comboBox1.addItem(">=");
			comboBox1.addItem("=");
			comboBox1.addItem("<");
			comboBox1.addItem("<=");
			comboBox1.setSelectedIndex(2);
			
			textVote = new JTextField();
			textVote.setColumns(10);
			
			JLabel lblFrom = new JLabel("From :");
			
			comboBox2 = new JComboBox<String>();
			comboBox2.setToolTipText("Day");
			comboBox2.addItem("Sun");
			comboBox2.addItem("Mon");
			comboBox2.addItem("Tue");
			comboBox2.addItem("Wed");
			comboBox2.addItem("Thur");
			comboBox2.addItem("Fri");
			comboBox2.addItem("Sat");
			comboBox2.setSelectedIndex(0);
			
			JLabel lblTo = new JLabel("To:");
			
			comboBox3 = new JComboBox<String>();
			comboBox3.setToolTipText("Day");
			comboBox3.addItem("Sun");
			comboBox3.addItem("Mon");
			comboBox3.addItem("Tue");
			comboBox3.addItem("Wed");
			comboBox3.addItem("Thur");
			comboBox3.addItem("Fri");
			comboBox3.addItem("Sat");
			comboBox3.setSelectedIndex(0);
			
			comboBox10 = new JComboBox<String>();
			comboBox10.setToolTipText("Hour");
			for (int i = 0; i<24; i++)
			{
				comboBox10.addItem(new Integer(i).toString());
			}
			comboBox11 = new JComboBox<String>();
			comboBox11.setToolTipText("Hour");
			for (int i = 0; i<24; i++)
			{
				comboBox11.addItem(new Integer(i).toString());
			}
			
			JLabel lblNewLabel = new JLabel("No. of Check ins");
			GroupLayout gl_panelCheckin = new GroupLayout(panelCheckin);
			gl_panelCheckin.setHorizontalGroup(
				gl_panelCheckin.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCheckin.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelCheckin.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(17))
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addGroup(gl_panelCheckin.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblTo)
									.addComponent(lblFrom))
								.addGap(18)))
						.addGroup(gl_panelCheckin.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addGap(1)
								.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelCheckin.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBox2, 0, 50, Short.MAX_VALUE)
									.addComponent(comboBox3, 0, 50, Short.MAX_VALUE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCheckin.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addComponent(comboBox11, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addComponent(comboBox10, 0, 45, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addComponent(textVote, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addGap(23))))
			);
			gl_panelCheckin.setVerticalGroup(
				gl_panelCheckin.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelCheckin.createSequentialGroup()
						.addGap(28)
						.addGroup(gl_panelCheckin.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addGroup(gl_panelCheckin.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textVote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(9)
								.addGroup(gl_panelCheckin.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblFrom)
									.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelCheckin.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBox3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(comboBox11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_panelCheckin.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
								.addComponent(lblTo)
								.addGap(7)))
						.addGap(67))
			);
			panelCheckin.setLayout(gl_panelCheckin);
			JScrollPane scrollPaneSQLquery = new JScrollPane();
			scrollPaneSQLquery.setBounds(264, 251, 439, 103);
			panelBusiness.add(scrollPaneSQLquery);
			
			JPanel panelSQLquery = new JPanel();
			scrollPaneSQLquery.setViewportView(panelSQLquery);
			panelSQLquery.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			panelSQLquery.setLayout(null);
			
			JScrollPane scrollPaneSQLdisplay = new JScrollPane();
			scrollPaneSQLdisplay.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scrollPaneSQLdisplay.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneSQLdisplay.setBounds(0, 0, 437, 101);
			panelSQLquery.add(scrollPaneSQLdisplay);
			
			
			textAreaSQLBusiness = new JTextArea();
			textAreaSQLBusiness.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textAreaSQLBusiness.setLineWrap(true);
			textAreaSQLBusiness.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
			textAreaSQLBusiness.setFont(new Font("Calibri", Font.ITALIC, 13));
			textAreaSQLBusiness.setEditable(false);
			textAreaSQLBusiness.setEnabled(false);
			textAreaSQLBusiness.setDisabledTextColor(Color.BLACK);
			scrollPaneSQLdisplay.setViewportView(textAreaSQLBusiness);
			
			JButton btnExecuteQuery = new JButton("Execute Query");
			btnExecuteQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ExecuteBusinessQuery(arg0);
				}
			});
			btnExecuteQuery.setBounds(63, 620, 127, 28);
			panelBusiness.add(btnExecuteQuery);
			
			comboBox5 = new JComboBox<String>();
			comboBox5.setBounds(220, 154, 59, 28);
			panelBusiness.add(comboBox5);
			
			JPanel panelDisplay = new JPanel();
			panelDisplay.setBounds(266, 365, 437, 260);
			panelBusiness.add(panelDisplay);
			panelDisplay.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			panelDisplay.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 11, 417, 212);
			panelDisplay.add(scrollPane_1);
			
			ResultBusiness = new JTable();
			ResultBusiness.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					BusinessSelect(arg0);
				}
			});
			scrollPane_1.setViewportView(ResultBusiness);
			
			
			lblBusinessName = new JLabel("Result");
			lblBusinessName.setLabelFor(ResultBusiness);
			lblBusinessName.setHorizontalTextPosition(SwingConstants.CENTER);
			lblBusinessName.setHorizontalAlignment(SwingConstants.CENTER);
			lblBusinessName.setBounds(10, 223, 417, 26);
			panelDisplay.add(lblBusinessName);
			
			JPanel panelStars = new JPanel();
			panelStars.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panelStars.setBounds(10, 386, 233, 38);
			panelBusiness.add(panelStars);
			panelStars.setLayout(null);
			
			comboBox4 = new JComboBox<String>();
			comboBox4.setBounds(105, 11, 50, 20);
			panelStars.add(comboBox4);
			comboBox4.addItem(">");
			comboBox4.addItem(">=");
			comboBox4.addItem("=");
			comboBox4.addItem("<");
			comboBox4.addItem("<=");
			comboBox4.setSelectedIndex(2);
			
			comboBoxReviewStars = new JComboBox<String>();
			comboBoxReviewStars.addItem(">");
			comboBoxReviewStars.addItem(">=");
			comboBoxReviewStars.addItem("=");
			comboBoxReviewStars.addItem("<");
			comboBoxReviewStars.addItem("<=");
			comboBoxReviewStars.setSelectedIndex(2);
			
			comboBoxVotes = new JComboBox<String>();
			
			comboBoxVotes.addItem(">");
			comboBoxVotes.addItem(">=");
			comboBoxVotes.addItem("=");
			comboBoxVotes.addItem("<");
			comboBoxVotes.addItem("<=");
			comboBoxVotes.setSelectedIndex(2);
			
			
			
			JLabel lblStars = new JLabel("Stars");
			lblStars.setBounds(10, 14, 50, 14);
			panelStars.add(lblStars);
			
			textStars = new JTextField();
			textStars.setBounds(165, 11, 45, 20);
			panelStars.add(textStars);
			textStars.setColumns(10);
			
			JPanel panelReview = new JPanel();
			panelReview.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
			panelReview.setBounds(10, 435, 246, 174);
			panelBusiness.add(panelReview);
			
			JLabel lblVotes = new JLabel("Votes");
			
			JLabel label_1 = new JLabel("To:");
			
			JLabel label_2 = new JLabel("From :");
			
			comboBoxFromYear = new JComboBox<String>();
			comboBoxFromYear.setToolTipText("Year");
		
			
			comboBoxToYear = new JComboBox<String>();
			comboBoxToYear.setToolTipText("Year");
			
			
			
			
			textFieldVote = new JTextField();
			textFieldVote.setColumns(10);
			
			comboBoxFromHour = new JComboBox<String>();
			comboBoxFromHour.setToolTipText("Month");
			
			comboBoxToMonth = new JComboBox<String>();
			comboBoxToMonth.setToolTipText("Month");
			
			JLabel label = new JLabel("Stars");
			
			
			
			
			textFieldReviewStars = new JTextField();
			textFieldReviewStars.setColumns(10);
			
			comboBoxFromDay = new JComboBox<String>();
			comboBoxFromDay.setToolTipText("day");
			
			comboBoxToDay = new JComboBox<String>();
			comboBoxToDay.setToolTipText("Day");
			GroupLayout gl_panelReview = new GroupLayout(panelReview);
			gl_panelReview.setHorizontalGroup(
				gl_panelReview.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panelReview.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panelReview.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelReview.createSequentialGroup()
								.addGroup(gl_panelReview.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panelReview.createSequentialGroup()
										.addComponent(lblVotes)
										.addGap(18)
										.addComponent(comboBoxVotes, 0, 44, Short.MAX_VALUE))
									.addGroup(gl_panelReview.createSequentialGroup()
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(comboBoxReviewStars, 0, 42, Short.MAX_VALUE)))
								.addGap(37)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelReview.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldReviewStars, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
									.addComponent(textFieldVote, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
								.addGap(48))
							.addGroup(gl_panelReview.createSequentialGroup()
								.addGroup(gl_panelReview.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panelReview.createSequentialGroup()
										.addComponent(label_1)
										.addGap(33)
										.addComponent(comboBoxToYear, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(gl_panelReview.createSequentialGroup()
										.addComponent(label_2)
										.addGap(18)
										.addComponent(comboBoxFromYear, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelReview.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBoxToMonth, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxFromHour, 0, 52, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelReview.createParallelGroup(Alignment.TRAILING)
									.addComponent(comboBoxToDay, 0, 51, Short.MAX_VALUE)
									.addComponent(comboBoxFromDay, Alignment.LEADING, 0, 51, Short.MAX_VALUE))))
						.addContainerGap())
			);
			gl_panelReview.setVerticalGroup(
				gl_panelReview.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panelReview.createSequentialGroup()
						.addGap(23)
						.addGroup(gl_panelReview.createParallelGroup(Alignment.BASELINE)
							.addComponent(label)
							.addComponent(comboBoxReviewStars, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldReviewStars, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelReview.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblVotes)
							.addComponent(comboBoxVotes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldVote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panelReview.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxFromDay, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_2)
							.addComponent(comboBoxFromYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxFromHour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelReview.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxToDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_1)
							.addComponent(comboBoxToYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxToMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18))
			);
			panelReview.setLayout(gl_panelReview);

			
			comboBox5.addItem("OR");
			comboBox5.addItem("AND");
			
			JPanel panelUser = new JPanel();
			tabbedPane.addTab("User", null, panelUser, null);
			panelUser.setLayout(null);
			
			JPanel panelUserAttr = new JPanel();
			panelUserAttr.setBounds(10, 11, 200, 441);
			panelUser.add(panelUserAttr);
			panelUserAttr.setLayout(null);
			
			JLabel lblNoOfFriends = new JLabel("No. of Friends");
			lblNoOfFriends.setBounds(10, 112, 93, 14);
			panelUserAttr.add(lblNoOfFriends);
			
			comboBox7 = new JComboBox<String>();
			comboBox7.setBounds(20, 137, 67, 20);
			panelUserAttr.add(comboBox7);
			comboBox7.addItem(">");
			comboBox7.addItem(">=");
			comboBox7.addItem("=");
			comboBox7.addItem("<");
			comboBox7.addItem("<=");
			comboBox7.setSelectedIndex(2);
			
			textFriends = new JTextField();
			textFriends.setBounds(97, 137, 61, 20);
			panelUserAttr.add(textFriends);
			textFriends.setColumns(10);
			
			JLabel lblAverageStars = new JLabel("Average Stars");
			lblAverageStars.setBounds(10, 182, 93, 14);
			panelUserAttr.add(lblAverageStars);
			
			comboBox8 = new JComboBox<String>();
			comboBox8.setBounds(20, 207, 67, 20);
			panelUserAttr.add(comboBox8);
			comboBox8.addItem(">");
			comboBox8.addItem(">=");
			comboBox8.addItem("=");
			comboBox8.addItem("<");
			comboBox8.addItem("<=");
			comboBox8.setSelectedIndex(2);
			
			textAvgStar = new JTextField();
			textAvgStar.setBounds(97, 207, 61, 20);
			panelUserAttr.add(textAvgStar);
			textAvgStar.setColumns(10);
			
			JLabel lblNoOfReviews = new JLabel("No. of Reviews");
			lblNoOfReviews.setBounds(10, 256, 93, 14);
			panelUserAttr.add(lblNoOfReviews);
			
			comboBox9 = new JComboBox<String>();
			comboBox9.setBounds(20, 281, 67, 20);
			panelUserAttr.add(comboBox9);
			comboBox9.addItem(">");
			comboBox9.addItem(">=");
			comboBox9.addItem("=");
			comboBox9.addItem("<");
			comboBox9.addItem("<=");
			comboBox9.setSelectedIndex(2);
			
			textReviewno = new JTextField();
			textReviewno.setBounds(97, 281, 61, 20);
			panelUserAttr.add(textReviewno);
			textReviewno.setColumns(10);
			
			JLabel lblMemberSince = new JLabel("Member Since ");
			lblMemberSince.setBounds(10, 347, 93, 14);
			panelUserAttr.add(lblMemberSince);
			
			 comboBoxYear = new JComboBox<String>();
			comboBoxYear.setBounds(81, 372, 77, 24);
			panelUserAttr.add(comboBoxYear);
			for(int i = 0; i <16; i++)
			{
			comboBoxYear.addItem(new Integer(2000+i).toString());
			}
			
			for(int i = 0; i <=16; i++)
			{
			comboBoxFromYear.addItem(new Integer(2000+i).toString());
			}
			for(int i = 0; i <=16; i++)
			{
			comboBoxToYear.addItem(new Integer(2000+i).toString());
			}
			
			
			comboBoxMonth = new JComboBox<String>();
			comboBoxMonth.setBounds(20, 372, 51, 24);
			panelUserAttr.add(comboBoxMonth);
			
			
			comboBox12 = new JComboBox<String>();
			comboBox12.setBounds(67, 53, 61, 29);
			panelUserAttr.add(comboBox12);
			comboBoxMonth.addItem("01");
			comboBoxMonth.addItem("02");
			comboBoxMonth.addItem("03");
			comboBoxMonth.addItem("04");
			comboBoxMonth.addItem("05");
			comboBoxMonth.addItem("06");
			comboBoxMonth.addItem("07");
			comboBoxMonth.addItem("08");
			comboBoxMonth.addItem("09");
			comboBoxMonth.addItem("10");
			comboBoxMonth.addItem("11");			
			comboBoxMonth.addItem("12");
			
			comboBoxFromHour.addItem("01");
			comboBoxFromHour.addItem("02");
			comboBoxFromHour.addItem("03");
			comboBoxFromHour.addItem("04");
			comboBoxFromHour.addItem("05");
			comboBoxFromHour.addItem("06");
			comboBoxFromHour.addItem("07");
			comboBoxFromHour.addItem("08");
			comboBoxFromHour.addItem("09");
			comboBoxFromHour.addItem("10");
			comboBoxFromHour.addItem("11");			
			comboBoxFromHour.addItem("12");
			
			comboBoxToMonth.addItem("01");
			comboBoxToMonth.addItem("02");
			comboBoxToMonth.addItem("03");
			comboBoxToMonth.addItem("04");
			comboBoxToMonth.addItem("05");
			comboBoxToMonth.addItem("06");
			comboBoxToMonth.addItem("07");
			comboBoxToMonth.addItem("08");
			comboBoxToMonth.addItem("09");
			comboBoxToMonth.addItem("10");
			comboBoxToMonth.addItem("11");			
			comboBoxToMonth.addItem("12");
			
			comboBoxToDay.addItem("01");
			comboBoxToDay.addItem("02");
			comboBoxToDay.addItem("03");
			comboBoxToDay.addItem("04");
			comboBoxToDay.addItem("05");
			comboBoxToDay.addItem("06");
			comboBoxToDay.addItem("07");
			comboBoxToDay.addItem("08");
			comboBoxToDay.addItem("09");
			comboBoxToDay.addItem("10");
			comboBoxToDay.addItem("11");			
			comboBoxToDay.addItem("12");			
			comboBoxToDay.addItem("13");
			comboBoxToDay.addItem("14");
			comboBoxToDay.addItem("15");
			comboBoxToDay.addItem("16");
			comboBoxToDay.addItem("17");
			comboBoxToDay.addItem("18");
			comboBoxToDay.addItem("19");
			comboBoxToDay.addItem("20");
			comboBoxToDay.addItem("21");
			comboBoxToDay.addItem("22");
			comboBoxToDay.addItem("23");			
			comboBoxToDay.addItem("24");
			comboBoxToDay.addItem("25");
			comboBoxToDay.addItem("26");
			comboBoxToDay.addItem("27");
			comboBoxToDay.addItem("28");
			comboBoxToDay.addItem("29");
			comboBoxToDay.addItem("30");
			comboBoxToDay.addItem("31");
			
			comboBoxFromDay.addItem("01");
			comboBoxFromDay.addItem("02");
			comboBoxFromDay.addItem("03");
			comboBoxFromDay.addItem("04");
			comboBoxFromDay.addItem("05");
			comboBoxFromDay.addItem("06");
			comboBoxFromDay.addItem("07");
			comboBoxFromDay.addItem("08");
			comboBoxFromDay.addItem("09");
			comboBoxFromDay.addItem("10");
			comboBoxFromDay.addItem("11");			
			comboBoxFromDay.addItem("12");			
			comboBoxFromDay.addItem("13");
			comboBoxFromDay.addItem("14");
			comboBoxFromDay.addItem("15");
			comboBoxFromDay.addItem("16");
			comboBoxFromDay.addItem("17");
			comboBoxFromDay.addItem("18");
			comboBoxFromDay.addItem("19");
			comboBoxFromDay.addItem("20");
			comboBoxFromDay.addItem("21");
			comboBoxFromDay.addItem("22");
			comboBoxFromDay.addItem("23");			
			comboBoxFromDay.addItem("24");
			comboBoxFromDay.addItem("25");
			comboBoxFromDay.addItem("26");
			comboBoxFromDay.addItem("27");
			comboBoxFromDay.addItem("28");
			comboBoxFromDay.addItem("29");
			comboBoxFromDay.addItem("30");
			comboBoxFromDay.addItem("31");
			
			
			comboBoxFromYear.setSelectedIndex(0);
			comboBoxToYear.setSelectedIndex(16);
			comboBoxFromHour.setSelectedIndex(0);
			comboBoxToMonth.setSelectedIndex(11);
			comboBoxFromDay.setSelectedIndex(0);
			comboBoxToDay.setSelectedIndex(30);
			
			comboBox12.addItem("AND");
			comboBox12.addItem("OR");
			
			JScrollPane scrollPaneSql = new JScrollPane();
			scrollPaneSql.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneSql.setBounds(220, 11, 446, 115);
			panelUser.add(scrollPaneSql);
			
			
			textSqlQuery2 = new JTextArea();
			textSqlQuery2.setDisabledTextColor(Color.BLACK);
			textSqlQuery2.setForeground(Color.BLACK);
			textSqlQuery2.setLineWrap(true);
			textSqlQuery2.setFont(new Font("Calibri", Font.ITALIC, 13));
			textSqlQuery2.setEditable(false);
			scrollPaneSql.setViewportView(textSqlQuery2);
			
			JScrollPane scrollPaneDisplay2 = new JScrollPane();
			scrollPaneDisplay2.setBounds(220, 137, 446, 316);
			panelUser.add(scrollPaneDisplay2);
			
			JPanel panelDisplay2 = new JPanel();
			scrollPaneDisplay2.setViewportView(panelDisplay2);
			panelDisplay2.setLayout(null);
			
			JScrollPane scrollPaneResultUser = new JScrollPane();
			scrollPaneResultUser.setBounds(10, 11, 424, 256);
			panelDisplay2.add(scrollPaneResultUser);
			
			Resultuser = new JTable();
			Resultuser.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					UserSelected(e);
				}
			});
			scrollPaneResultUser.setViewportView(Resultuser);
			
			
			lblUserName = new JLabel("Result");
			lblUserName.setHorizontalTextPosition(SwingConstants.CENTER);
			lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
			lblUserName.setBounds(10, 271, 424, 32);
			panelDisplay2.add(lblUserName);
			
			JButton btnNewButton = new JButton("Execute Query");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ExecuteUserQuery(arg0);
				}
			});
			btnNewButton.setBounds(178, 497, 145, 36);
			panelUser.add(btnNewButton);
			
			
			
	}
	
	 protected void UserSelected(MouseEvent e) {
		// TODO Auto-generated method stub
		 String uName = (String) Resultuser.getValueAt(Resultuser.getSelectedRow(), 1);
		 String YID = (String) Resultuser.getValueAt(Resultuser.getSelectedRow(), 3);
		 String rQuery = "select r.DATE_OF_REVIEW as \"Date\", r.TEXT as \"Reviews\", b.NAME as \"Business\"  from   REVIEW r, BUSINESS b where r.BUSINESS_ID = b.BUSINESS_ID and r.USER_ID = '" + YID+"'";
		 Connection con = null;
	        try {
	            con = SQLConnection();
	            textSqlQuery2.setText(rQuery);
	            PreparedStatement stmt = null;
	           	stmt = con.prepareStatement(rQuery);
			
			ResultSet rs = stmt.executeQuery();
			DefaultTableModel model = null;
			String str;
			
			  if (rs.next()){
	              ResultSetMetaData rsmd = rs.getMetaData();
	          model = new DefaultTableModel();
	          model.setColumnCount(rsmd.getColumnCount());
	          Vector<String> cols = new Vector<String>();
	          for (int i = 0; i < rsmd.getColumnCount(); i++) {
	              cols.add(rsmd.getColumnName(i + 1));
	          }
	          model.setColumnIdentifiers(cols);
	          do {
	              cols = new Vector<String>();
	              
	              for (int i = 0; i < rsmd.getColumnCount(); i++) {
	            	  str = rs.getString(rsmd.getColumnName(i+1));
	                  cols.add(str);
	              }
	              model.addRow(cols);
	              
	          }while(rs.next());
			  }
			  Resultuser.setModel(model);
			  lblUserName.setText(uName + "'s reviews");
			  Resultuser.setRowSelectionAllowed(false);
			  Resultuser.setCellSelectionEnabled(false);
			}catch( SQLException e1)
	              {
	            	  e1.printStackTrace();
	              }	        
		 lblBusinessName.setText(uName + "Reviews");		
	}

	protected void BusinessSelect(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(!flagReviewtableup)
		{
		String bName = (String) ResultBusiness.getValueAt(ResultBusiness.getSelectedRow(), 5);
		 String bID = (String) ResultBusiness.getValueAt(ResultBusiness.getSelectedRow(), 0);
		 String rQuery = "select r.DATE_OF_REVIEW as \"Date\", r.TEXT as \"Reviews\", r.STARS as \"Stars\", u.NAME as \"Author\"  from review r, users u where r.USER_ID = u.USER_ID and r.BUSINESS_ID = '" + bID+"'";
		 Connection con = null;
		 textAreaSQLBusiness.setText(rQuery);
	        try {
	            con = SQLConnection();
	            PreparedStatement stmt;	
	      	stmt = con.prepareStatement(rQuery);
			
			ResultSet rs = stmt.executeQuery();
			DefaultTableModel model = null;
			
			  while (rs.next()){
	          ResultSetMetaData rsmd = rs.getMetaData();
	          model = new DefaultTableModel();
	          model.setColumnCount(rsmd.getColumnCount());
	          Vector<String> cols = new Vector<String>();
	          for (int i = 0; i < rsmd.getColumnCount(); i++) {
	              cols.add(rsmd.getColumnName(i + 1));
	          }
	          model.setColumnIdentifiers(cols);
	          do{
	              cols = new Vector<String>();
	              for (int i = 0; i < rsmd.getColumnCount(); i++) {
	                  cols.add(rs.getString(rsmd.getColumnName(i + 1)));
	              }
	              model.addRow(cols);
	          }while (rs.next()) ;
			  }
			  ResultBusiness.setModel(model);
			  ResultBusiness.setRowSelectionAllowed(false);
			  ResultBusiness.setCellSelectionEnabled(false);
			  flagReviewtableup = true;

			}catch( SQLException e1)
	              {
	            	  e1.printStackTrace();
	              }
	        
		 lblBusinessName.setText(bName);
		 
		  
		}
	}

	protected void ExecuteUserQuery(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 String operFriends = null;
		 String noOfFriends = null;
		 String operStars = null;
		 String noOfStars = null;
		 String operReviews = null;
		 String noOfReviews = null;
		 String year = null;
		 String month = null;
		operFriends = comboBox7.getSelectedItem().toString();
		 noOfFriends = textFriends.getText();
		 operStars = comboBox8.getSelectedItem().toString();
		 noOfStars = textAvgStar.getText();
		 operReviews = comboBox9.getSelectedItem().toString();
		 noOfReviews = textReviewno.getText();
		 year = comboBoxYear.getSelectedItem().toString();
		 month = comboBoxMonth.getSelectedItem().toString();
		 String operator = comboBox12.getSelectedItem().toString();
		 try{
			  String yelping_since = year+"-"+month;
			  BigDecimal count = new BigDecimal(noOfReviews);
			 BigDecimal star =new BigDecimal(noOfStars);
			 BigDecimal friends_count=new BigDecimal(noOfFriends);
			
			if(!noOfReviews.isEmpty() && !noOfStars.isEmpty() && !noOfFriends.isEmpty())
			{
			String query1 = null;
			query1 = "SELECT * from  USERS WHERE to_char((YELPING_SINCE),'YYYY-MM') "+"> "+"'"+yelping_since+"' " + operator +" REVIEW_COUNT "+operReviews+" '"
			  +count+"' "+ operator  + " AVERAGE_STARS "+operStars+" '"+star+"' " + operator+ " FRIENDS_COUNT "+operFriends+" '"+friends_count+"'";
			    System.out.println(query1);
			    textSqlQuery2.setText(query1);
			    Connection con = SQLConnection();
			    PreparedStatement stmt = con.prepareStatement(query1);
			    ResultSet rs = stmt.executeQuery();
			    DefaultTableModel model = new DefaultTableModel();
			    while(rs.next()){			       
			      ResultSetMetaData rsmd = rs.getMetaData();
			            
			            model.setColumnCount(rsmd.getColumnCount());
			            Vector<String> cols = new Vector<String>();
			            for (int i = 0; i < rsmd.getColumnCount(); i++) {
			                cols.add(rsmd.getColumnName(i + 1));
			            }
			            model.setColumnIdentifiers(cols);
			            do {
			                cols = new Vector<String>();
			                for (int i = 0; i < rsmd.getColumnCount(); i++) {
			                    cols.add(rs.getString(rsmd.getColumnName(i + 1)));
			                }
			                model.addRow(cols);
			            }while (rs.next());
			            Resultuser.setModel(model);
			            Resultuser.setRowSelectionAllowed(true);
			   
			        }
			        
			 }
		 }catch (SQLException ex) {
			            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
			        
			        } 
		 
		 comboBox7.setSelectedIndex(2);
		 comboBox8.setSelectedIndex(2);
		 comboBox9.setSelectedIndex(2);
		 textFriends.setText("");
		 textReviewno.setText("");
		 textAvgStar.setText("");

		
	}

	protected void ExecuteBusinessQuery(ActionEvent arg0){
		// TODO Auto-generated method stub
		 ArrayList<String> categoryList = new ArrayList<String>();
		 ArrayList<String> subcategoryList = new ArrayList<String>(); 
		 
		 for(Component c : panelcat.getComponents())
		 {
			 if (c.getClass().equals(JCheckBox.class)) {
                 JCheckBox jcb = (JCheckBox) c;
                 if (jcb.isSelected()) {
                     categoryList.add(jcb.getText());
                     flagCat = false;
                 }
			 	}
			 
		 }
		 if(categoryList.isEmpty()){
			 flagCat = true;
		 }
		 
		 for(Component c : panelsub.getComponents())
		 {
			 if (c.getClass().equals(JCheckBox.class)) {
                 JCheckBox jscb = (JCheckBox) c;
                 if (jscb.isSelected()) {
                     subcategoryList.add(jscb.getText().substring(0, jscb.getText().length()-1 ));
                     flagCat = false;
                 }
			 	}
			 
		 }
		 String noOfVotes = null;
		 noOfVotes= textVote.getText();
		 String operVotes = null;
		 String operStars = null;
		 operVotes = comboBox1.getSelectedItem().toString();
		 operStars = comboBox4.getSelectedItem().toString();
		 int dayFrom;
		 int dayTo;
		 String noOfStars = null;
		 noOfStars = textStars.getText();
		 dayFrom = comboBox2.getSelectedIndex();
		 dayTo = comboBox3.getSelectedIndex();
		 int HourFrom;
		 String operator = comboBox5.getSelectedItem().toString();
		 HourFrom = (int)Integer.parseInt(comboBox10.getSelectedItem().toString());
		 int HourTo;
		 HourTo = (int)Integer.parseInt(comboBox11.getSelectedItem().toString());
		 if((comboBox2.getSelectedIndex() < comboBox3.getSelectedIndex()) || (comboBox2.getSelectedIndex() == comboBox3.getSelectedIndex() && comboBox10.getSelectedIndex() < comboBox11.getSelectedIndex()))
			 flagCheckin = true;
		 else
			 flagCheckin = false;
		 if(noOfVotes.isEmpty())
			 flagCheckin = true;
		 else
			 flagCheckin = false;
		 if(noOfStars.isEmpty())
			 flagStar  = true;
		 else
			 flagStar = false;
		 if(textFieldReviewStars.getText().isEmpty() && textVote.getText().isEmpty() && ((comboBoxFromYear.getSelectedIndex() < comboBoxToYear.getSelectedIndex()) || (comboBoxFromYear.getSelectedIndex() == comboBoxToYear.getSelectedIndex() && comboBoxFromHour.getSelectedIndex() < comboBoxToMonth.getSelectedIndex())))
			 flagReview  = true;
		 else
			 flagReview = false;
		 
		 
		 
		 String Query = null;
		 Query = "SELECT * FROM BUSINESS WHERE ";
		 
		 if(!flagStar)
		 {
			 Query += "STARS " + operStars + " " + noOfStars + " "; 
		 }
		 
		 
		 if(!flagCat)
		 {
			 if(!flagStar)
			 {
				 Query += operator +" ";
			 }
			 Query = Query + "BUSINESS_ID IN (SELECT BUSINESS_ID FROM BUSINESS_CAT_SUB WHERE ";
		 
			 if (categoryList.size() > 0) {
				 
				 for (int i = 0; i < categoryList.size(); i++) {
		                if (i == categoryList.size() - 1 && subcategoryList.isEmpty()) {
		                    Query = Query + " CATEGORY_NAME = '" + categoryList.get(i) + "' ";
		                } else {
		                    Query = Query + " CATEGORY_NAME = '" + categoryList.get(i) + "' " + operator + " ";
		                }
		            }
				 
		 }
			 if (subcategoryList.size() > 0) {
				 	 for (int i = 0; i < subcategoryList.size(); i++) {
		                if (i == subcategoryList.size() - 1) {
		                    Query = Query + " SUB_CATEGORY_NAME = '" + subcategoryList.get(i) + "' ";
		                } else {
		                    Query = Query + " SUB_CATEGORY_NAME = '" + subcategoryList.get(i) + "' " + operator + " ";
		                }
		            }		 
		 }
			 
		}
		if(!flagCheckin)
		{
			if(!flagCat)
			{
				Query += " UNION ";
			}
			else 
			{
				if(!flagStar)
				{
					Query += operator;
				}
				Query += " BUSINESS_ID IN ( "; 
			}
			
			Query += "SELECT BUSINESS_ID FROM CHECK_IN WHERE ";
			
				Float floatFrom = dayFrom + HourFrom/24F;
				Float floatTo = dayTo + HourTo/24F;
				
				Query += "DAYANDTIME >= " + floatFrom.toString() + " AND DAYANDTIME <= " + floatTo.toString(); 
				if(!noOfVotes.isEmpty())
					Query += " AND IN_COUNT " + operVotes + textVote.getText() ;  
				
				}
	
		if(!flagReview)
		{
			if(!flagCat || !flagCheckin)
			{
				Query += " UNION ";
			}
			else 
			{
				if(!flagStar)
				{
					Query += operator;
				}
				Query += " BUSINESS_ID IN ( "; 
			}
			
			Query += "SELECT BUSINESS_ID FROM REVIEW WHERE ";
				if(!textFieldReviewStars.getText().isEmpty())
					Query += "STARS " + comboBoxReviewStars.getSelectedItem().toString() + " " +textFieldReviewStars.getText() + " AND ";
				if(!textFieldVote.getText().isEmpty())
					Query += "VOTES " + comboBoxVotes.getSelectedItem().toString() + " " +textFieldVote.getText() + " AND ";
				Query += "DATE_OF_REVIEW BETWEEN TO_DATE('" +comboBoxFromYear.getSelectedItem().toString()+"-"+comboBoxFromHour.getSelectedItem().toString()+"-"+comboBoxFromDay.getSelectedItem().toString()+"','YYYY-MM-DD') AND TO_DATE('" +comboBoxToYear.getSelectedItem().toString()+"-"+comboBoxFromHour.getSelectedItem().toString()+"-"+comboBoxToDay.getSelectedItem().toString()+"','YYYY-MM-DD')";  
				
				}
		if (!flagCat || !flagCheckin || !flagReview)
			Query += ")";
		textAreaSQLBusiness.setText("");
		textAreaSQLBusiness.setText(Query);
		comboBox1.setSelectedIndex(2);
		 comboBox2.setSelectedIndex(0);
		 comboBox3.setSelectedIndex(0);
		 comboBox4.setSelectedIndex(2);
		 comboBox10.setSelectedIndex(0);
		 comboBox11.setSelectedIndex(0);
		 textStars.setText("");
		 textVote.setText("");
		 textFieldReviewStars.setText("");
		 textFieldVote.setText("");
		 lblBusinessName.setText("Result");
		 for(Component c : panelcat.getComponents())
		 {
			 if (c.getClass().equals(JCheckBox.class)) {
                 JCheckBox jcb = (JCheckBox) c;
                 if (jcb.isSelected()) {
                     jcb.setSelected(false);
                     
                 }
			 	}
			 
		 }
		 
		panelsub.removeAll();
		panelsub.repaint();
		 flagCat = false;
		 flagCheckin = false;
		 flagStar = false;
		 Connection dbConnection = SQLConnection();
		PreparedStatement stmt;
		try {
			stmt = dbConnection.prepareStatement(Query);
			
		System.out.println(Query);
		
		ResultSet rs = stmt.executeQuery();
		
		  while (rs.next()){
              ResultSetMetaData rsmd = rs.getMetaData();
          DefaultTableModel model = new DefaultTableModel();
          model.setColumnCount(rsmd.getColumnCount());
          Vector<String> cols = new Vector<String>();
          for (int i = 0; i < rsmd.getColumnCount(); i++) {
              cols.add(rsmd.getColumnName(i + 1));
          }
          model.setColumnIdentifiers(cols);
          do {
              cols = new Vector<String>();
              for (int i = 0; i < rsmd.getColumnCount(); i++) {
                  cols.add(rs.getString(rsmd.getColumnName(i + 1)));
              }
              model.addRow(cols);
          }while (rs.next());
		  
          ResultBusiness.removeAll();
          ResultBusiness.setModel(model);
          ResultBusiness.setRowSelectionAllowed(true);
          ResultBusiness.repaint();
          flagReviewtableup = false;
         
     
          
 
      }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   }
      

	public static Connection SQLConnection(){
		 Connection connection = null;
		 try {
			 DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			connection = DriverManager.getConnection ("jdbc:oracle:thin:localhost:1521:orcl", dbname, dbpass);
			 System.out.println("Connection Succesful");
			 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		 
		 
	 }

	protected void getSubcategories(java.awt.event.ActionEvent evt) {
		Connection con = null;
        try {
            con = SQLConnection();
            String sQuery = "";
            ArrayList<String> BusnSel = new ArrayList<String>();
            for (Component c : panelcat.getComponents()) {
                if (c.getClass().equals(javax.swing.JCheckBox.class)) {
                    JCheckBox jcb = (JCheckBox) c;
                    if (jcb.isSelected()) {
                        BusnSel.add(jcb.getText());
                    }
                }
            }
           sQuery = sQuery + "SELECT DISTINCT SUB_CATEGORY_NAME FROM BUSINESS_CAT_SUB WHERE ";
            for (int i = 0; i < BusnSel.size(); i++) {
                if (i == BusnSel.size() - 1) {
                    sQuery = sQuery + " CATEGORY_NAME = ?";
                } else {
                    sQuery = sQuery + " CATEGORY_NAME = ? OR";
                }
            }
            panelsub.removeAll();
            PreparedStatement ps = con.prepareStatement(sQuery);
            int iCtr = 1;
            for (String str : BusnSel) {
                ps.setString(iCtr, str);
                iCtr++;
            }
            ResultSet rs = ps.executeQuery();
            subCatList = new ArrayList<JCheckBox>();
            while (rs.next()) {
                String result = rs.getString(1) + "\n";
                JCheckBox newChBox = new JCheckBox();
                newChBox.setText(result);
                subCatList.add(newChBox);
            }
            panelsub.setLayout(new GridLayout(0, 2, 10, 10));
            for (JCheckBox ch : subCatList) {
                panelsub.add(ch);
                panelsub.revalidate();
                panelsub.repaint();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed
}
