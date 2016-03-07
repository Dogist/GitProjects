/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.gui;

import at.htlpinkafeld.pojo.Pupil;
import at.htlpinkafeld.service.PupilManagerService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Martin Six
 */
public class PupilBean {

    @ManagedProperty(value = "#{pupilManagerService}")
    private final PupilManagerService pupilManagerService;
    private Pupil activePupil;
    private int activeIdx;
    private Date DummyValObj;

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
        } else {
            pupilManagerService.setPupil(activeIdx, activePupil);
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
        activeIdx = pupilManagerService.indexOf(p);
        activePupil = p;
        return null;
    }

    public Object deletePupil(Pupil p) {
        pupilManagerService.removePupil(p);
        return null;
    }

    public void assignedChange(ValueChangeEvent e) {
        Boolean b = (Boolean) e.getNewValue();
        if (b != null) {
            this.activePupil.setAssigned(b);
        }
        FacesContext.getCurrentInstance().renderResponse();
    }

    public Object resetPage() {
        this.activePupil.setAssigned(Boolean.FALSE);
        this.activePupil.setBirthdate(null);
        this.activePupil.setEntryDate(null);
        this.activePupil.setFirstName("");
        this.activePupil.setLastName("");
        this.activePupil.setForm("");
        FacesContext.getCurrentInstance().renderResponse();

        return null;
    }

    public Object deleteCurrP() {
        this.pupilManagerService.removePupil(activePupil);
        resetPage();
        return null;
    }

    public void validateForm(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String form = o.toString();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Form Format!([1-5]+aBZ...)", null);
        if (form.charAt(0) > '0' && form.charAt(0) < '6') {
            for (Character c : form.substring(1).toCharArray()) {
                if (Character.toLowerCase(c) <= 'z' && Character.toLowerCase(c) >= 'a') {

                } else {
                    throw new ValidatorException(message);
                }
            }
        } else {
            throw new ValidatorException(message);
        }
    }

    public void validateBirthday(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Date dat = (Date) o;
        this.DummyValObj = dat;
        if (dat.after(Date.from(Instant.now()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Birthday is in the future!", null);
            throw new ValidatorException(message);
        }
    }

    public void validateEntryDate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Date entDat = (Date) o;
        Date birthD = this.DummyValObj;
        if (entDat.after(Date.from(Instant.now()))) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entry Date is in the future!", null);
            throw new ValidatorException(message);
        }

        if (birthD.after(entDat)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Birthday after Entry Date!", null);
            throw new ValidatorException(message);
        }
    }
}
