 package hw3;

import org.json.*;


import java.io.*;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.Driver;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Paths;




public class populatedb{
	private String directory = "C:/Study/DB/";
	private static String dbname = "kart";
	private static String dbpass = "peace";
	private static Connection dbConnection;
	
	
	 public static void main(String[] args) throws Exception, FileNotFoundException {
		
		 	dbConnection = SQLConnection();
		 	populatedb populatedbObject = new populatedb();
		 	populatedbObject.deletereviews();
		 	populatedbObject.deleteCheck_in();
		 	populatedbObject.deletesubcategories();
		 	populatedbObject.deleteCategory();		 	
		 	populatedbObject.deletebusiness_cat_sub();
		 	populatedbObject.deleteUsers();
		 	populatedbObject.deleteBusiness();
		 	populatedbObject.insertUsers();
		 	populatedbObject.insertBusiness();
		 	populatedbObject.insertChekin();
		 	populatedbObject.insertReview();
		 
	 }
	 
	 
	 
	 public static Connection SQLConnection(){
		 Connection connection = null;
		 try {
			 DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			connection = DriverManager.getConnection ("jdbc:oracle:thin:localhost:1521:orcl", dbname, dbpass);
			 System.out.println("Connection Succesful");
			 } catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;		 
	 }
	
	 public void deleteUsers(){
		 try {
			 PreparedStatement clearprevious = null;
				String cleartable = "DELETE from USERS";
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("USERS table cleared");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	 }
	 public void deleteCategory()
	 {
		 PreparedStatement clearprevious = null;
		String cleartable;
		 cleartable = "DELETE from CATEGORY";
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("CATEGORY table cleared");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	 }
	 public void deleteBusiness()
	 {
		 PreparedStatement clearprevious = null;
			String cleartable;
		 cleartable = "DELETE from BUSINESS";
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("BUSINESS table cleared");
				} catch (SQLException e) {
				e.printStackTrace();
			}		 
	 }
	 
