/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import javafx.event.ActionEvent;

/**
 *
 * @author Martin Six
 */
public interface ToolBarDelegateInterface {

    public void handelInsertAction(ActionEvent event);

    public void handelSaveAction(ActionEvent event);

    public void handelDeleteAction(ActionEvent event);

    public void handelRevertAction(ActionEvent event);
}
