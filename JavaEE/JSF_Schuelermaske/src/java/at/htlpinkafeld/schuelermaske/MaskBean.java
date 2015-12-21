/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.schuelermaske;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Martin Six
 */
@ManagedBean
@ApplicationScoped
public class MaskBean {

    private String vn;
    private String nn;
    private String kl;
    private int knr;
    private int alter;

    List<Schueler> sList;

    public MaskBean() {
        this.sList= new ArrayList();
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }

    public String getKl() {
        return kl;
    }

    public void setKl(String kl) {
        this.kl = kl;
    }

    public int getKnr() {
        return knr;
    }

    public void setKnr(int knr) {
        this.knr = knr;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public Object addSchueler() {
        this.sList.add(new Schueler(vn, nn, kl, alter, knr));
        
        return null;
    }

    public Object printList() {
        for(Schueler s:this.sList){
            System.out.println(s);
        }
        
        return null;
    }
}
