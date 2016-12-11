package hw3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;
import org.json.JSONArray;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;



public class hw4
{
	private JFrame frmYelpmongobk;
	private JTable businessTable;
	private JPanel panel_1;
	private JComboBox<String> comboBox_pointOfInterest;
	private JTable reviewTable;
	private String selected_catg;
	private JComboBox<String> comboBox_Searchfor; 
	private JScrollPane scrollPane_review ;
	private JComboBox<String> comboBox_Proximity ;
	private  List<String> listattributes ;
	ArrayList<String> Selected_listCategories = new ArrayList<String>();
	ArrayList<String> Selected_listAttributes = new ArrayList<String>();
	List<String> listofBusinessId = new ArrayList<String>();
	JTextPane textPane;
	JComboBox<String> comboBox;
	JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_1_1;
	
	/* Connection to 
	 * MongoDB*
	 */
	   MongoClient mongoClient = new MongoClient("localhost",27017);
	   DB db = mongoClient.getDB("yelpdb");
	   DBCollection dbcollection=db.getCollection("business");
	   DBCollection new_collection=db.getCollection("business_db");
		BasicDBObject businessQuery = new BasicDBObject();
		BasicDBObject projection = new BasicDBObject();		
		private JTextField textField;
		private JTextField textField_1;
	/*Connection 
	 * Established Successfully
	 */
/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{	
			public void run()
			{	try
				{	hw4 window = new hw4();
					window.frmYelpmongobk.setVisible(true);
				} catch (Exception e) 	{ e.printStackTrace(); 	}
			}
		});
	}

	/**
	 * * Create the application.
	 */
	public hw4()
	{
		if(!db.collectionExists("business_db"))
			newCollection();
		initialize();
		
	}	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmYelpmongobk = new JFrame();
		frmYelpmongobk.setTitle("Yelp_Mongo_BK");
		frmYelpmongobk.getContentPane().setForeground(Color.BLACK);
		frmYelpmongobk.setBounds(20, 20, 1050, 650);
		frmYelpmongobk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setMaximumSize(new Dimension(100, 100));
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JLabel lblNewLabel = new JLabel("Point of Interest");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("Proximity");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblSearchFor = new JLabel("AND/OR");
		lblSearchFor.setForeground(new Color(0, 0, 0));
		lblSearchFor.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JButton searchBtn = new JButton("Bus.Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OnSearchPopulateTable();
			}
		});
		comboBox_pointOfInterest = new JComboBox();
		
		AddressClass address1 = new AddressClass("4237 Lien Rd\nSte H\nMayfair Park\nMadison, WI 53704", -89.3083801269531, 43.1205749511719);
		AddressClass address2 = new AddressClass("4840 E Indian School Rd\nSte 101\nPhoenix, AZ 85018",  -111.983757019043, 33.4993133544922);
		AddressClass address3 = new AddressClass("Mesa, AZ 85206", -111.701843261719, 33.3951606750488);
		AddressClass address4 = new AddressClass("3921 E Baseline Rd\nSte 108\nGilbert, AZ 85234",  -111.747520446777, 33.3782119750977);
		AddressClass address5 = new AddressClass("1000 N Green Valley Pkwy\nHenderson, NV 89012", -115.083946228027, 43.1205749511719);
		
		comboBox_pointOfInterest.setModel(new DefaultComboBoxModel<String>(new String[] {"NONE", address1.Address,address2.Address,address3.Address ,address4.Address ,address5.Address}));
		
		comboBox_Proximity = new JComboBox();
		comboBox_Proximity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OnSearchPopulateTable();
			}
		});
		comboBox_Proximity.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "5", "10", "20", "30", "50"}));
		
		comboBox_Searchfor = new JComboBox();
		comboBox_Searchfor.setModel(new DefaultComboBoxModel(new String[] {"NONE", "OR", "AND"}));
		
		
		textPane = new JTextPane();
		
	
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblSatrs = new JLabel("Stars");
		lblSatrs.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JPanel panelUser = new JPanel();
		panelUser.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelUser.setName("User");
		panelUser.setToolTipText("User");
		
		GroupLayout groupLayout = new GroupLayout(frmYelpmongobk.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(52)
													.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblSearchFor)
														.addComponent(lblNewLabel_1)))
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblSatrs, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addGap(30)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_pointOfInterest, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(comboBox_Proximity, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_Searchfor, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
											.addGap(45)
											.addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSatrs, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox_pointOfInterest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1)
										.addComponent(comboBox_Proximity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox_Searchfor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSearchFor)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
							.addGap(11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
							.addGap(37))))
		);
		
		JLabel lblNoOfFriends = new JLabel("No. of Reviews");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblMemberSince = new JLabel("Member since");
		
		
		comboBox = new JComboBox<String>();
		
		comboBox_1 = new JComboBox<String>();
		for(int i = 0; i <16; i++)
		{
		comboBox.addItem(new Integer(2000+i).toString());
		}

		
		
		comboBox_1_1 = new JComboBox<String>();
		comboBox_1_1.setBounds(20, 372, 51, 24);
		
		
		
	
		comboBox_1_1.addItem("01");
		comboBox_1_1.addItem("02");
		comboBox_1_1.addItem("03");
		comboBox_1_1.addItem("04");
		comboBox_1_1.addItem("05");
		comboBox_1_1.addItem("06");
		comboBox_1_1.addItem("07");
		comboBox_1_1.addItem("08");
		comboBox_1_1.addItem("09");
		comboBox_1_1.addItem("10");
		comboBox_1_1.addItem("11");			
		comboBox_1_1.addItem("12");
		
		JButton btnUsersearch = new JButton("UserSearch");
		btnUsersearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserSearch(arg0);
			}
		});
		GroupLayout gl_panelUser = new GroupLayout(panelUser);
		gl_panelUser.setHorizontalGroup(
			gl_panelUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUser.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panelUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNoOfFriends, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblMemberSince, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_1, Alignment.TRAILING))
					.addGap(18)
					.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUsersearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelUser.setVerticalGroup(
			gl_panelUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelUser.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoOfFriends)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUsersearch, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMemberSince)
						.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		panelUser.setLayout(gl_panelUser);
		
		businessTable = new JTable();
		businessTable.setToolTipText("");
		scrollPane_2.setViewportView(businessTable);
		businessTable.addMouseListener(new java.awt.event.MouseAdapter()
		{@Override
			public void mouseClicked(MouseEvent evt)
			{ 	
				int rowID = businessTable.rowAtPoint(evt.getPoint());
				String business_name = businessTable.getModel().getValueAt(rowID, 0).toString();
				
				
				DBCollection dbcollection=db.getCollection("business_db");
				BasicDBObject businessQuery=new BasicDBObject();
				BasicDBObject businessProjection=new BasicDBObject();
				businessQuery.put("name",business_name);
				businessProjection.put("b_id", 1);
				DBCursor cursor=dbcollection.find(businessQuery,businessProjection);
				String business_Id="";
				while(cursor.hasNext()){
					DBObject result=cursor.next();
					business_Id=(String) result.get("b_id");
					}
				if (rowID >= 0)
				{
					JFrame reviewframe = new JFrame();
					reviewframe.setSize(600, 600);
					reviewframe.setTitle("Reviews Table");
					reviewframe.setVisible(true);
						reviewTable = new JTable();
						DBCollection dbCollection=db.getCollection("review");
						BasicDBObject reviewQuery=new BasicDBObject();
						BasicDBObject reviewProjection=new BasicDBObject();
						
						if(businessTable.getModel().getColumnName(3) == "User ID")
						{
							
							String user_id = (String) businessTable.getModel().getValueAt(rowID, 3);
									reviewQuery.put("user_id", user_id);
									reviewProjection.put("business_id", 1);
						}
						else
						{
						reviewQuery.put("business_id", business_Id);
						reviewProjection.put("user_id", 1);
						}
						reviewProjection.put("date", 1);
						reviewProjection.put("stars", 1);
						reviewProjection.put("text", 1);
						
						reviewProjection.put("votes.useful", 1);
						textPane.setText(reviewQuery+","+reviewProjection);
				    	textPane.repaint();
				    	DBCursor cursor1=dbCollection.find(reviewQuery,reviewProjection);
						DBObject result;
						/*Creating
						 * Review table 
						 */
						String[] columnNames = new String[]{"Review Date", "Stars", "Review Text","Name"};
						DefaultTableModel model1 = new DefaultTableModel(columnNames, 0);
						reviewTable.setModel(model1);
						reviewTable.setShowGrid(true);
						String user_name=null;
						   int i=0;
						while(cursor1.hasNext()){
						   i++;
						  // System.out.println("reached reviews");
						   result=cursor1.next();
						   String r_date=(String) result.get("date");
						   String r_text=(String) result.get("text");
						   int r_stars=(int)result.get("stars");
						   String r_user=(String) result.get("user_id");
						 
						   
						   if (businessTable.getModel().getColumnName(3) == "User ID") {
							   DBCollection userCollection = db.getCollection("business_db");
								BasicDBObject userQuery = new BasicDBObject();
								BasicDBObject userProjection = new BasicDBObject();
								r_user = (String) result.get("business_id");
								userQuery.put("b_id", r_user);
								userProjection.put("name", 1);
								DBCursor cursor2 = userCollection.find(userQuery, userProjection);
								while (cursor2.hasNext()) {
									result = cursor2.next();
									user_name = (String) result.get("name");
								} 
						   }
						   else{
							DBCollection userCollection = db.getCollection("user");
							BasicDBObject userQuery = new BasicDBObject();
							BasicDBObject userProjection = new BasicDBObject();
							userQuery.put("user_id", r_user);
							userProjection.put("name", 1);
							DBCursor cursor2 = userCollection.find(userQuery, userProjection);
							while (cursor2.hasNext()) {
								result = cursor2.next();
								user_name = (String) result.get("name");
							} 
						}
						model1.addRow(new Object[]{r_date, r_stars, r_text,user_name});
					
						  // System.out.println("[R_date:"+r_date+"] [R_stars:"+r_stars+"] [R_text:"+r_text+"][R_user:"+user_name+"][ R_VotesUseful:"+r_votesUseful+"]");
						    }
					scrollPane_review = new JScrollPane();
					reviewframe.getContentPane().add(scrollPane_review);
					scrollPane_review.add(reviewTable);
					scrollPane_review.setViewportView(reviewTable);
					scrollPane_review.createHorizontalScrollBar();
					scrollPane_review.revalidate();
					scrollPane_review.repaint();
				}
		}
	});
		//scrollPane_review.setViewportView(reviewTable);
				
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		frmYelpmongobk.getContentPane().setLayout(groupLayout);
		
		//Code start's here
		   		   
		//Categories Data-28 categories
		String[] categories={"Active Life","Arts & Entertainment","Automotive","Car Rental","Cafes","Beauty & Spas","Convenience Stores",
				 "Dentists", "Doctors", "Drugstores", "Department Stores","Education", "Event Planning & Services", "Flowers & Gifts",
				"Food", "Health & Medical", "Home Services","Home & Garden","Hospitals","Hotels & Travel","Hardware Stores","Grocery",
				"Medical Centers", "Nurseries & Gardening","Nightlife","Restaurants","Shopping","Transportation"};
		JCheckBox checkbox1;
		panel.setLayout(new GridLayout(categories.length, 1, 0, 0));
		for(String s:categories){
			checkbox1=new JCheckBox();
			checkbox1.setText(s);
			checkbox1.addItemListener(new ItemListener()
			{	public void itemStateChanged(ItemEvent e)
				{
				JCheckBox checkbox = (JCheckBox) (e.getItemSelectable());
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					
					selected_catg = checkbox.getText();
					Selected_listCategories.add(selected_catg);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					selected_catg = checkbox.getText();
					Selected_listCategories.remove(selected_catg);
				}
				
				try {
					callAttributes();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(checkbox1);
		}
		//mongoClient.close();
	}	 	//Main Function End's Here
	
	/*new_collection known as business_db*/
	
	protected void UserSearch(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int noofReviews = new Integer(textField_1.getText()).intValue();
		String year = comboBox.getSelectedItem().toString();
		String month = comboBox_1_1.getSelectedItem().toString();
		
		try{
			DBCollection dbCollection=db.getCollection("user");
			BasicDBObject reviewQuery=new BasicDBObject();
			BasicDBObject reviewProjection=new BasicDBObject();
			reviewQuery.put("review_count", noofReviews);
			reviewQuery.append("yelping_since", new BasicDBObject("$gt", year+"-"+month));
			
			reviewProjection.put("review_count", 1);
			reviewProjection.put("name", 1);
			reviewProjection.put("yelping_since", 1);
			reviewProjection.put("user_id", 1);
			
			
			DBCursor cursor=dbCollection.find(reviewQuery,reviewProjection);
			
			DBObject res;
    	textPane.setText(reviewQuery+","+reviewProjection);
    	textPane.repaint();
    	String[] columnNames = new String[]{"Name", "ReviewCount", "Member Since","User ID"};
    	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    	businessTable.setModel(model);
    	businessTable.setShowGrid(true);
        	   
       while(cursor.hasNext()){
    	   
    	   res=cursor.next();
    	   String u_name=(String) res.get("name");
    	   String u_reviewCount= new Integer((int) res.get("review_count")).toString();
    	   String u_memSince=(String) res.get("yelping_since");
    	   String b_stars=(String) res.get("user_id");
    	  
    	   
    	   model.addRow(new Object[]{u_name, u_reviewCount, u_memSince,b_stars});
    	    }
       businessTable.setModel(model);
       businessTable.setShowGrid(true);
       cursor.close();
      
    	}
    		catch (Exception e) 	{ e.printStackTrace();}
		
		
		
	}

	public void newCollection(){
	
	MongoClient mongoClient = new MongoClient("localhost",27017);
	 MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
     DBCollection b_collection = client.getDB("yelpdb").getCollection("business");
     if(client.getDB("yelpdb").getCollection("business_db").count() > 0)
     {
    	 BasicDBObject query = new BasicDBObject();
    	 client.getDB("yelpdb").getCollection("business_db").remove(query);
     }
	 final DBCollection new_collection = client.getDB("yelpdb").getCollection("business_db");

			           BasicDBObject query = new BasicDBObject();
			           DBCursor cursor = b_collection.find(query);
		            while(cursor.hasNext()){
			        	   try {
			        		   DBObject result = cursor.next();
			                   JSONParser parser = new JSONParser();
			                   JSONObject jsonObject;
			                   jsonObject = (JSONObject)parser.parse(result.toString());
			                   String business_id = (String) jsonObject.get("business_id");
			                   BasicDBObject doc = new BasicDBObject("b_id", business_id);
			                   String full_address = (String)jsonObject.get("full_address");
			                   doc.append("full_address", full_address);
			                   String city = (String) jsonObject.get("city");
			                   doc.append("city", city);
			                   String review_count = jsonObject.get("review_count").toString();
			                   doc.append("review_count", review_count);
			                   String businessName = jsonObject.get("name").toString();
			                   doc.append("name", businessName);
			                   String longitude = jsonObject.get("longitude").toString();
			                   String latitude = jsonObject.get("latitude").toString();
			                   Float[] Loc={Float.parseFloat(longitude),Float.parseFloat(latitude)};
			                   doc.append("Loc",new Document().append("longitude", Float.parseFloat(longitude)).append("latitude",Float.parseFloat(latitude)));
			                   String state = (String) jsonObject.get("state");
			                   doc.append("state", state);
			                   String stars = jsonObject.get("stars").toString();
			                   doc.append("stars", stars);
			                   String bType = (String) jsonObject.get("type");
			                   doc.append("type",bType);
			                   org.json.simple.JSONArray category=(org.json.simple.JSONArray)jsonObject.get("categories");
			                   
			                   List<String> subCategory = new ArrayList<>();
								List<String> mainCat = new ArrayList<>();
								
								String[] categories={"Active Life","Arts & Entertainment","Automotive","Car Rental","Cafes","Beauty & Spas","Convenience Stores",
										 "Dentists", "Doctors", "Drugstores", "Department Stores","Education", "Event Planning & Services", "Flowers & Gifts",
										"Food", "Health & Medical", "Home Services","Home & Garden","Hospitals","Hotels & Travel","Hardware Stores","Grocery",
										"Medical Centers", "Nurseries & Gardening","Nightlife","Restaurants","Shopping","Transportation"};
								for (Object object : category) {
									if (Arrays.asList(categories).contains(object.toString())) {
										mainCat.add(object.toString());
									}
									else
									{
										subCategory.add(object.toString());
									}
								}
								doc.append("subcategories", subCategory);
								doc.append("categories", mainCat);								
								new_collection.insert(doc);
			    			 
			        	   }
			        	   catch( ParseException e){
			        		   e.printStackTrace();
			        		   
			        	   }
		            }
		           }


	public static boolean checkforJson(String str){
        
        if (str.trim().contains("{") || str.trim().contains("[")) {
            return str.trim().charAt(0) == '{' || str.trim().charAt(0) == '[';
        } else {
            return false;
        }                    
    }
	/*Populating the attributes column*/
	
		@SuppressWarnings("unchecked")
		public void callAttributes() throws ParseException{
		//Attributes Data
		DBCollection dbcollection=db.getCollection("business_db");
		businessQuery.put("categories", new BasicDBObject("$in", Selected_listCategories));
		projection.put("subcategories",1);
		projection.put("_id",0);
		
		DBCursor cursor=dbcollection.find(businessQuery,projection);
		DBObject result;
		   HashMap<String, String> attr=new HashMap<>();
		   Set<String> attributes = new HashSet<String>();
		   Set<String> mySet = new HashSet<String>();
		   String[] categories={"Active Life","Arts & Entertainment","Automotive","Car Rental","Cafes","Beauty & Spas","Convenience Stores",
					 "Dentists", "Doctors", "Drugstores", "Department Stores","Education", "Event Planning & Services", "Flowers & Gifts",
					"Food", "Health & Medical", "Home Services","Home & Garden","Hospitals","Hotels & Travel","Hardware Stores","Grocery",
					"Medical Centers", "Nurseries & Gardening","Nightlife","Restaurants","Shopping","Transportation"};
		   ArrayList<String> temp = new ArrayList<String>();
		   while (cursor.hasNext())
			{
			   result=cursor.next();
			   temp = (ArrayList<String>)result.get("subcategories");
			  
			  if(temp!= null)
				  mySet.addAll(temp);                
				
		   }  
		  listattributes = new ArrayList<String>(new LinkedHashSet<String>(mySet));
		 
		  
		  JCheckBox checkbox2;
		  panel_1.removeAll();
			panel_1.setLayout(new GridLayout(listattributes.size(), 1, 0, 0));
			for(String s:listattributes){
				checkbox2=new JCheckBox();
				checkbox2.setText(s);
				checkbox2.addItemListener(new ItemListener()
				{	public void itemStateChanged(ItemEvent e)
					{	JCheckBox checkboxAttr = (JCheckBox) (e.getItemSelectable());
					if (e.getStateChange() == ItemEvent.SELECTED)
					{	Selected_listAttributes.add(checkboxAttr.getText());
					}else if (e.getStateChange() == ItemEvent.DESELECTED) {
						Selected_listAttributes.remove(checkboxAttr.getText());
					}
				}
			});
				panel_1.add(checkbox2);
				panel_1.revalidate();
				panel_1.repaint();
			}
			
	}
	public void OnSearchPopulateTable(){
			try{
	DBCollection dbcollection=db.getCollection("business_db");
	if (((comboBox_pointOfInterest.getSelectedItem().toString())=="NONE") || (comboBox_pointOfInterest.getSelectedIndex())=='1'){
		businessQuery.remove("full_address");
		businessQuery.remove("Loc");
		if(!Selected_listCategories.isEmpty())
			businessQuery.put("categories", new BasicDBObject("$in", Selected_listCategories));
    	if((comboBox_Searchfor.getSelectedItem().toString() == "OR")||((comboBox_Searchfor.getSelectedItem().toString() == "NONE"))){
    		if(!Selected_listAttributes.isEmpty())
    			businessQuery.append("subcategories", new BasicDBObject("$in", Selected_listAttributes));
    	} else if ((comboBox_Searchfor.getSelectedItem().toString() == "AND") ){		
    businessQuery.append("attr_keys", new BasicDBObject("$in", listattributes));	
	}
	}
	if(!textField.getText().isEmpty() || textField.getText() == "")
	{
		businessQuery.append("stars", textField.getText());
	}
	else
	{
		textField.removeAll();
		businessQuery.remove("stars");
	}
	if((comboBox_pointOfInterest.getSelectedItem()) != null && (comboBox_pointOfInterest.getSelectedItem()) != "NONE"){
			//&& (comboBox_pointOfInterest.getSelectedItem())!="NONE"){
    String pointOfInterested_Value=comboBox_pointOfInterest.getSelectedItem().toString();
    //businessQuery.append("full_address", pointOfInterested_Value);
    	
    	BasicDBObject full=new BasicDBObject();
    	full.append("full_address", pointOfInterested_Value);
    	BasicDBObject proj=new BasicDBObject();
    	proj.put("Loc", 1);
    	DBCursor cursor = dbcollection.find(full,proj);
    	DBObject res;
    	float latitude = 0;
    	float longitude = 0;
    	while(cursor.hasNext()){
    	res=cursor.next();
    		
    		DBObject report=(DBObject) res.get("Loc");
    		latitude=Float.parseFloat(report.get("latitude").toString().trim());
    		longitude=Float.parseFloat(report.get("longitude").toString().trim());
    		
    	}
    	BasicDBList geoCoord = new BasicDBList();
        geoCoord.add(longitude);
        geoCoord.add(latitude);

        BasicDBList geoParams = new BasicDBList();
        geoParams.add(geoCoord);
        geoParams.add(Float.parseFloat(comboBox_Proximity.getSelectedItem().toString())/3963.2);

        BasicDBObject query = new BasicDBObject("Loc", new BasicDBObject("$geoWithin", new BasicDBObject("$centerSphere", geoParams)));
        if((comboBox_Proximity.getSelectedItem()) != null ){
	        businessQuery.append("Loc", new BasicDBObject("$geoWithin", new BasicDBObject("$centerSphere", geoParams)));
	        DBCursor cursor_geo = dbcollection.find(query);   	
	        DBObject res_geo;
	        while(cursor_geo.hasNext()){
	        	res_geo= cursor_geo.next();
	        	
	   
	        }
        }
	}
	projection.clear();
	projection.put("name",1);
	projection.put("state",1);
	projection.put("city",1);
	projection.put("stars",1);
	
	textPane.setText(businessQuery+","+projection);
	textPane.repaint();
	DBCursor cursor_table=dbcollection.find(businessQuery,projection);
	DBObject result_table;
	
	/*Table 
	 * creating for BUSINESS*/
	String[] columnNames = new String[]{"Name", "City", "State","Stars"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	businessTable.setModel(model);
	businessTable.setShowGrid(true);
    	   
   while(cursor_table.hasNext()){
	   //System.out.println("reached");
	   result_table=cursor_table.next();
	   String b_name=(String) result_table.get("name");
	   String b_city=(String) result_table.get("city");
	   String b_state=(String) result_table.get("state");
	   String b_stars=(String) result_table.get("stars");
	   String b_id=(String) result_table.get("b_id");
	   listofBusinessId.add(b_id);
	   model.addRow(new Object[]{b_name, b_city, b_state,b_stars});
	    }
   businessTable.setModel(model);
   businessTable.setShowGrid(true);
   cursor_table.close();
  
	}
		catch (Exception e) 	{ e.printStackTrace();}
}
	public class AddressClass{
		public String Address;
		public double Lonlongitude;
		public double Latitude;
		public AddressClass(String Address, double d, double e){
			this.Address = Address;
			this.Lonlongitude = d;
			this.Latitude = e;
		}
	}
}
