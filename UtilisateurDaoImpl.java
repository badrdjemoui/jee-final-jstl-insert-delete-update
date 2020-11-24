package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.beans.BeanException;
import com.dao.DaoException;
import com.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
    private DaoFactory daoFactory;

    UtilisateurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());

            preparedStatement.executeUpdate();
            connexion.commit();
        } catch (SQLException e) {
            try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }

    }

    @Override
    public List<Utilisateur> lister() throws DaoException {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,nom, prenom FROM noms;");

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");

                Utilisateur utilisateur = new Utilisateur();
                
                utilisateur.setid(id);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);

                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (BeanException e) {
            throw new DaoException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
        return utilisateurs;
    }

	@Override
	public boolean supprimer(int id) throws DaoException {
		
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            
	            String sql = "delete from noms where id="+id+";";
	        
	            preparedStatement = connexion.prepareStatement(sql);
	            preparedStatement.executeUpdate();
	          
	            connexion.commit();
           connexion.close();
            	
              return true;
	           
	        } 
	        catch (SQLException e) {
	            throw new DaoException("Impossible de communiquer avec la base de données");
	        } 
	        finally {
	            try {
	                if (connexion != null) {
	                    connexion.close();  return false;
	                }
	            } catch (SQLException e) {
	                throw new DaoException("Impossible de communiquer avec la base de données");
	            }
	        }
	    
		
	}
	public boolean update(Utilisateur userupdated) throws DaoException {
		
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        
        try {
            connexion = daoFactory.getConnection();
          
            String sql = "UPDATE noms SET nom = ? ,prenom = ?";
                   sql += " WHERE id = ?";
                   System.out.println(" sql = "+sql);
                   preparedStatement = connexion.prepareStatement(sql);
                   preparedStatement.setString(1, userupdated.getNom());
                   preparedStatement.setString(2,userupdated.getPrenom());
                   preparedStatement.setInt(3,userupdated.getid());
                   
                    
            
            preparedStatement.executeUpdate();
          
        connexion.commit();
       connexion.close();
        	
       return true; 
           
        } 
        catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        } 
        finally {
            try {
                if (connexion != null) {
                    connexion.close(); return false;
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
        }
    
	
}
	public Utilisateur searchByid(int id) throws DaoException {
	
    Connection connexion = null;
    Statement stmt = null;
   
    ResultSet result = null;
    Utilisateur utilisateur = new Utilisateur();

    try {
        connexion = daoFactory.getConnection();
        
        
        String sql = "SELECT * FROM noms "+"WHERE id ="+id+";";
        
       
       
        stmt = connexion.createStatement();
        
        result = stmt.executeQuery(sql);
        if (result.next()) { 
        
        	
        	String nom = result.getString("nom");
        String prenom = result.getString("prenom");
        
       
        utilisateur.setid(id);
		utilisateur.setNom(nom);
		
        utilisateur.setPrenom(prenom);

        }
      
        
      
   connexion.commit();
   connexion.close();
    	
    
       
    } 
    catch (SQLException e) {
        throw new DaoException("Impossible de communiquer avec la base de données");
    } catch (BeanException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    finally {
        try {
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
    }
  
    return utilisateur;

}

}