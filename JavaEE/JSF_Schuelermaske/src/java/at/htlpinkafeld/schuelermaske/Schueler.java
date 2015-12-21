/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.schuelermaske;

/**
 *
 * @author Martin Six
 */
public class Schueler {

    private String vname;
    private String nname;
    private String klasse;
    private int alter;
    private int katalogNr;

    public Schueler(String vname, String nname, String klasse, int alter, int katalogNr) {
        this.vname = vname;
        this.nname = nname;
        this.klasse = klasse;
        this.alter = alter;
        this.katalogNr=katalogNr;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public int getKatalogNr() {
        return katalogNr;
    }

    public void setKatalogNr(int katalogNr) {
        this.katalogNr = katalogNr;
    }

    @Override
    public String toString() {
        return "Schueler{" + "vname=" + vname + ", nname=" + nname + ", klasse=" + klasse + ", alter=" + alter + ", katalogNr=" + katalogNr + '}';
    }

}
