/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.service;

import at.htlpinkafeld.pojo.Pupil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class PupilManagerService {

    private final List<Pupil> pupList;
    public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");

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

    public List<Pupil> getPupList() {
        return pupList;
    }
}
