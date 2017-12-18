package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SkillsAndExperienceListener implements ActionListener{

	private InitWidgets initWidgets = new InitWidgets();
	private CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	private String type;
	private JButton button;
	private ArrayList<String> skillsAndExperience;
	
	public SkillsAndExperienceListener(JButton button, String type, ArrayList<String> skillsAndExperience) {
		this.button = button;
		this.type = type;
		this.skillsAndExperience = skillsAndExperience;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFrame frame = cvCommonFunctions.createFrame(type, 500, 250, 600, 400, false);
		cvCommonFunctions.onExit(frame, true);
		
		JPanel pane = (JPanel)  frame.getContentPane();

		JTextField field = initWidgets.createJtextField(pane, null, 10, 280, 319, 20, 10);
		JTextField skills = initWidgets.createJtextField(pane, null, 10, 330, 319, 20, 10);
		JLabel fieldLabel = initWidgets.createJLabel(pane, "Field", 10, 260, 319, 14);
		JLabel skillsLabel = initWidgets.createJLabel(pane, "Skills And Experience", 10, 310, 319, 14);
		JButton remove = initWidgets.createButton(pane, "Remove Selected", 446, 236, 128, 35);
		JButton edit = initWidgets.createButton(pane, "Edit Selected", 446, 275, 128, 35);
		JButton add = initWidgets.createButton(pane, "Add", 446, 314, 128, 35);
		
		JScrollPane listScroll = new JScrollPane();
		
		DefaultListModel<String> mainList = new DefaultListModel<>();
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(2);
		list.setBounds(10, 11, 564, 201);
		
		frame.getContentPane().add(list);
		
		listScroll.setViewportView(list);
		listScroll.setBounds(10, 11, 564, 201);
		
		frame.getContentPane().add(listScroll);
		
		list.setModel(mainList);
		
		for (String temp : skillsAndExperience){
			mainList.addElement(temp);
		}
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (field.getText().equals(" ") || skills.getText().equals(" ")  ){
					JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
				}else {
					mainList.addElement(field.getText() + ", " + skills.getText());
					skillsAndExperience.add(field.getText() + ", " + skills.getText());
					list.setModel(mainList);
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1){
				
					skillsAndExperience.remove(list.getSelectedValue());
				
					mainList.remove(list.getSelectedIndex());
					list.setModel(mainList);
				} else {
					JOptionPane.showMessageDialog(null, "Select an item from the list, then press remove to remove it.");
				}
			}
		});
		edit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1){

				
					int i = 0;
					String tempList = list.getSelectedValue() ;
					for (String temp : skillsAndExperience){
						if (!temp.equals(null)){
							if (tempList.equals(temp) ){
								skillsAndExperience.set(i, field.getText() + ", " + skills.getText() );
								
								mainList.set(list.getSelectedIndex(), skillsAndExperience.get(i));
								list.setModel(mainList);
							}
							i++;	
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No list item selected to edit.");
				}
			}
		});
		
		MouseListener mouseListen = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1){
					String[] listSelectedSplit = list.getSelectedValue().split(", "); 
					field.setText(listSelectedSplit[0]);
					skills.setText(listSelectedSplit[1]);
				}
			}
		};
		list.addMouseListener(mouseListen);
		frame.setVisible(true);
	}

}
