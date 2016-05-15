/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.inmem;

import at.htlpinkafeld.cm.dao.interf.SalgradeDao;
import at.htlpinkafeld.cm.pojo.Salgrade;

/**
 *
 * @author Martin Six
 */
public class SalgradeInMemoryDao extends BaseInMemoryDao<Salgrade> implements SalgradeDao {

    protected SalgradeInMemoryDao() {
    }

    @Override
    protected Salgrade clone(Salgrade t) {
        return new Salgrade(t);
    }

}
