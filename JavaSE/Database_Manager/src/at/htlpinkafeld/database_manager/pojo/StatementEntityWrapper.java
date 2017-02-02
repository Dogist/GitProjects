/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.pojo;

/**
 *
 * @author Martin Six
 */
public class StatementEntityWrapper<T> {

    public enum StatementType {
        INSERT, UPDATE, DELETE
    };
    private final T entity;
    private final StatementType statementtype;

    public StatementEntityWrapper(StatementType statementtype, T o) {
        entity = o;
        this.statementtype = statementtype;
    }

    public T getEntity() {
        return entity;
    }

    public StatementType getStatementtype() {
        return statementtype;
    }

}
