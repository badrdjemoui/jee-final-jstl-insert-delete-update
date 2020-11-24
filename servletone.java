package com.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DaoException;
import com.beans.Utilisateur;
import com.dao.*;


@WebServlet("/servletone")
public class servletone extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private UtilisateurDao utilisateurDao;

	    public void init() throws ServletException {
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.utilisateurDao = daoFactory.getUtilisateurDao();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            request.setAttribute("utilisateurs", utilisateurDao.lister());
	        }
	        catch (DaoException e) {
	            request.setAttribute("erreur", e.getMessage());
	        }
	        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	    }

	    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        try {
	            Utilisateur utilisateur = new Utilisateur();
	          
	            utilisateur.setNom(request.getParameter("nom_i"));
	            utilisateur.setPrenom(request.getParameter("prenom_i"));
	            
	            utilisateurDao.ajouter(utilisateur);
	            request.setAttribute("utilisateurs", utilisateurDao.lister());
	            
	           
	        }
	        catch (Exception e) {
	            request.setAttribute("erreur", e.getMessage());
	        }
	        
	        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
	    }
	    
	    

	}