	 public void deletesubcategories()
	 {
		 PreparedStatement clearprevious = null;
			String cleartable;
		 cleartable = "DELETE from sub_category";
			
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("SUBCATEGORIES table cleared");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 public void deletebusiness_cat_sub()
	 {
		 PreparedStatement clearprevious = null;
			String cleartable;
		 cleartable = "DELETE from BUSINESS_CAT_SUB";
			
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("BUSINESS_CAT_SUB table cleared");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
	 public void deleteCheck_in()
	 {	PreparedStatement clearprevious = null;
		 String cleartable = "DELETE from CHECK_IN";
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("CHECK_IN table cleared");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	 }
	 public void deletereviews()
	 {	PreparedStatement clearprevious = null;
		 String cleartable = "DELETE from REVIEW";
			try {
				clearprevious = dbConnection.prepareStatement(cleartable);
				clearprevious.executeQuery();
				System.out.println("REVIEW table cleared");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	 }
	 public void insertUsers() {
			List<String> userList = new ArrayList<>();
			PreparedStatement userStatement = null;					
			try (Stream<String> stream = Files.lines(Paths.get(directory+"yelp_user.json"))) {
				 
				dbConnection.setAutoCommit(true);
				userList = stream.collect(Collectors.toList());
				for (String string : userList) {

					try {
						JSONObject jsonObject = new JSONObject(string);

							if (userStatement == null) {
								try {
									String sql = "INSERT INTO users(YELPING_SINCE, NAME, REVIEW_COUNT, USER_ID, AVERAGE_STARS, FRIENDS_COUNT)"
											+ " VALUES (?,?,?,?,?,?)";									
									userStatement = dbConnection.prepareStatement(sql);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
						java.util.Date parsed = format.parse(jsonObject.get("yelping_since").toString());
						userStatement.setDate(1, new Date(parsed.getTime()));
						userStatement.setString(2, jsonObject.get("name").toString());
						userStatement.setString(3, jsonObject.get("review_count").toString());
						userStatement.setString(4, jsonObject.get("user_id").toString());
						userStatement.setString(5, jsonObject.get("average_stars").toString());
						JSONArray friends = (JSONArray) jsonObject.get("friends");
						userStatement.setInt(6, friends.length());
						try {
							userStatement.executeUpdate();
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (userStatement != null) {
					try {
						userStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					userStatement = null;
				}

			}
		}

public void insertBusiness() {
	//business, subcategories, categories, business_cat_sub tables are populatedbd
	PreparedStatement businessStatement = null;
	
	String[] mainCategories = {"ActiveLife", "Arts '&'Entertainment", "Automotive", "CarRental", "Cafes", "Beauty &Spas", "ConvenienceStores", "Dentists", "Doctors", "Drugstores", "DepartmentStores", "Education", "Event Planning &Services","Flowers &Gifts","Food","Health &Medical", "HomeServices", "Home &Garden", "Hospitals", "Hotels &Travel", "HardwareStores", "Grocery", "MedicalCenters", "Nurseries &Gardening", "Nightlife2", "Restaurants", "Shopping", "Transportation"};
	/*try {
		dbConnection.setAutoCommit(true);
		cleartable = "DELETE from CATEGORY";
		clearprevious = dbConnection.prepareStatement(cleartable);
		clearprevious.executeQuery();
		String categories = "INSERT into CATEGORY(CAT_ID, NAME) VALUES (?, ?)";
		
		for(int i = 0; i < mainCategories.length; i++)
		{
			mainCategoryStatement = dbConnection.prepareStatement(categories);
			mainCategoryStatement.setString(1, "C"+ String.valueOf(i));
			mainCategoryStatement.setString(2, mainCategories[i]);
			mainCategoryStatement.executeUpdate();
		}		
		//dbConnection.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	}*/
		List<String> list = new ArrayList<>();
		PreparedStatement bus_cate_insert = null;
		try (Stream<String> stream = Files.lines(Paths.get(directory+"yelp_business.json"))) {
			list = stream.collect(Collectors.toList());
			
			dbConnection.setAutoCommit(true);
		
		for (String string : list) {

				try {
					JSONObject jsonObject = new JSONObject(string);
					try {
					if (businessStatement == null) {
						
							String sql = "INSERT INTO BUSINESS(BUSINESS_ID, CITY, REVIEW_COUNT, STARS, STATE, NAME)"
									+ " VALUES (?,?,?,?,?,?)";
							businessStatement = dbConnection.prepareStatement(sql);
					}
							businessStatement.setString(1, jsonObject.get("business_id").toString());
							businessStatement.setString(2, jsonObject.get("city").toString());
							businessStatement.setInt(3, Integer.parseInt(jsonObject.get("review_count").toString()));
							businessStatement.setString(4, jsonObject.get("stars").toString());
							businessStatement.setString(5,  jsonObject.get("state").toString());
							businessStatement.setString(6,jsonObject.get("name").toString());
							try {
								businessStatement.executeUpdate();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					
					List<String> subCategory = new ArrayList<>();
					List<String> mainCat = new ArrayList<>();
					JSONArray category = (JSONArray) jsonObject.get("categories");
					for (Object object : category) {
						if (Arrays.asList(mainCategories).contains(object.toString())) {
							mainCat.add(object.toString());
						}
						else
						{
							subCategory.add(object.toString());
						}
					}
					
					
								
					try {
						if (bus_cate_insert == null) {
							
								String sql = "INSERT INTO BUSINESS_CAT_SUB(business_id,category_name,SUB_CATEGORY_name)"
										+ " VALUES (?,?,?)";
								bus_cate_insert = dbConnection.prepareStatement(sql);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
							for (String cat : mainCat) {
								for (String sub_cat : subCategory) {
									bus_cate_insert.setString(1, jsonObject.get("business_id").toString());
									bus_cate_insert.setString(2, cat);
									bus_cate_insert.setString(3, sub_cat);
									try {
										bus_cate_insert.executeUpdate();
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
							
						}
							
					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		System.out.println("All " + new Integer(list.size()).toString() + " business tables inserted");
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		}
			try{
			
		if (businessStatement != null) {
				businessStatement.close();
				businessStatement = null;
			}
			if (bus_cate_insert != null) {
				bus_cate_insert.close();
				bus_cate_insert = null;
			}
		} catch (Exception e) {
		e.printStackTrace();
	}
}


public void insertChekin() {
	//populatedbs check_in table
	List<String> checkinList = new ArrayList<>();
	PreparedStatement checkinStatement = null;
	int count = 0;
	try (Stream<String> stream = Files.lines(Paths.get(directory+"yelp_checkin.json"))) {
		
		
		dbConnection.setAutoCommit(true);
		
		checkinList = stream.collect(Collectors.toList());
		System.out.println("File extracted to list with" + new Integer(checkinList.size()).toString() + "entries");
		for (String string : checkinList) {

			try {
				JSONObject jsonObject = new JSONObject(string);

				if (checkinStatement == null) {
					try {
						String sql = "INSERT INTO CHECK_IN (dayandtime, IN_COUNT, BUSINESS_ID)"
								+ " VALUES (?,?,?)";
						checkinStatement = dbConnection.prepareStatement(sql);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				JSONObject checkinTime = (JSONObject) jsonObject.get("checkin_info");
				Set<String> keys = checkinTime.keySet();
				for (String temp : keys) {
					checkinStatement.setString(2, checkinTime.get(temp).toString());
					String[] hourday = temp.split("-");
					Float fHrsDay = Integer.parseInt(hourday[1]) + Integer.parseInt(hourday[0]) / 24F;
					checkinStatement.setFloat(1, fHrsDay);
					checkinStatement.setString(3, jsonObject.get("business_id").toString());
					checkinStatement.addBatch();
					count++;
					if(count > 200)
					try {
						checkinStatement.executeBatch();
						count = 0;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (checkinStatement != null) {
			try {
				checkinStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			checkinStatement = null;
		}

	}
}
public void insertReview() {
	//populates reviews table
	List<String> reviewList = new ArrayList<>();
	PreparedStatement reviewStatement = null;
	try (Stream<String> stream = Files.lines(Paths.get(directory+"yelp_review.json"))) {
		
		dbConnection.setAutoCommit(true);
		reviewList = stream.collect(Collectors.toList());
		System.out.println("File extracted to list with" + new Integer(reviewList.size()).toString() + "entries");
		
		for (String string : reviewList) {

			try {
				JSONObject jsonObject = new JSONObject(string);

				if (reviewStatement == null) {
					try {
						String sql = "INSERT INTO REVIEW(REVIEW_ID, DATE_OF_REVIEW, STARS, USER_ID, BUSINESS_ID, VOTES, TEXT) "
								+ " VALUES (?,?,?,?,?,?,?)";
						reviewStatement = dbConnection.prepareStatement(sql);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsed = format.parse(jsonObject.get("date").toString());
				reviewStatement.setDate(2, new Date(parsed.getTime()));
				reviewStatement.setString(1, jsonObject.get("review_id").toString());
				reviewStatement.setString(3, jsonObject.get("stars").toString());
				reviewStatement.setString(4, jsonObject.get("user_id").toString());
				reviewStatement.setString(5, jsonObject.get("business_id").toString());
				reviewStatement.setString(7, jsonObject.get("text").toString());
				JSONObject votes = (JSONObject) jsonObject.get("votes");

				int votesCount = 0;
				Set<String> keys = votes.keySet();
				for (String temp : keys) {
					votesCount = votesCount + Integer.parseInt((votes.get(temp).toString()));
				}
				reviewStatement.setInt(6, votesCount);
				
				try {
					reviewStatement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Reviews table filled");

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (reviewStatement != null) {
			try {
				reviewStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			reviewStatement = null;
		}

	}
}

}
