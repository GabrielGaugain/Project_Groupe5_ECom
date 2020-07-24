package com.intiformation.ECommerce.controller;


import com.intiformation.ECommerce.modele.Categorie;
import com.intiformation.ECommerce.modele.Photo;
import com.intiformation.ECommerce.dao.CategorieDAOImpl;
import com.intiformation.ECommerce.dao.ICategorieDAO;
import com.intiformation.ECommerce.dao.IPhotoDAO;
import com.intiformation.ECommerce.dao.PhotoDAOImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 * @author INTIFORMATION
 */
@ManagedBean (name = "categorieBean")
@SessionScoped
public class GestionCategorieBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*_____________________________ CHAMPS __________________________________*/
    private Collection<Categorie> categories;
    
    private Categorie categorie;

    // file upload de l'API servlet
    private Part uploadedFile;

    // dao 
    IPhotoDAO photoDAO;
    ICategorieDAO categorieDAO;

    /*_____________________________ CTORS __________________________________*/
    /**
     * ctor Creates a new instance of GestionCategorie
     */
    public GestionCategorieBean() {
    	categorieDAO = new CategorieDAOImpl();
    	photoDAO = new PhotoDAOImpl();
    }

    /*_____________________________ METHODES __________________________________*/
    /**
     * recup de la liste des categories Ã  partir de la BDD <br/>
     * @return
     */
    public Collection<Categorie> getCategories() {

        categories = categorieDAO.getAll() ;
        return categories;
    }

    /**
     * permet d'initialiser une cate appelÃ©e lors de l'ajout de cate
     *
     * @param event
     */
    public void initCat(ActionEvent event) {
        setCategorie(new Categorie());
    }

    /**
     * modif d'une cate
     *
     * @param event
     */
    public void selectCat(ActionEvent event) {

        UIParameter cCatUp = (UIParameter) event.getComponent().findComponent("editID");
        Long idUp = (Long) cCatUp.getValue();
        System.out.println("id dde la cat a mod : "+idUp);
        Categorie cat = categorieDAO.getById(idUp) ;

        setCategorie(cat);
       
    }

    /**
     * suppression d'une cate => Marche !!
     * @param event
     */
    public void deleteCat(ActionEvent event) {

        UIParameter cp = (UIParameter) event.getComponent().findComponent("deleteID");
        Long id = (long) cp.getValue();
       
        categorieDAO.delete(id);
        
        categories = getCategories();
        
    }//end deleteCat

    /**
     * sauvegarder une cate categorie: celui qui est declaré dans les champs du
     * formulaire de edit.jsp
     *
     * @param event
     */
    public void saveCat(ActionEvent event) {

        //-------------------------------------------
        // cas : ajout 
        //-------------------------------------------
        if (categorie.getIdCategorie() == 0) {

            try {
                // traitement du fileUpload : recup du nom de l'image
                String fileName = uploadedFile.getSubmittedFileName();
                
                System.out.println("... chemin de la photo" +fileName);
               
                
                // affectation du nom à  la prop urlImage de la cate
                categorie.setUrlImageCategorie(fileName);
                
                ///System.out.println("... nom de la photo" +nomPhoto);
                
                Photo photoToAdd = new Photo(fileName, fileName);
 
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
                
                
                // ajout de l'image dans la bdd
                photoDAO.add(photoToAdd);
                
                // ajout de la cate dans la bdd
                categorieDAO.add(categorie);

				
            } catch (IOException ex) {
                Logger.getLogger(GestionCategorieBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //-------------------------------------------
        // cas : modif 
        //-------------------------------------------
        if (categorie.getIdCategorie() != 0) {

            if (uploadedFile != null) {

                String fileNameToUpdate = uploadedFile.getSubmittedFileName();

                if (!"".equals(fileNameToUpdate) && fileNameToUpdate != null) {

                    // affectation du nouveau nom Ã  la prop urlImage de la cate 
                    categorie.setUrlImageCategorie(fileNameToUpdate);
                }
            }

            categorieDAO.update(categorie);
        }

    }//end saveCat()

    /*_______________________ GETTERS/SETTERS ________________________________*/
    /**
     * @return the categorie
     */
    public Categorie getCategorie(){
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }

    /**
     * @return the uploadedFile
     */
    public Part getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

}//end class