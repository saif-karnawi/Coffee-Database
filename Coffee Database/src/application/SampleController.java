package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SampleController implements Initializable{

    @FXML
    private TableView<Coffee> tableView;

    @FXML
    private TableColumn<Coffee, String> tc1;

    @FXML
    private TableColumn<Coffee, Double> tc2;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button BtnRemove;

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button btnRead;

    @FXML
    private TextField textboxCoffee;

    @FXML
    private TextField textboxPrice;

    @FXML
    void OnAdd(ActionEvent event) {
    	

    	String coffee = textboxCoffee.getText();
    	String price = textboxPrice.getText().toString();
    	
    	
    	Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			String db="/Users/saifkarnawi/Desktop/coffePractice.accdb";
			String myPath="jdbc:ucanaccess://"+db;
			
			
			con=DriverManager.getConnection(myPath);
			stmt=con.createStatement();

			
			//INSERT INTO Student(StudentName,Age, Percentage) VALUES('Jim',34,67.8)
			String query2="INSERT INTO coffeePractice(Coffee,  Price) VALUES('"+coffee+"',"+price+")";
			
			stmt.executeUpdate(query2);
						
				
		} 
		catch (ClassNotFoundException e) {
			System.out.print("Problem in loading Driver");
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
    	
    	data.add(coffee + " $" + price);
		data2.add(new Coffee(coffee,Double.parseDouble(price)));

    	listView.getItems().clear();
    	listView.getItems().addAll(data);
		tableView.setItems(data2); 

    }

    @FXML
    void OnRead(ActionEvent event) {
    	
    	tc1.setCellValueFactory(cellData -> cellData.getValue().coffeeNameProperty());
		tc2.setCellValueFactory(cellData -> cellData.getValue().coffeePriceProperty().asObject());
    	
    	Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");		
			String db="/Users/saifkarnawi/Desktop/coffePractice.accdb";
			String dbPath="jdbc:ucanaccess://"+db;
			con = DriverManager.getConnection(dbPath);
			stmt=con.createStatement();		
			String query = "SELECT * FROM coffeePractice";
			rs=stmt.executeQuery(query);
		
			data.clear();
			data2.clear();
			
			while(rs.next()) {
				
				String coffee=rs.getString(2);
				double price=rs.getDouble(3);
				data.add(coffee + " $" + price);
				data2.add(new Coffee(coffee,price));

				
			}
			
			listView.getItems().clear();
			listView.getItems().addAll(data);
			tableView.setItems(data2); 
			
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally
			{
		
				try {
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}

    }

    @FXML
    void OnRemove(ActionEvent event) {
    	
		tableView.getItems().clear();

    	
    	Connection con = null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			
	    	String selection = listView.getSelectionModel().getSelectedItem().toString();
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");		
			String db="/Users/saifkarnawi/Desktop/coffePractice.accdb";
			String dbPath="jdbc:ucanaccess://"+db;
			con = DriverManager.getConnection(dbPath);
			stmt=con.createStatement();		
			String query = "SELECT * FROM coffeePractice";
			rs=stmt.executeQuery(query);
			data.clear();
			data2.clear();
			
			while(rs.next()) {
				
				String coffee=rs.getString(2);
				double price=rs.getDouble(3);
				if(selection.contains(coffee))
				{
					String query1 = "DELETE FROM coffeePractice WHERE Coffee='"+coffee+"'";
					stmt.executeUpdate(query1);
					
				}
				
				else
				{
					data.add(coffee + " $" + price);
					data2.add(new Coffee(coffee,price));

				}
	
			}
			
			listView.getItems().clear();
			listView.getItems().addAll(data);
			tableView.setItems(data2); 
			
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally
			{
		
				try {
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}

    }

    @FXML
    void OnUpdate(ActionEvent event) {
		tableView.getItems().clear();

    	Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;

		try {
			

			String selection = listView.getSelectionModel().getSelectedItem().toString();
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");		
			String db="/Users/saifkarnawi/Desktop/coffePractice.accdb";
			String dbPath="jdbc:ucanaccess://"+db;
			con = DriverManager.getConnection(dbPath);
			stmt=con.createStatement();		
			String query = "SELECT * FROM coffeePractice";
			rs=stmt.executeQuery(query);
			data.clear();
			data2.clear();
			
			while(rs.next()) {
				
				String coffee=rs.getString(2);
				double price=rs.getDouble(3);
				String newCoffee = textboxCoffee.getText();
				double newPrice = Double.parseDouble(textboxPrice.getText()); 
				
				if(selection.contains(coffee))
				{	
					
					String query1="UPDATE coffeePractice SET Price="+newPrice+" WHERE Coffee='"+coffee+"'";
					String query2="UPDATE coffeePractice SET Coffee='"+newCoffee+"' WHERE Price="+newPrice+"";

					stmt.executeUpdate(query1);
					stmt.executeUpdate(query2);
					data.add(newCoffee + " $" + newPrice);
					data2.add(new Coffee(newCoffee,newPrice));


				}
				
				else
				{
					data.addAll(coffee + " $" + price);
					data2.add(new Coffee(coffee,price));
				}
					


			}
			
			listView.getItems().clear();
			listView.getItems().addAll(data);
			tableView.setItems(data2); 

		} 
		
		
		catch (ClassNotFoundException e) {
			System.out.print("Problem loading Driver");
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

    }
    
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<Coffee> data2 = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tc1.setCellValueFactory(cellData -> cellData.getValue().coffeeNameProperty());
		tc2.setCellValueFactory(cellData -> cellData.getValue().coffeePriceProperty().asObject());
		
		Connection con = null; 
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");		
			String db="/Users/saifkarnawi/Desktop/coffePractice.accdb";
			String dbPath="jdbc:ucanaccess://"+db;
			con = DriverManager.getConnection(dbPath);
			stmt=con.createStatement();		
			String query = "SELECT * FROM coffeePractice";
			rs=stmt.executeQuery(query);
		
		
			while(rs.next()) {
				
				String coffee=rs.getString(2);
				double price=rs.getDouble(3);
				data.add(coffee + " $" + price);
				data2.add(new Coffee(coffee,price));

				
			}
			
			listView.getItems().addAll(data);
			tableView.setItems(data2); 

			
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			catch (SQLException e) {
				e.printStackTrace();
			}
		
			finally
			{
		
				try {
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
		
		
		
	}

}
