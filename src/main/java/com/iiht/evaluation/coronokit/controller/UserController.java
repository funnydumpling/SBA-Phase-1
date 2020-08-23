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
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.CPKException;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.OrderSummary;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet({"/user"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private CoronaKit ck;
	private OrderSummary ordersummary;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}
	
	public void setCoronaKit(CoronaKit ck) {
		this.ck = ck;
	}	

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.ck = new CoronaKit();
		this.ordersummary = new OrderSummary();
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public HttpSession session;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		String action = request.getParameter("action");
		 
			
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "add":
				viewName = add(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "delete":
				viewName = delete(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = showOrderSummary(request, response);
				break;	
				
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
		
			RequestDispatcher dispatch = request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}



	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		String address = request.getParameter("building")+","+request.getParameter("street")+","+request.getParameter("city")+","+request.getParameter("country")+"-"+request.getParameter("zipcode");
		ck.setDeliveryAddress(address);
		
		try {
			List<KitDetail> kit = kitDAO.getKit(ck.getId());
			ordersummary=new OrderSummary(ck,kit);
			session.setAttribute("order", ordersummary);
			view = "ordersummary.jsp";
			} catch (CPKException | SQLException e) {
			session.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		
		return view;
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		try {
			List<KitDetail> kit = kitDAO.getKit(ck.getId());
			session.setAttribute("kit", kit);
			view="showkit.jsp";
		} catch (CPKException | SQLException e) {
			session.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) throws CPKException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String view="";
		
		System.out.println(id);
		
		if(kitDAO.delete(id))
			{
			session.setAttribute("msg", "Product Got Removed from the Kit!");
			view="showProductstoUser.jsp";
			}
			else
			{
				session.setAttribute("errMsg", "No such product found!");
				view="errPage.jsp";
			}
		return view;
	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		
		return "deleteFromKit.jsp";
	}

	
	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		int id = Integer.parseInt(request.getParameter("id"));
		int qty = Integer.parseInt(request.getParameter("quantity"));
		
		KitDetail kit = new KitDetail();
		kit.setCoronaKitId(ck.getId());
		kit.setProductId(id);
		kit.setQuantity(qty);
		
		
		try {
			kitDAO.add(kit);
			session.setAttribute("msg", "Product added to your Kit!");
			view="showProductstoUser.jsp";
		} catch (CPKException | SQLException e) {
			session.setAttribute("errMsg", e.getMessage());
			view="errPage.jsp";
		}
		
		
		return view;
		
	}

	private String add(HttpServletRequest request, HttpServletResponse response) {
		
		return "add.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException 
	{
		session=request.getSession();
		String view = "";
		ck.setId(Integer.parseInt((request.getParameter("id"))));
		ck.setPersonName(request.getParameter("name"));
		ck.setEmail(request.getParameter("email"));
		ck.setContactNumber(request.getParameter("contact"));
		
		try {
			List<ProductMaster> prods = productMasterDao.getAllProducts();
			session.setAttribute("prods", prods);
			view = "showProductstoUser.jsp";
			
		} catch (CPKException e) {
			// TODO Auto-generated catch block
			session.setAttribute("errMsg", e.getMessage());
			view = "errorPage.jsp";
		} 
		
		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		return "newUser.jsp";
	}
}