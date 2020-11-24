package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Utilisateur;
import com.dao.DaoException;
import com.dao.DaoFactory;
import com.dao.UtilisateurDao;

/**
 * Servlet implementation class servletupdate
 */
@WebServlet("/servletupdate")
public class servletupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private UtilisateurDao utilisateurDao;

	   public void init() throws ServletException {
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.utilisateurDao = daoFactory.getUtilisateurDao();
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
          request.setAttribute("utilisateurs", utilisateurDao.lister());
      }
      catch (DaoException e) {
          request.setAttribute("erreur", e.getMessage());
      }
      this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
  }

  public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
      try {
    	  
      	int id = Integer.parseInt(request.getParameter("id"));
      	
      	Utilisateur utilisateur = new Utilisateur();
     
      	utilisateur = utilisateurDao.searchByid(id);
      	
      	 utilisateur.setNom(request.getParameter("nom_i"));
      	 
         utilisateur.setPrenom(request.getParameter("prenom_i"));
         
      	utilisateurDao.update(utilisateur);
        
          
          
          request.setAttribute("utilisateurs", utilisateurDao.lister());
      }
      catch (Exception e) {
          request.setAttribute("erreur", e.getMessage());
      }
      
      this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
  }
  
  

}

