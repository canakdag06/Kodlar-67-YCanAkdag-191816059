package arayuz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.UrunlerDAL;
import entities.Urunler;
import interfaces.ArayuzInterface;

public class UrunDuzenleFE extends JDialog implements ArayuzInterface
{
	public UrunDuzenleFE()
	{
		initPencere();
	}

	@Override
	public void initPencere()
	{
		JPanel panel = initPanel();
		add(panel);
		setTitle("�r�n D�zenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel()
	{
		JPanel panel = new JPanel(new GridLayout(4, 3));
		panel.setBorder(BorderFactory.createTitledBorder("�r�n D�zenle"));
		
		JLabel urunIdLabel = new JLabel("�r�n Id:", JLabel.RIGHT);
		panel.add(urunIdLabel);
		JTextField urunIdField = new JTextField(15);
		panel.add(urunIdField);
		
		JLabel urunAdiLabel = new JLabel("�r�n Ad�: ", JLabel.RIGHT);
		panel.add(urunAdiLabel);
		JTextField urunAdiField = new JTextField(15);
		panel.add(urunAdiField);
		
		JLabel urunFiyatLabel = new JLabel("Fiyat: ", JLabel.RIGHT);
		panel.add(urunFiyatLabel);
		JTextField urunFiyatField = new JTextField(15);
		panel.add(urunFiyatField);
		
		JButton urunGuncelleButton = new JButton("G�ncelle");
		panel.add(urunGuncelleButton);
		
		urunGuncelleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Urunler contract = new Urunler();
				contract.setAd(urunAdiField.getText());
				contract.setId(Integer.parseInt(urunIdField.getText()));
				contract.setFiyat(Float.parseFloat(urunFiyatField.getText()));
				new UrunlerDAL().Update(contract);
				JOptionPane.showMessageDialog(null, "�r�n Bilgileri G�ncellendi");
			}
		});
		
		JButton urunSilButton = new JButton("Sil");
		panel.add(urunSilButton);
		
		urunSilButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Urunler contract = new Urunler();
				contract.setId(Integer.parseInt(urunIdField.getText()));
				new UrunlerDAL().Delete(contract);
				JOptionPane.showMessageDialog(null,"�r�n Silindi");
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