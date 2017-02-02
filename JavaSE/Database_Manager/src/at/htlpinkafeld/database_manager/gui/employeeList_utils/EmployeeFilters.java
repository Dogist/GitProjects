/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui.employeeList_utils;

import at.htlpinkafeld.database_manager.pojo.Employee;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 * @author Martin Six
 */
public final class EmployeeFilters {

    private EmployeeFilters() {

    }

    public static final class EmpnoFilter implements Predicate<Employee> {

        private final Integer filterEmpno;
        private final String option;

        public EmpnoFilter(Integer filterEmpno, String option) {
            this.filterEmpno = filterEmpno;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return Objects.equals(t.getEmpno(), filterEmpno);
                case "!=":
                    return !Objects.equals(t.getEmpno(), filterEmpno);
            }
            if (filterEmpno != null && t.getEmpno() != null) {
                switch (option) {
                    case "<=":
                        return t.getEmpno() <= filterEmpno;
                    case ">=":
                        return t.getEmpno() >= filterEmpno;
                    case "<":
                        return t.getEmpno() < filterEmpno;
                    case ">":
                        return t.getEmpno() > filterEmpno;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    public static final class EnameFilter implements Predicate<Employee> {

        private String filterEname;
        private final String option;

        public EnameFilter(String filterEname, String option) {
            filterEname = filterEname.toLowerCase(); // ignoring locale for now
            filterEname = filterEname.replace(".", "\\."); // "\\" is escaped to "\" (thanks, Alan M)
            // ... escape any other potentially problematic characters here
            filterEname = filterEname.replace("?", ".");
            this.filterEname = filterEname.replace("%", ".*");

            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return t.getEname().equalsIgnoreCase(filterEname);
                case "!=":
                    return !t.getEname().equalsIgnoreCase(filterEname);
                case "like":
                    String enameLow = t.getEname().toLowerCase();
                    return enameLow.matches(filterEname);
                default:
                    return false;
            }
        }
    }

    public static final class JobFilter implements Predicate<Employee> {

        private String filterJob;
        private final String option;

        public JobFilter(String filterJob, String option) {
            this.filterJob = filterJob;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return t.getJob().equalsIgnoreCase(filterJob);
                case "!=":
                    return !t.getJob().equalsIgnoreCase(filterJob);
                case "like":
                    filterJob = filterJob.toLowerCase(); // ignoring locale for now
                    filterJob = filterJob.replace(".", "\\."); // "\\" is escaped to "\" (thanks, Alan M)
                    // ... escape any other potentially problematic characters here
                    filterJob = filterJob.replace("?", ".");
                    filterJob = filterJob.replace("%", ".*");
                    String jobLow = t.getEname().toLowerCase();
                    return jobLow.matches(filterJob);
                default:
                    return false;
            }
        }
    }

    public static final class MgrFilter implements Predicate<Employee> {

        private final Integer filterMgr;
        private final String option;

        public MgrFilter(Integer filterMgr, String option) {
            this.filterMgr = filterMgr;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            if (t.getMgr() == null) {
                switch (option) {
                    case "<=":
                    case ">=":
                    case "==":
                        return Objects.equals(t.getMgr(), filterMgr);
                    case "!=":
                        return !Objects.equals(t.getMgr(), filterMgr);
                    default:
                        return false;
                }
            }
            switch (option) {
                case "==":
                    return Objects.equals(t.getMgr().getEmpno(), filterMgr);
                case "!=":
                    return !Objects.equals(t.getMgr().getEmpno(), filterMgr);
            }
            if (filterMgr != null) {
                switch (option) {
                    case "<=":
                        return t.getMgr().getEmpno() <= filterMgr;
                    case ">=":
                        return t.getMgr().getEmpno() >= filterMgr;
                    case "<":
                        return t.getMgr().getEmpno() < filterMgr;
                    case ">":
                        return t.getMgr().getEmpno() > filterMgr;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    public static final class HiredateFilter implements Predicate<Employee> {

        private final LocalDate filterHiredate;
        private final String option;

        public HiredateFilter(LocalDate filterHiredate, String option) {
            this.filterHiredate = filterHiredate;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return Objects.equals(t.getHiredate(), filterHiredate);
                case "!=":
                    return !Objects.equals(t.getHiredate(), filterHiredate);
            }
            if (filterHiredate != null && t.getHiredate() != null) {
                switch (option) {
                    case "<=":
                        return t.getHiredate().isBefore(filterHiredate) || t.getHiredate().isEqual(filterHiredate);
                    case ">=":
                        return t.getHiredate().isAfter(filterHiredate) || t.getHiredate().isEqual(filterHiredate);
                    case "<":
                        return t.getHiredate().isBefore(filterHiredate);
                    case ">":
                        return t.getHiredate().isAfter(filterHiredate);
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    public static final class SalFilter implements Predicate<Employee> {

        private final Double filterSal;
        private final String option;

        public SalFilter(Double filterSal, String option) {
            this.filterSal = filterSal;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return Objects.equals(t.getSal(), filterSal);
                case "!=":
                    return !Objects.equals(t.getSal(), filterSal);
            }
            if (filterSal != null && t.getSal() != null) {
                switch (option) {
                    case "<=":
                        return t.getSal() <= filterSal;
                    case ">=":
                        return t.getSal() >= filterSal;
                    case "<":
                        return t.getSal() < filterSal;
                    case ">":
                        return t.getSal() > filterSal;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    public static final class CommFilter implements Predicate<Employee> {

        private final Double filterComm;
        private final String option;

        public CommFilter(Double filterComm, String option) {
            this.filterComm = filterComm;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            switch (option) {
                case "==":
                    return Objects.equals(t.getComm(), filterComm);
                case "!=":
                    return !Objects.equals(t.getComm(), filterComm);
            }
            if (filterComm != null && t.getComm() != null) {
                switch (option) {
                    case "<=":
                        return t.getComm() <= filterComm;
                    case ">=":
                        return t.getComm() >= filterComm;
                    case "<":
                        return t.getComm() < filterComm;
                    case ">":
                        return t.getComm() > filterComm;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

    public static final class DeptnoFilter implements Predicate<Employee> {

        private final Integer filterDeptno;
        private final String option;

        public DeptnoFilter(Integer filterDeptno, String option) {
            this.filterDeptno = filterDeptno;
            this.option = option;
        }

        @Override
        public boolean test(Employee t) {
            if (t.getDept() == null) {
                switch (option) {
                    case "<=":
                    case ">=":
                    case "==":
                        return Objects.equals(t.getDept(), filterDeptno);
                    case "!=":
                        return !Objects.equals(t.getDept(), filterDeptno);
                    default:
                        return false;
                }
            }
            switch (option) {
                case "==":
                    return Objects.equals(t.getDept().getDeptno(), filterDeptno);
                case "!=":
                    return !Objects.equals(t.getDept().getDeptno(), filterDeptno);
            }
            if (filterDeptno != null) {
                switch (option) {
                    case "==":
                        return t.getDept().getDeptno() == filterDeptno;
                    case "!=":
                        return t.getDept().getDeptno() != filterDeptno;
                    case "<=":
                        return t.getDept().getDeptno() <= filterDeptno;
                    case ">=":
                        return t.getDept().getDeptno() >= filterDeptno;
                    case "<":
                        return t.getDept().getDeptno() < filterDeptno;
                    case ">":
                        return t.getDept().getDeptno() > filterDeptno;
                    default:
                        return false;
                }
            }
            return false;
        }
    }

}
