package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	
	ProductMasterDao prod;
	
	public KitDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
        prod= new ProductMasterDao(jdbcURL,jdbcUsername,jdbcPassword);

	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			
			
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public static final String ADD_KIT = "INSERT INTO kitDetail(coronakitId,productId,productName,quantity,amount) VALUES(?,?,?,?,?);";
	public static final String DELETE_KIT = "Delete from kitDetail where productId=?;";
	public static final String LIST_KIT = "Select id,coronakitId,productId,productName,quantity,amount from kitDetail where coronakitId=?";
	
	public KitDetail add(KitDetail kit) throws CPKException, SQLException {
		if(kit!=null)
		{
			connect();
			try(
				
					PreparedStatement pst = jdbcConnection.prepareStatement(ADD_KIT)
					
					){
				pst.setInt(1,kit.getCoronaKitId());
				pst.setInt(2, kit.getProductId());
				pst.setInt(4, kit.getQuantity());
				
				double amount = prod.getCostbyId(kit.getProductId()) * kit.getQuantity();
				String name = prod.getProductName(kit.getProductId());
				pst.setDouble(5,amount);
				pst.setString(3, name);
				pst.executeUpdate();
				
				System.out.println("Amount:"+amount);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CPKException("Adding item to the kit failed");
			}
		}

		disconnect();
		return kit;
	}
	
	
	
	public boolean delete(int productId) throws CPKException, SQLException {
		boolean isDeleted = false;
		connect();
			try(
				
					PreparedStatement pst = jdbcConnection.prepareStatement(DELETE_KIT)
					
					){
				pst.setInt(1,productId);
				
				int rowCount = pst.executeUpdate();
				
				isDeleted = rowCount>0;
				
			} catch (SQLException e) {
				e.printStackTrace();
				
				throw new CPKException("Deleting item in the kit failed");
			}

			disconnect();
			return isDeleted;
		}
		
	

	
	public List<KitDetail> getKit(int id) throws CPKException, SQLException{
		List<KitDetail> entirekit = new ArrayList<>();
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(LIST_KIT)
				
				){
			pst.setInt(1, id);	
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				KitDetail kit = new KitDetail();
				kit = new KitDetail();
				kit.setId(rs.getInt(1));
				kit.setCoronaKitId(rs.getInt(2));
				kit.setProductId(rs.getInt(3));
				kit.setProductName(rs.getString(4));
				kit.setQuantity(rs.getInt(5));
				kit.setAmount((rs.getDouble(6)));
				
				entirekit.add(kit);
			}
			
			if(entirekit.isEmpty())
				entirekit=null;
			
						
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Retrieving the kit failed");
		}

		disconnect();
		return entirekit;
	}

	
}