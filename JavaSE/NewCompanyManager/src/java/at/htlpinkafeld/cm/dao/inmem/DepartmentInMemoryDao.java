/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.cm.dao.inmem;

import at.htlpinkafeld.cm.dao.interf.DepartmentDao;
import at.htlpinkafeld.cm.pojo.Department;
import at.htlpinkafeld.cm.pojo.DepartmentImpl;

/**
 *
 * @author Martin Six
 */
public class DepartmentInMemoryDao extends BaseInMemoryDao<Department> implements DepartmentDao {

    @Override
    protected Department clone(Department t) {
        return new DepartmentImpl(t);
    }

}
