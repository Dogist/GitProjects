/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.pojo;

/**
 *
 * @author Martin Six
 */
public class Salgrade implements Identifiable {

    private int grade;
    private double losal;
    private double hisal;

    public Salgrade() {
        grade = -1;
    }

    public Salgrade(Salgrade s) {
        this.grade = s.grade;
        this.losal = s.losal;
        this.hisal = s.hisal;
    }

    public Salgrade(double losal, double hisal) {
        this();
        this.losal = losal;
        this.hisal = hisal;
    }

    @Override
    public int getId() {
        return grade;
    }

    @Override
    public void setId(int id) {
        grade = id;
    }

    public double getLosal() {
        return losal;
    }

    public void setLosal(double losal) {
        this.losal = losal;
    }

    public double getHisal() {
        return hisal;
    }

    public void setHisal(double hisal) {
        this.hisal = hisal;
    }

    @Override
    public String toString() {
        return "Salgrade{" + "grade=" + grade + ", losal=" + losal + ", hisal=" + hisal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.grade;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.losal) ^ (Double.doubleToLongBits(this.losal) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.hisal) ^ (Double.doubleToLongBits(this.hisal) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salgrade other = (Salgrade) obj;
        if (this.grade != other.grade) {
            return false;
        }
        return true;
    }

}
