/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.service;

import at.htlpinkafeld.cm.dao.jdbc.JDBCFactory;
import at.htlpinkafeld.cm.dao.interf.SalgradeDao;
import at.htlpinkafeld.cm.pojo.Salgrade;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class SalgradeService {

    private static SalgradeService ss;
    private final SalgradeDao sDao;

    public static SalgradeService getInstance() {
        if (ss == null) {
            ss = new SalgradeService();
        }
        return ss;
    }

    private SalgradeService() {
        sDao = JDBCFactory.getDAOFactory().getSalgradeDAO();
    }

    public void createSalg(Salgrade t) {
        sDao.create(t);
    }

    public Salgrade readSalg(int id) {
        return sDao.read(id);
    }

    public void updateSalg(Salgrade t) {
        sDao.update(t);
    }

    public void deleteSalg(Salgrade t) {
        sDao.delete(t);
    }

    public List<Salgrade> listSalgs() {
        return sDao.list();
    }
}
