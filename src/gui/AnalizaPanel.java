package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import fa.Frekvenca;
import gui.file.TextFilter;
import gui.table.AnalizaModel;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;

import util.FAUtil;

/**
 * @author Gregor Panič
 *
 */
public class AnalizaPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton cypherOpen;
	private JFileChooser cypherChooser;
	private JTextArea cypherTextArea;
	private JLabel cypherFileLabel;
	private JTable analizaCypherTable;
	
	private JButton plainOpen;
	private JFileChooser plainChooser;
	private JTextArea plainTextArea;
	private JLabel plainFileLabel;
	private JTable analizaPlainTable;
	
	private JButton decypherSave;
	private JButton decypherButton;
	private JFileChooser decypherChooser;
	private JTextArea decypherTextArea;
	private JTable analizaDecypherTable;
	private JButton decypherSwapConfirm;
	private JFormattedTextField character1;
	private JFormattedTextField character2;
	
	private String sifrirano;
	private String desifrirano;
	
	private ArrayList<Frekvenca> sifFrek;
	private ArrayList<Frekvenca> refFrek;
	
	public AnalizaPanel() {
		
		cypherChooser=new JFileChooser();
		cypherChooser.setFileFilter(new TextFilter());
		cypherChooser.setAcceptAllFileFilterUsed(false);
		
		plainChooser=new JFileChooser();
		plainChooser.setFileFilter(new TextFilter());
		plainChooser.setAcceptAllFileFilterUsed(false);
		
		decypherChooser=new JFileChooser();
		decypherChooser.setFileFilter(new TextFilter());
		decypherChooser.setAcceptAllFileFilterUsed(false);
		
		JPanel cypherPanel=createCypherPanel();
		JPanel plainPanel=createPlainPanel();
		JPanel decypherPanel=createDecypherPanel();
		
		GroupLayout layout=new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(
						layout.createSequentialGroup()
						.addComponent(cypherPanel,500,500,500)
						.addComponent(plainPanel,500,500,500)
						)
				.addComponent(decypherPanel)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup()
						.addComponent(cypherPanel,350,350,350)
						.addComponent(plainPanel,350,350,350)
						)
				.addComponent(decypherPanel,250,250,250)
				);
		
		this.setLayout(layout);
	}
	
	private JPanel createCypherPanel() {
		JPanel cypherPanel=new JPanel();
		cypherPanel.setBorder(BorderFactory.createTitledBorder("Šifrirano besedilo"));
		
		cypherTextArea=new JTextArea();
		cypherTextArea.setLineWrap(true);
		cypherTextArea.setEditable(false);
		
		JScrollPane cypherScroll=new JScrollPane(cypherTextArea);
		
		cypherFileLabel=new JLabel("File:");
		
		cypherOpen=new JButton("Odpri");
		cypherOpen.addActionListener(new OpenButtonListener());
		
		analizaCypherTable=new JTable(new AnalizaModel());
		analizaCypherTable.setTableHeader(null);
		analizaCypherTable.setColumnSelectionAllowed(false);
		analizaCypherTable.setRowSelectionAllowed(false);
		JScrollPane analizaCypherScroll=new JScrollPane(analizaCypherTable);
		
		GroupLayout cypherPanelLayout=new GroupLayout(cypherPanel);
		cypherPanelLayout.setAutoCreateGaps(true);
		cypherPanelLayout.setAutoCreateContainerGaps(true);
		cypherPanelLayout.setHorizontalGroup(
				cypherPanelLayout.createSequentialGroup()
				.addGroup(
						cypherPanelLayout.createParallelGroup()
						.addComponent(cypherScroll)
						.addGroup(
								cypherPanelLayout.createSequentialGroup()
								.addComponent(cypherOpen)
								.addComponent(cypherFileLabel)
								)
						)
				.addComponent(analizaCypherScroll,75,75,75)
				);
		cypherPanelLayout.setVerticalGroup(
				cypherPanelLayout.createParallelGroup()
				.addGroup(
						cypherPanelLayout.createSequentialGroup()
						.addComponent(cypherScroll)
						.addGroup(
								cypherPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(cypherOpen)
								.addComponent(cypherFileLabel)
								)
						)
				.addComponent(analizaCypherScroll,271,271,271)
				);
		
		cypherPanel.setLayout(cypherPanelLayout);
		return cypherPanel;
	}
	
	private JPanel createPlainPanel() {
		JPanel plainPanel=new JPanel();
		plainPanel.setBorder(BorderFactory.createTitledBorder("Referenčno besedilo"));
		
		plainTextArea=new JTextArea();
		plainTextArea.setLineWrap(true);
		plainTextArea.setEditable(false);
		
		JScrollPane plainScroll=new JScrollPane(plainTextArea);
		
		plainFileLabel=new JLabel("File:");
		
		plainOpen=new JButton("Odpri");
		plainOpen.addActionListener(new OpenButtonListener());
		
		analizaPlainTable=new JTable(new AnalizaModel());
		analizaPlainTable.setTableHeader(null);
		analizaPlainTable.setColumnSelectionAllowed(false);
		analizaPlainTable.setRowSelectionAllowed(false);
		JScrollPane analizaPlainScroll=new JScrollPane(analizaPlainTable);
		
		GroupLayout	plainPanelLayout=new GroupLayout(plainPanel);
		plainPanelLayout.setAutoCreateGaps(true);
		plainPanelLayout.setAutoCreateContainerGaps(true);
		plainPanelLayout.setHorizontalGroup(
				plainPanelLayout.createSequentialGroup()
				.addGroup(
						plainPanelLayout.createParallelGroup()
						.addComponent(plainScroll)
						.addGroup(
								plainPanelLayout.createSequentialGroup()
								.addComponent(plainOpen)
								.addComponent(plainFileLabel)
								)
						)
				.addComponent(analizaPlainScroll,75,75,75)
				);
		plainPanelLayout.setVerticalGroup(
				plainPanelLayout.createParallelGroup()
				.addGroup(
						plainPanelLayout.createSequentialGroup()
						.addComponent(plainScroll)
						.addGroup(
								plainPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(plainOpen)
								.addComponent(plainFileLabel)
								)
						)
				.addComponent(analizaPlainScroll,271,271,271)
				);
		plainPanel.setLayout(plainPanelLayout);
		return plainPanel;
	}
	
	private JPanel createDecypherPanel() {
		JPanel decypherPanel=new JPanel();
		decypherPanel.setBorder(BorderFactory.createTitledBorder("Dešifrirano besedilo"));
		
		decypherTextArea=new JTextArea();
		decypherTextArea.setLineWrap(true);
		decypherTextArea.setEditable(false);
		
		JScrollPane decypherScroll=new JScrollPane(decypherTextArea);
		
		decypherSave=new JButton("Shrani");
		decypherSave.addActionListener(new SaveButtonListener());
		decypherButton=new JButton("Dešifriraj");
		decypherButton.addActionListener(new DecypherButtonListener());
		
		analizaDecypherTable=new JTable(new AnalizaModel());
		analizaDecypherTable.setTableHeader(null);
		analizaDecypherTable.setColumnSelectionAllowed(false);
		analizaDecypherTable.setRowSelectionAllowed(false);
		JScrollPane analizaDecypherScroll=new JScrollPane(analizaDecypherTable);
		
		JLabel label1=new JLabel("Zamenjaj");
		JLabel label2=new JLabel("z");
		try {
			character1 = new JFormattedTextField(new MaskFormatter("*"));
			character2 = new JFormattedTextField(new MaskFormatter("*"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		decypherSwapConfirm=new JButton("Potrdi");
		decypherSwapConfirm.addActionListener(new SwapCharButtonListener());
		
		GroupLayout	decypherPanelLayout=new GroupLayout(decypherPanel);
		decypherPanelLayout.setAutoCreateGaps(true);
		decypherPanelLayout.setAutoCreateContainerGaps(true);
		decypherPanelLayout.setHorizontalGroup(
				decypherPanelLayout.createSequentialGroup()
				.addGroup(
						decypherPanelLayout.createParallelGroup()
						.addComponent(decypherScroll)
						.addGroup(
								decypherPanelLayout.createSequentialGroup()
								.addComponent(decypherButton)
								.addComponent(decypherSave)
								.addGap(505)
								.addComponent(label1)
								.addComponent(character1,15,15,15)
								.addComponent(label2)
								.addComponent(character2,15,15,15)
								.addGap(30)
								.addComponent(decypherSwapConfirm)
								)
						)
				.addComponent(analizaDecypherScroll,75,75,75)
				);
		decypherPanelLayout.setVerticalGroup(
				decypherPanelLayout.createParallelGroup()
				.addGroup(
						decypherPanelLayout.createSequentialGroup()
						.addComponent(decypherScroll)
						.addGroup(
								decypherPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(decypherButton)
								.addComponent(decypherSave)
								.addComponent(label1)
								.addComponent(character1,18,18,18)
								.addComponent(label2)
								.addComponent(character2,18,18,18)
								.addComponent(decypherSwapConfirm)
								)
						)
				.addComponent(analizaDecypherScroll,171,171,171)
				);
		decypherPanel.setLayout(decypherPanelLayout);
		
		return decypherPanel;
	}
	
	private class OpenButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==cypherOpen) {
				int returnVal=cypherChooser.showOpenDialog(AnalizaPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = cypherChooser.getSelectedFile();
						StringBuilder sb=new StringBuilder();
						try {
							FileInputStream fis=new FileInputStream(file);
							InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
							BufferedReader br=new BufferedReader(isr);
							String line;
							while((line=br.readLine())!=null) {
								sb.append(line);
								sb.append('\n');
							}
							fis.close();
							isr.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						cypherFileLabel.setText("File: "+file.getAbsolutePath());
						cypherTextArea.setText(sb.toString());
						sifrirano=sb.toString();
						AnalizaModel model=(AnalizaModel) analizaCypherTable.getModel();
						ArrayList<Frekvenca> frekvence=FAUtil.getFrekvence(sb.toString());
						Collections.sort(frekvence);
						model.setTableData(frekvence);
						sifFrek=frekvence;
						model.fireTableDataChanged();
				}
			} else if(e.getSource()==plainOpen) {
				int returnVal=plainChooser.showOpenDialog(AnalizaPanel.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = plainChooser.getSelectedFile();
					StringBuilder sb=new StringBuilder();
					try {
						FileInputStream fis=new FileInputStream(file);
						InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
						BufferedReader br=new BufferedReader(isr);
						String line;
						while((line=br.readLine())!=null) {
							sb.append(line);
							sb.append('\n');
						}
						fis.close();
						isr.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					plainFileLabel.setText("File: "+file.getAbsolutePath());
					plainTextArea.setText(sb.toString());
					AnalizaModel model=(AnalizaModel) analizaPlainTable.getModel();
					ArrayList<Frekvenca> frekvence=FAUtil.getFrekvence(sb.toString());
					Collections.sort(frekvence);
					model.setTableData(frekvence);
					refFrek=frekvence;
					model.fireTableDataChanged();
				}
			}
		}
	}
	
	private class DecypherButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==decypherButton) {
				if(sifFrek!=null&&refFrek!=null&&!sifFrek.isEmpty()&&!refFrek.isEmpty()) {
					desifrirano=FAUtil.decypher(sifrirano, sifFrek, refFrek);
					decypherTextArea.setText(desifrirano);
					AnalizaModel model=(AnalizaModel)analizaDecypherTable.getModel();
					ArrayList<Frekvenca> frekvence=FAUtil.getFrekvence(desifrirano);
					Collections.sort(frekvence);
					model.setTableData(frekvence);
					model.fireTableDataChanged();
					JOptionPane.showMessageDialog(AnalizaPanel.this, "Datoteka je bila dešifrirana.");
				} else {
					JOptionPane.showMessageDialog(AnalizaPanel.this, "Naložite neprazno šifrirano in referenčno datoteko.");
				}
			}
		}
		
	}
	
	private class SaveButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==decypherSave) {
				if(sifFrek!=null&&refFrek!=null&&!sifFrek.isEmpty()&&!refFrek.isEmpty()) {
					int returnVal=decypherChooser.showSaveDialog(AnalizaPanel.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = decypherChooser.getSelectedFile();
						File output=new File(file.getAbsolutePath()+".txt");
						try {
							PrintWriter pw=new PrintWriter(output);
							pw.print(desifrirano);
							pw.close();
							JOptionPane.showMessageDialog(AnalizaPanel.this, "Datoteka je bila shranjena.");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
					}
				} else {
					JOptionPane.showMessageDialog(AnalizaPanel.this, "Naložite neprazno šifrirano in referenčno datoteko.");
				}
			}
		}
	}

	private class SwapCharButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==decypherSwapConfirm) {
				if(sifFrek!=null&&refFrek!=null&&!sifFrek.isEmpty()&&!refFrek.isEmpty()) {
					if((!character1.getText().equals(""))&&(!character2.getText().equals(""))) {
						String crka1=character1.getText();
						String crka2=character2.getText();
						desifrirano=FAUtil.swapCharacters(desifrirano, character1.getText(), character2.getText());
						decypherTextArea.setText(desifrirano);
						AnalizaModel model=(AnalizaModel)analizaDecypherTable.getModel();
						ArrayList<Frekvenca> frekvence=FAUtil.getFrekvence(desifrirano);
						Collections.sort(frekvence);
						model.setTableData(frekvence);
						model.fireTableDataChanged();
						character1.setText(null);
						character2.setText(null);
						JOptionPane.showMessageDialog(AnalizaPanel.this, "Črki "+crka1+" in "+crka2+" sta bili zamenjani.");
					}
				} else {
					JOptionPane.showMessageDialog(AnalizaPanel.this, "Naložite neprazno šifrirano in referenčno datoteko.");
				}
				
			}
		}
		
		
	}
}
