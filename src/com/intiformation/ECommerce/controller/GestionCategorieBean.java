package com.intiformation.ECommerce.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.intiformation.ECommerce.dao.CategorieDAOImpl;
import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.modele.Categorie;

@ManagedBean(name = "categorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable {

	/* _____________________________props_______________________________ */

	ICategorieDAO categorieDAO;
	private Collection<Categorie> listeCateBDD;
	private Categorie categorie;
	private Part uploadedFile;
	private String formulaire ;

	/* _____________________________ctors_______________________________ */
	public GestionCategorieBean() {
		categorieDAO = new CategorieDAOImpl();
	}// end ctor vide

	/* _____________________________meths_______________________________ */

	

	public Collection<Categorie> getListeCate() {
		return listeCateBDD = categorieDAO.getAll();
	}

	public void initCategorie(ActionEvent event) {
		setCategorie(new Categorie());
	}

	public void selectCategorie(ActionEvent event) {

		UIParameter cp = (UIParameter) event.getComponent().findComponent("modifID");
		int id = (int) cp.getValue();

		Categorie categorie = categorieDAO.getById(id);

		setCategorie(categorie);
	}

	public void deleteCategorie(ActionEvent event) {

		UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
		int id = (int) cp.getValue();

		categorieDAO.delete(id);
	}

	public void saveCategorie(ActionEvent event) {

	        //-------------------------------------------
	        // cas : ajout 
	        //-------------------------------------------
	        if (categorie.getIdCategorie()== 0) {

	            try {
	                // traitement du fileUpload : recup du nom de l'image
	                String fileName = uploadedFile.getSubmittedFileName();
	                
	                // affectation du nom à  la prop urlImage de la cate
	                categorie.setUrlImageCategorie(fileName);
	                
	                // ajout de la categorie dans la bdd
	                categorieDAO.add(categorie);

	                //----------------------------------------------
	                // ajout de la photo dans le dossier images
	                //----------------------------------------------
	                
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
	            } catch (IOException ex) {
	                Logger.getLogger(GestionCategorieBean.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        	}
	            if (categorie.getIdCategorie() != 0) {

	                if (uploadedFile != null) {

	                    String fileNameToUpdate = uploadedFile.getSubmittedFileName();

	                    if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

	                        // affectation du nouveau nom Ã  la prop urlImage cate
	                    	categorie.setUrlImageCategorie(fileNameToUpdate);
	                    }
	                }
	            }

	                categorieDAO.update(categorie);
	                
	                  
	        }// end saveCate()

	/* _______________________ GETTERS/SETTERS ________________________________ */

	public ICategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public void setCategorieDAO(ICategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFormulaire() {
		return formulaire;
	}

	public void setFormulaire(String formulaire) {
		this.formulaire = formulaire;
	}





}// end class
