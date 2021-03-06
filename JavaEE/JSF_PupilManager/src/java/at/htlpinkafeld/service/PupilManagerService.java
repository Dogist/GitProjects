/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.service;

import at.htlpinkafeld.pojo.Pupil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class PupilManagerService {

    private final List<Pupil> pupList;

    public PupilManagerService() {
        pupList = new ArrayList<>();
    }

    public boolean contains(Pupil p) {
        return pupList.contains(p);
    }

    public boolean addPupil(Pupil p) {
        return pupList.add(p);
    }

    public boolean removePupil(Pupil p) {
        return pupList.remove(p);
    }

    public Pupil getPupil(int index) {
        return pupList.get(index);
    }

    public Pupil setPupil(int index, Pupil pupil) {
        return pupList.set(index, pupil);
    }

    public int indexOf(Object o) {
        return pupList.indexOf(o);
    }
    
    public List<Pupil> getPupList() {
        return pupList;
    }
}
