/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.lamda.presentation;

import at.htlpinkafeld.lamda.service.AppService;
import at.htlpinkafeld.lamda.service.Display;
import at.htlpinkafeld.lamda.service.Student;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author masix
 */
public class MainFrame extends JFrame {

    private AppService appService;

    private Map<JCheckBox, Predicate<Student>> filterMap = new HashMap<>();
    private Map<JRadioButton, Comparator<Student>> orderMap = new HashMap<>();
    private Map<JCheckBox, Consumer<Display<Student>>> actionMap = new HashMap<>();

    private JList<Display> displayList;

    private JTextField nameUnivFilterTextField = null;
    private JTextField dateFilterTextField = null;

    public MainFrame() throws HeadlessException {
        super("Flexible Student List");

        appService = AppService.getAppService();

        BorderLayout mainLayout = new BorderLayout();
        Container container = super.getContentPane();
        container.setLayout(mainLayout);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionsPanel.add(createFilterByPanel());
        optionsPanel.add(createFilterTextPanel());
        optionsPanel.add(createOrderByPanel());
        optionsPanel.add(createActionsPanel());

        JButton actionButton = new JButton(new AbstractAction("Action") {
            @Override
            public void actionPerformed(ActionEvent e) {

                Comparator<Student> comparator = null;
                List<Predicate<Student>> filters = new LinkedList<>();
                List<Consumer<Display<Student>>> actions = new LinkedList<>();

                for (Entry<JRadioButton, Comparator<Student>> compEntry : orderMap.entrySet()) {
                    if (compEntry.getKey().isSelected()) {
                        comparator = compEntry.getValue();
                        break;
                    }
                }

                for (Entry<JCheckBox, Predicate<Student>> filterEntry : filterMap.entrySet()) {
                    if (filterEntry.getKey().isSelected()) {
                        filters.add(filterEntry.getValue());
                    }
                }

                for (Entry<JCheckBox, Consumer<Display<Student>>> actionsEntry : actionMap.entrySet()) {
                    if (actionsEntry.getKey().isSelected()) {
                        actions.add(actionsEntry.getValue());
                    }
                }

                displayList.setListData(appService.processList(comparator, filters, actions).toArray(new Display[]{}));
            }
        });

        displayList = new JList<>();
        actionButton.getAction().actionPerformed(null);

        container.add(optionsPanel, BorderLayout.WEST);
        container.add(actionButton, BorderLayout.SOUTH);
        container.add(displayList, BorderLayout.CENTER);

    }

