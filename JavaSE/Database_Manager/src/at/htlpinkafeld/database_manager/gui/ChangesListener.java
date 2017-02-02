/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

/**
 *
 * @author Martin Six
 */
public interface ChangesListener {

    public void setUncommitedChanges();

    public void unsetUncommitedChanges();
}
