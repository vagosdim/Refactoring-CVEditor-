package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ProfessionalExperienceListener implements ActionListener{

	private HashMap<String, String> professionalExperience;
	private String oldDate, newDate, tempKey, tempStr;
	private InitWidgets initWidgets = new InitWidgets();
	private CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	
	public ProfessionalExperienceListener(HashMap<String, String> professionalExperience) {
		this.professionalExperience = professionalExperience;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setBounds( 500, 250, 600, 451);
		frame.getContentPane().setLayout(null);
		cvCommonFunctions.onExit(frame, true);
		frame.setResizable(false);
		
		JPanel pane = (JPanel)  frame.getContentPane();
		
		JTextField companyField = initWidgets.createJtextField(pane, "companyField", 128, 213, 319, 20,10);
		JTextField jobField  = initWidgets.createJtextField(pane, "jobField", 128, 244, 319, 20,10);
		JTextField dateField = initWidgets.createJtextField(pane, "dateField", 128, 275, 319, 20,10);
		
		JLabel companyLabel =  initWidgets.createJLabel(pane, "Company", 10, 213, 112, 14);
		JLabel jobTitleLabel = initWidgets.createJLabel(pane, "Job Title", 10, 244, 112, 14);
		JLabel lblDate = initWidgets.createJLabel(pane, "Date", 10, 275, 46, 14);
		JLabel listOfAchievementsLabel = initWidgets.createJLabel(pane, "List of Achievements: ", 10, 306, 120, 14);
		JLabel separateLabel = initWidgets.createJLabel(pane, "(seperate  by ', ')", 10, 323, 100, 14);
		JLabel separate2Label = initWidgets.createJLabel(pane, "(Comma + Space)", 10, 340, 120, 14);
		JLabel paragraphLabel = initWidgets.createJLabel(pane, "Paragraph", 10, 366, 84, 14);
		
		JTextArea listOfAchievementsArea = initWidgets.createJTextArea(pane,128, 306, 318, 45);
		JTextArea paragraphArea = initWidgets.createJTextArea(pane,129, 362, 318, 45);
		
		JScrollPane listScroll = new JScrollPane();
		
		DefaultListModel<String> mainList = new DefaultListModel<>();
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.setVisibleRowCount(2);
		list.setBounds(10, 11, 564, 191);
		
		frame.getContentPane().add(list);
		
		listScroll.setViewportView(list);
		listScroll.setBounds(10, 11, 564, 201);
		
		frame.getContentPane().add(listScroll);
		
		for (String key  : professionalExperience.keySet() ) {
			mainList.addElement(key + ", " + professionalExperience.get(key));
		}

		list.setModel(mainList);
		
		JButton remove = initWidgets.createButton(pane,"Remove Selected",462, 212, 112, 60);
		JButton edit = initWidgets.createButton(pane,"Edit Selected",462, 279, 112, 60);
		JButton add = initWidgets.createButton(pane,"Add",462, 345, 112, 60);
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
				tempStr = ( listOfAchievementsArea.getText() + ", " + paragraphArea.getText() );
				
				if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
						|| listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
					JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
				} else if (professionalExperience.containsKey(tempKey)) {
					JOptionPane.showMessageDialog(null, "Please change one of the values: Company, Job Title, Date");
				} else {
					mainList.addElement(companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText()
						+ ", " + listOfAchievementsArea.getText()  + ", " + paragraphArea.getText() );	
					
					professionalExperience.put(tempKey , tempStr);
					list.setModel(mainList);
				}
			}
		});
		
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tempListItem[];
				if (list.getSelectedIndex() != -1){
					tempListItem = list.getSelectedValue().split(", ");
					tempKey = (tempListItem[0] + ", " + tempListItem[1] + ", " + tempListItem[2]);
					professionalExperience.remove(tempKey);
					
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
					String[] tempArray = list.getSelectedValue().split(", ");
					String tempListKey = (tempArray[0] + ", " + tempArray[1] + ", " + tempArray[2]);
					tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
					tempStr = (listOfAchievementsArea.getText() + ", " + paragraphArea.getText());
					
					if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
							|| listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
						JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
					} else {
						if (tempListKey.equals(tempKey)){
							professionalExperience.put(tempKey, tempStr);
						} else {
							professionalExperience.remove(tempListKey);
							professionalExperience.put(tempKey, tempStr);
						}
						mainList.set(list.getSelectedIndex(), (tempKey + ", " + tempStr) );
						list.setModel(mainList);
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
					
					tempStr = listSelectedSplit[3];
					
					for (int i = 0; i < listSelectedSplit.length -1 ; i++){
						if (i > 3){
							tempStr = (tempStr + ", " + listSelectedSplit[i]);
						}
					}
					companyField.setText(listSelectedSplit[0]);
					jobField.setText(listSelectedSplit[1]);
					dateField.setText(listSelectedSplit[2]);
					listOfAchievementsArea.setText(tempStr);
					paragraphArea.setText(listSelectedSplit[listSelectedSplit.length-1]);
				}
			}
		};
		
		list.addMouseListener(mouseListen);
		
		frame.setVisible(true);
	}
}
