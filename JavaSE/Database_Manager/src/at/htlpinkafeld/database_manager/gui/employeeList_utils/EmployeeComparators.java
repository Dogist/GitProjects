/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui.employeeList_utils;

import at.htlpinkafeld.database_manager.pojo.Employee;
import java.util.Comparator;

/**
 *
 * @author Martin Six
 */
public final class EmployeeComparators {

    private EmployeeComparators() {
    }

    public static class EmpnoComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getEmpno().compareTo(o2.getEmpno());
        }
    }

    public static class EnameComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getEname().compareTo(o2.getEname());
        }
    }

    public static class JobComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getJob().compareTo(o2.getJob());
        }
    }

    public static class MgrComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getMgr().getEmpno(), o2.getMgr().getEmpno());
        }
    }

    public static class HiredateComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getHiredate().compareTo(o2.getHiredate());
        }
    }

    public static class SalComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o1.getSal(), o2.getSal());
        }
    }

    public static class CommComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o1.getComm(), o2.getComm());
        }
    }

    public static class DeptnoComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getDept().getDeptno(), o2.getDept().getDeptno());
        }
    }
}
