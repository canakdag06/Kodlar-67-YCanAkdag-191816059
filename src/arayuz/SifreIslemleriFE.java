package arayuz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import database.AccountsDAL;
import database.KategoriDAL;
import database.PersonelDAL;
import database.YetkilerDAL;
import entities.Accounts;
import entities.Personel;
import entities.Yetkiler;
import interfaces.ArayuzInterface;

public class SifreIslemleriFE extends JDialog implements ArayuzInterface
{
	public SifreIslemleriFE()
	{
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("�ifre ��lemleri"));
		
		add(panel);
		setTitle("�ifre ��lemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5,2));
		JLabel personelLabel = new JLabel("Personel Se�:",JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);
		
		JLabel yetkiLabel = new JLabel("Yetki Se�:",JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);
		
		JLabel passwordLabel = new JLabel("�ifre:",JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("�ifre Tekrar:",JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrarField = new JPasswordField(10);
		panel.add(passTekrarField);
		
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
		
		
		kaydetButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Accounts contract = new Accounts();
				Personel pContract = (Personel) personelBox.getSelectedItem();
				Yetkiler yContract = (Yetkiler) yetkiBox.getSelectedItem();
				
				if(passField.getText().equals(passTekrarField.getText()))
				{
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					new AccountsDAL().Insert(contract);
					JOptionPane.showMessageDialog(null,pContract.getAdSoyad()+" Adl� Ki�i "+yContract.getAd()+" Olarak Atand�");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Girdi�iniz �ifreler Ayn� De�il");
				}
			}
		});
		
		iptalButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}
}