/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.gui;

import at.htlpinkafeld.pojo.Pupil;
import at.htlpinkafeld.service.PupilManagerService;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Martin Six
 */
public class PupilBean {

    @ManagedProperty(value = "#{pupilManagerService}")
    private final PupilManagerService pupilManagerService;
    private Pupil activePupil;

    public PupilBean() {
        pupilManagerService = new PupilManagerService();
        activePupil = new Pupil();
    }

    public Pupil getActivePupil() {
        return activePupil;
    }

    public int getAge() {
        if (activePupil.getBirthdate() != null) {
            LocalDate localDate = activePupil.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return localDate.until(LocalDate.now()).getYears();
        }
        return 0;
    }

    public List<Pupil> getPupList() {
        return pupilManagerService.getPupList();
    }

    public Object newPupil() {
        activePupil = new Pupil();
        return "index.xhtml";
    }

    public Object savePupil() {
        if (!pupilManagerService.contains(activePupil)) {
            pupilManagerService.addPupil(activePupil);
        }
        return null;
    }

    public Object printPList() {
        pupilManagerService.getPupList().stream().forEach((p) -> {
            System.out.println(p);
        });
        return null;
    }

    public Object editPupil(Pupil p) {
        activePupil = p;
        return null;
    }

    public Object deletePupil(Pupil p) {
        pupilManagerService.removePupil(p);
        return null;
    }
}
