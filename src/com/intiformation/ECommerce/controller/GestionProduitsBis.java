package com.intiformation.ECommerce.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import com.intiformation.ECommerce.dao.CategorieDAOImpl;
import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.dao.IPhotoDAO;
import com.intiformation.ECommerce.dao.IProduitDAO;
import com.intiformation.ECommerce.dao.PhotoDAOImpl;
import com.intiformation.ECommerce.dao.ProduitDAOImpl;
import com.intiformation.ECommerce.modele.Categorie;
import com.intiformation.ECommerce.modele.Produit;

@ManagedBean(name = "produitBeanBis")
@SessionScoped
public class GestionProduitsBis implements Serializable{
	/* _____________________________props_______________________________ */

	// service/DAO
	IProduitDAO prodDAO;
	IPhotoDAO photoDAO;
	// Listes
	private Collection<Categorie> listeCateBDD;
	private Collection<Produit> listeProdBDD;
	private Collection<Produit> listeProdCateBDD;
	private Collection<Produit> listeProdMCBDD;
	
	// var  
	private Produit produit;
	private Produit SelectedProd;
	private long idCategorie ;
	private String motCle ;
	private boolean coche;
	
    // file upload de l'API servlet
    private Part uploadedFile;	
	
    /* _____________________________ctors_______________________________ */
	public GestionProduitsBis() {
		prodDAO = new ProduitDAOImpl();
		listeProdMCBDD = prodDAO.getAll();
		motCle = "";
		photoDAO = new PhotoDAOImpl();
		
	}// end ctor vide

	
	/* _____________________________meths_______________________________ */

	public Collection<Categorie> getListeCate() {
		ICategorieDAO catDAO = new CategorieDAOImpl();
		return listeCateBDD = catDAO.getAll();
	}
	
	public Collection<Produit> getListeProd() {
		return listeProdBDD = prodDAO.getAll();
	}
	
	public Collection<Produit> getListeProdCate() {
		return listeProdCateBDD = prodDAO.getAllProduitByCategorie(idCategorie);
	}
	
	public Collection<Produit> getListeProdMC() {
		 return listeProdMCBDD = prodDAO.getByMotCle(motCle) ;
	}
	
	public void addMessage() {
        String summary = coche ? "Produit ajouté à la sélection" : "Produit retiré de la sélection";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
	
	
	/**
	 * Recup de la liste des prod par categorie 
	 */
	public void getListeProdMCCate(ActionEvent event) {
		// 1. recup context
		FacesContext contextJSF = FacesContext.getCurrentInstance();		
		// 2.recup param id du produit
		
		UIParameter uCat = (UIParameter) event.getComponent().findComponent("idCat");
		long idCategorie = (long) uCat.getValue();
		
		listeProdMCBDD = prodDAO.getAllProduitByCategorie(idCategorie);
	} 	
	
	
	/**
	 * Select le pdt pour la modif
	 * @param event
	 */
    public void selectProd(ActionEvent event) {

        UIParameter pId = (UIParameter) event.getComponent().findComponent("editIDpdt");
        Long idPdt = (long) pId.getValue();

        Produit pdt = prodDAO.getById(idPdt) ;

        setProduit(pdt);
       
    }
    
    /**
     * suppression d'un produit
     * @param event
     */
    public void deleteProd(ActionEvent event) {
    	System.out.println("Dans deleteProd ....");
        UIParameter pId = (UIParameter) event.getComponent().findComponent("deleteIDpdt");
        Long idPdtToDel = (long) pId.getValue();

        System.out.println("Id prod to del : "+idPdtToDel);
        
        prodDAO.delete(idPdtToDel);
        
        listeProdBDD = getListeProd();
        
    }    //end deleteProd
    
    
    
    public void initProd(ActionEvent event) {
        setProduit( new Produit() );
    }//end    initProd 

    
    public void saveProd(ActionEvent event) {
    	System.out.println("Dans  saveProd .... id prod : "+produit.getIdProduit());
        //-------------------------------------------
        // cas : ajout 
        //-------------------------------------------
        if (produit.getIdProduit() == 0) {

            try {
                // traitement du fileUpload : recup du nom de l'image
                String fileName = uploadedFile.getSubmittedFileName();
                
                System.out.println("... chemin de la photo" +fileName);
               
                
                // affectation du nom à  la prop urlImage de la cate
                produit.setUrlImageProduit(fileName);
                
                File f = new File(fileName);
      
                System.out.println("path : "+f.getPath());
                System.out.println("name : "+f.getName());
                
                ///System.out.println("... nom de la photo" +nomPhoto);
                
                //Photo photoToAdd = new Photo(fileName, nomPhoto);
 
                //----------------------------------------------
                // ajout de la photo dans le dossier images du projet
                //-----------------------------------------------
             
                // recup du contenu de l'image
                InputStream imageContent = uploadedFile.getInputStream();

                // recup de la valeur du param d'initialisation context-param de web.xml
                FacesContext fContext = FacesContext.getCurrentInstance();
                String pathTmp = fContext.getExternalContext().getInitParameter("file-upload");
                
                String filePath = fContext.getExternalContext().getRealPath(pathTmp);

                // création du fichier image (conteneur de l'image) 
                File targetFile = new File(filePath, fileName);

                // instanciation du flux de sortie vers le fichier image
                OutputStream outStream = new FileOutputStream(targetFile);
                byte[] buf = new byte[1024];
                int len;

                while ((len = imageContent.read(buf)) > 0) {
                    outStream.write(buf, 0, len);
                }
                
                outStream.close();                
                
                /*
                // ajout de l'image dans la bdd
                photoDAO.add(photoToAdd);
                
                // ajout de la cate dans la bdd
                prodDAO.add(produit);

				*/
            } catch (IOException ex) {
                Logger.getLogger(GestionCategorieBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //-------------------------------------------
        // cas : modif 
        //-------------------------------------------
        if (produit.getIdProduit() != 0) {
        	System.out.println("Dans modifier prod admin ...");
            if (uploadedFile != null) {

                String fileNameToUpdate = uploadedFile.getSubmittedFileName();

                if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

                    // affectation du nouveau nom Ã  la prop urlImage de la cate 
                    produit.setUrlImageProduit(fileNameToUpdate);
                }
            }

            prodDAO.update(produit);
        }

    }//end saveCat()    
    
	/* _______________________ GETTERS/SETTERS ________________________________ */

	public Collection<Produit> getListeProdBDD() {
		return listeProdBDD;
	}

	public void setListeProdBDD(Collection<Produit> listeProdBDD) {
		this.listeProdBDD = listeProdBDD;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Collection<Produit> getListeProdCateBDD() {
		return listeProdCateBDD;
	}
	
	public void setListeProdCateBDD(Collection<Produit> listeProdCateBDD) {
		this.listeProdCateBDD = listeProdCateBDD;
	}

	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}



	public Collection<Produit> getListeProdMCBDD() {
		return listeProdMCBDD;
	}


	public void setListeProdMCBDD(Collection<Produit> listeProdMCBDD) {
		this.listeProdMCBDD = listeProdMCBDD;
	}


	public String getMotCle() {
		return motCle;
	}


	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}


	public Produit getSelectedProd() {
		return SelectedProd;
	}


	public void setSelectedProd(Produit selectedProd) {
		SelectedProd = selectedProd;
	}


	public boolean isCoche() {
		return coche;
	}


	public void setCoche(boolean coche) {
		this.coche = coche;
	}


	public Part getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	

}// end class
