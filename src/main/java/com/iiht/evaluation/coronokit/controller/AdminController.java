package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.CPKException;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet({"/admin","/list","/login","/newproduct","/insertproduct","/deleteproduct","/editproduct","/updateProduct","/logout","/edit","/delete","/goback"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
	
		
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateProduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "delete":
				viewName = delete(request, response);
				break;
			case "edit":
				viewName = edit(request, response);
				break;
			case "goback":
				viewName = goBack(request, response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		try {
			List<ProductMaster> prods = productMasterDao.getAllProducts();
			request.setAttribute("products", prods);
			view="listproducts.jsp";
		} catch (CPKException | SQLException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		return view;
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		ProductMaster prod = new ProductMaster();
		System.out.println(request.getParameter("id"));
		prod.setId(Integer.parseInt(request.getParameter("id")));
		prod.setProductName(request.getParameter("pname"));
		prod.setProductDescription(request.getParameter("pdesc"));
		
		prod.setCost(Double.parseDouble(request.getParameter("pcost")));
	
		
		String view="";
		
		try {
			productMasterDao.update(prod);
			request.setAttribute("msg", "Item updated!");
			view="welcomeAdmin.jsp";
		} catch (SQLException | CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		return view;
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws CPKException, SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		String view="";
						
		ProductMaster prod = productMasterDao.getProductByID(id);
		if(prod.getId()!=0)
			{request.setAttribute("prod", prod);
			view="newproduct.jsp";
		} 
		
		else{
			request.setAttribute("errMsg", "No such Product Found");
			view="errPage.jsp";
		}
		return view;
		
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws CPKException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String view="";
		
		
		if(productMasterDao.deleteById(id))
			{request.setAttribute("msg", "Product Got Deleted!");
			view="welcomeAdmin.jsp";
			}
			else
			{
				request.setAttribute("errMsg", "No such product found!");
				view="errPage.jsp";
			}
		return view;
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		ProductMaster prod = new ProductMaster();
		prod.setProductName(request.getParameter("pname"));
		prod.setProductDescription(request.getParameter("pdesc"));
		prod.setCost(Double.parseDouble(request.getParameter("pcost")));
		
		System.out.println(prod.getProductName());
		System.out.println(prod.getProductDescription());
		System.out.println(prod.getCost());
		
		String view="";
		
		try {
			productMasterDao.add(prod);
			request.setAttribute("msg", "Item added!");
			view="welcomeAdmin.jsp";
		} catch (SQLException | CPKException e) {
			request.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		
		ProductMaster prod = new ProductMaster();
		request.setAttribute("prod", prod);
		request.setAttribute("perform", "Add");
		return "newproduct.jsp";
		
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		
		return "delete.jsp";
	
	}	
	
	private String goBack(HttpServletRequest request, HttpServletResponse response) throws CPKException, SQLException {
		
		return "welcomeAdmin.jsp";
	
	}	
	
	private String edit(HttpServletRequest request, HttpServletResponse response) throws CPKException, SQLException {
		
		return "editproductID.jsp";
	
	}		
	
	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		return "welcomeAdmin.jsp";
	
	}

	
}