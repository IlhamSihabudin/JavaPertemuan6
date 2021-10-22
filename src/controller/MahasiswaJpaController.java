/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import helper.PersistenceHelper;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Mahasiswa;
import viewer.MasterMahasiswa;

/**
 *
 * @author Ilham Sihabudin
 */
public class MahasiswaJpaController implements Serializable {
    private MasterMahasiswa view;
    
    public MahasiswaJpaController(MasterMahasiswa view) {
        this.view = view;
    }

    public void create()  {
        EntityManager manager = PersistenceHelper.getFactory().createEntityManager();
        manager.getTransaction().begin();
        try {
            Mahasiswa tbMahasiswa = new Mahasiswa();
            tbMahasiswa.setNrp(view.getTextNrp().getText());
            tbMahasiswa.setNama(view.getTextNama().getText());
            tbMahasiswa.setAngkatan(view.getTextAngkatan().getText());
            tbMahasiswa.setKodeFakultas(view.getComboKodeFakultas().getSelectedItem().toString().substring(0, 2));
            tbMahasiswa.setKodeDosen(view.getComboKodeDosen().getSelectedItem().toString().substring(0, 2));
            
            view.getTextNrp().setText("");
            view.getTextNama().setText("");
            view.getTextAngkatan().setText("");
            view.getComboKodeFakultas().setSelectedIndex(0);
            view.getComboKodeDosen().setSelectedIndex(0);
            
            manager.persist(tbMahasiswa);
            
            manager.getTransaction().commit();
            view.getTableModelMahasiswa().insert(tbMahasiswa);
            view.getTextMessage().setText("Simpan Berhasil");
        } catch (Exception e) {
            view.getTextMessage().setText("Simpan Gagal");
            e.printStackTrace();
	    manager.getTransaction().rollback();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }
    
    public void update() {
        int index = view.getTableMahasiswa().getSelectedRow();
        if (index == -1) {
            return;
        }
        
        EntityManager manager = PersistenceHelper.getFactory().createEntityManager();
	manager.getTransaction().begin();
        try {
            Mahasiswa tbMahasiswa = view.getTableModelMahasiswa().select(index);
            tbMahasiswa.setNrp(view.getTextNrp().getText());
            tbMahasiswa.setNama(view.getTextNama().getText());
            tbMahasiswa.setAngkatan(view.getTextAngkatan().getText());
            tbMahasiswa.setKodeFakultas(view.getComboKodeFakultas().getSelectedItem().toString().substring(0, 2));
            tbMahasiswa.setKodeDosen(view.getComboKodeDosen().getSelectedItem().toString().substring(0, 2));
            
            view.getTextNrp().setText("");
            view.getTextNama().setText("");
            view.getTextAngkatan().setText("");
            view.getComboKodeFakultas().setSelectedIndex(0);
            view.getComboKodeDosen().setSelectedIndex(0);
            
            manager.merge(tbMahasiswa);
            
            manager.getTransaction().commit();
            view.getTableModelMahasiswa().update(index, tbMahasiswa);
            view.getTextMessage().setText("Update Berhasil");
        } catch (Exception e) {
            view.getTextMessage().setText("Update Gagal");
            e.printStackTrace();
	    manager.getTransaction().rollback();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }
    
    public void delete() {
        int index = view.getTableMahasiswa().getSelectedRow();
        if (index == -1){
            return;
        }
        
        EntityManager manager = PersistenceHelper.getFactory().createEntityManager();
	manager.getTransaction().begin();
        try {
            Mahasiswa tbMahasiswa = view.getTableModelMahasiswa().select(index);
            
            manager.remove(manager.merge(tbMahasiswa));
            
            manager.getTransaction().commit();
            view.getTableModelMahasiswa().delete(index);
            view.getTextMessage().setText("Delete Berhasil");
        } catch (Exception e) {
            view.getTextMessage().setText("Delete Gagal");
            e.printStackTrace();
	    manager.getTransaction().rollback();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }
    
    public void select() {
	EntityManager manager = PersistenceHelper.getFactory().createEntityManager();
	try {
	    @SuppressWarnings("unchecked")
	    List list = manager.createQuery("select a from Mahasiswa a").getResultList();
	    view.getTableModelMahasiswa().updateAll(list);
	} finally {
	    manager.close();
	}
    }
}
