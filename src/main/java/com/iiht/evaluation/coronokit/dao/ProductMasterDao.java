package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
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

	public static final String ADD_PRODUCT = "INSERT INTO productMaster(pname,pdesc,pcost) VALUES(?,?,?);";
	public static final String UPDATE_PRODUCT = "Update productMaster set pname=?,pdesc=?,pcost=? where id=?";
	public static final String DELETE_PRODUCT = "Delete from productMaster where id=?;";
	public static final String LIST_BY_ID = "Select id,pname,pdesc,pcost from productMaster where id=?;";
	public static final String LIST_ALL_PRODUCTS = "Select id,pname,pdesc,pcost from productMaster;";
	public static final String LIST_COST = "Select pcost from productMaster where id=?;";
	public static final String LIST_PRODUCTNAME = "Select pname from productMaster where id=?;";
	
	
	public ProductMaster add(ProductMaster prod) throws CPKException, SQLException {
		
		if(prod!=null)
		{
			connect();
			try(
					
					PreparedStatement pst = jdbcConnection.prepareStatement(ADD_PRODUCT)
					
					){
				pst.setString(1, prod.getProductName());
				pst.setString(2, prod.getProductDescription());
				pst.setDouble(3, prod.getCost());
				
				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CPKException("Adding the product failed");
			}
		}
		
		return prod;
	}
	
	public ProductMaster update(ProductMaster prod) throws CPKException, SQLException {
		if(prod!=null)
		{
			connect();
			try(
				
					PreparedStatement pst = jdbcConnection.prepareStatement(UPDATE_PRODUCT)
					
					){
				pst.setString(1, prod.getProductName());
				pst.setString(2, prod.getProductDescription());
				pst.setDouble(3, prod.getCost());
				pst.setInt(4,prod.getId());

				pst.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CPKException("Updating the product failed");
			}
		}

		
		return prod;
	}
	
	
	public boolean deleteById(int id) throws CPKException, SQLException {
		boolean isDeleted = false;
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(DELETE_PRODUCT)
				
				){
			
			pst.setInt(1,id);
			
			int rowCount = pst.executeUpdate();
			
			isDeleted = rowCount>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Deleting the product failed");
		}

		
		return isDeleted;
	}
	
	
	public List<ProductMaster> getAllProducts() throws CPKException, SQLException{
		List<ProductMaster> products = new ArrayList<>();
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(LIST_ALL_PRODUCTS)
				
				){
				
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				ProductMaster prod = new ProductMaster();
				prod = new ProductMaster();
                prod.setId(rs.getInt(1));
                prod.setProductName(rs.getString(2));
                prod.setProductDescription(rs.getString(3));
                prod.setCost(rs.getDouble(4));
				
				products.add(prod);
			}
			
			if(products.isEmpty())
				products=null;
			
						
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Retrieving all items failed");
		}

		disconnect();
		return products;
	}

	public ProductMaster getProductByID(int id) throws CPKException, SQLException{
		ProductMaster prod = new ProductMaster();
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(LIST_BY_ID)
				
				){
				
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				
                prod.setId(rs.getInt(1));
                prod.setProductName(rs.getString(2));
                prod.setProductDescription(rs.getString(3));
                prod.setCost(rs.getDouble(4));
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Retrieving all items failed");
		}

		disconnect();
		return prod;
	}
	
	
	public double getCostbyId(int id) throws CPKException, SQLException{
		double pcost=0;
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(LIST_COST)
				
				){
			
			pst.setInt(1, id);
				
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				pcost = rs.getDouble(1);
			}
			
			System.out.println("COst of the item"+pcost);
						
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Retrieving cost of the selected item failed");
		}

		disconnect();
		return pcost;
	}


	public String getProductName(int id) throws CPKException, SQLException{
		String pname="";
		connect();
		try(
				
				PreparedStatement pst = jdbcConnection.prepareStatement(LIST_PRODUCTNAME)
				
				){
			
			pst.setInt(1, id);
				
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				pname = rs.getString(1);
			}
			
			System.out.println("Name of the item"+pname);
						
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new CPKException("Retrieving cost of the selected item failed");
		}

		disconnect();
		return pname;
	}

}