    private JPanel createFilterByPanel() {
        JPanel filterByPanel = new JPanel(new FlowLayout());

        JCheckBox checkbox = new JCheckBox("by Date");
        filterByPanel.add(checkbox);
        filterMap.put(checkbox, (t) -> {
            try {
                return t.getBirthday().isBefore(LocalDate.parse(dateFilterTextField.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            } catch (DateTimeParseException ex) {
                return true;
            }
        });

        checkbox = new JCheckBox("by Name");
        filterByPanel.add(checkbox);
        filterMap.put(checkbox, (t) -> {
            return t.getName().toUpperCase().startsWith(nameUnivFilterTextField.getText().trim().toUpperCase());
        });

        checkbox = new JCheckBox("by University");
        filterByPanel.add(checkbox);
        filterMap.put(checkbox, (t) -> {
            return t.getUniversity().toUpperCase().startsWith(nameUnivFilterTextField.getText().trim().toUpperCase());
        });

        checkbox = new JCheckBox("by even Reg. Number");
        filterByPanel.add(checkbox);
        filterMap.put(checkbox, (t) -> {
            return t.getRegistrationNumber() % 2 == 0;
        });

        checkbox = new JCheckBox("by odd Reg. Number");
        filterByPanel.add(checkbox);
        filterMap.put(checkbox, (t) -> {
            return t.getRegistrationNumber() % 2 == 1;
        });

        filterByPanel.setBorder(BorderFactory.createTitledBorder("Select filters:"));

        return filterByPanel;
    }

    private JPanel createFilterTextPanel() {
        JPanel filterTextPanel = new JPanel(new FlowLayout());

        filterTextPanel.add("nameUnvLabel", new JLabel("Name/Univ. starts with:"));
        nameUnivFilterTextField = new JTextField(10);
        filterTextPanel.add("nameUnvText", nameUnivFilterTextField);
        filterTextPanel.add("dateLabel", new JLabel("Date limit:"));
        dateFilterTextField = new JTextField(10);
        filterTextPanel.add("dateText", dateFilterTextField);
        filterTextPanel.add("dateFormatLabel", new JLabel("dd.mm.yyyy"));

        filterTextPanel.setBorder(BorderFactory.createTitledBorder("Enter Criterions:"));
        return filterTextPanel;
    }

    private JPanel createOrderByPanel() {
        JPanel orderByTextPanel = new JPanel(new FlowLayout());

        ButtonGroup bg = new ButtonGroup();

        JRadioButton radioButton = new JRadioButton("Sort by Date");
        bg.add(radioButton);
        orderByTextPanel.add(radioButton);
        orderMap.put(radioButton, (Student s1, Student s2) -> {
            return Objects.compare(s1.getBirthday(), s2.getBirthday(), Comparator.nullsLast(Comparator.naturalOrder()));
        });

        radioButton = new JRadioButton("Sort by Name");
        bg.add(radioButton);
        orderByTextPanel.add(radioButton);
        orderMap.put(radioButton, (Student s1, Student s2) -> {
            return Objects.compare(s1.getName(), s2.getName(), Comparator.nullsLast(Comparator.naturalOrder()));
        });

        radioButton = new JRadioButton("Sort by University");
        bg.add(radioButton);
        orderByTextPanel.add(radioButton);
        orderMap.put(radioButton, (Student s1, Student s2) -> {
            return Objects.compare(s1.getUniversity(), s2.getUniversity(), Comparator.nullsLast(Comparator.naturalOrder()));
        });

        radioButton = new JRadioButton("Sort by Registrationnumber");
        bg.add(radioButton);
        orderByTextPanel.add(radioButton);
        orderMap.put(radioButton, (Student s1, Student s2) -> {
            return Objects.compare(s1.getRegistrationNumber(), s2.getRegistrationNumber(), Comparator.nullsLast(Comparator.naturalOrder()));
        });

        orderByTextPanel.setBorder(BorderFactory.createTitledBorder("Select sort criterion:"));
        return orderByTextPanel;
    }

    private JPanel createActionsPanel() {
        JPanel actionsPanel = new JPanel(new FlowLayout());

        JCheckBox checkbox = new JCheckBox("Add Age");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            d.setDispString(d.getDispString() + "Age: " + d.getSource().getBirthday().until(LocalDate.now(), ChronoUnit.YEARS) + " - ");
        });

        checkbox = new JCheckBox("Add Name");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            d.setDispString(d.getDispString() + "Name: " + d.getSource().getName() + " - ");
        });

        checkbox = new JCheckBox("Add University");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            d.setDispString(d.getDispString() + "University: " + d.getSource().getUniversity() + " - ");
        });

        checkbox = new JCheckBox("Print Age");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            System.out.println("Age: " + d.getSource().getBirthday().until(LocalDate.now(), ChronoUnit.YEARS));
        });

        checkbox = new JCheckBox("Print Name");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            System.out.println("Name: " + d.getSource().getName());
        });

        checkbox = new JCheckBox("Print University");
        actionsPanel.add(checkbox);
        actionMap.put(checkbox, (Display<Student> d) -> {
            System.out.println("University: " + d.getSource().getUniversity());
        });

        actionsPanel.setBorder(BorderFactory.createTitledBorder("Select actions:"));
        return actionsPanel;
    }

}
