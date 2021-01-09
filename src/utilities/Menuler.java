package utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import arayuz.KategoriDuzenleFE;
import arayuz.KategoriEkleFE;
import arayuz.LoginFE;
import arayuz.MusteriDuzenleFE;
import arayuz.MusteriEkleFE;
import arayuz.PersonelEkleFE;
import arayuz.PersonelIslemleriFE;
import arayuz.SifreIslemleriFE;
import arayuz.UrunDuzenleFE;
import arayuz.UrunEkleFE;
import arayuz.YetkiEkleFE;
import database.AccountsDAL;
import entities.Personel;

public class Menuler
{
	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		
		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("��k��");
		dosyaMenu.add(cikisItem);
		cikisItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		/*�R�NLER MEN�S�*/
		JMenu urunlerMenu = new JMenu("�r�nler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("�r�n Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		
		JMenuItem urunDuzenleItem = new JMenuItem("�r�n D�zenle");
		urunlerMenu.add(urunDuzenleItem);
		urunDuzenleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new UrunDuzenleFE();
			}	
		});
		
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategoriyi Ara");
		urunlerMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriDuzenleFE();
			}
		});
		
		
		/*PERSONEL MEN�S�*/
		JMenu personellerMenu = new JMenu("Personel ��lemleri");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		JMenuItem sifreBelirleItem = new JMenuItem("�ifre/Yetki Belirle");
		personellerMenu.add(sifreBelirleItem);
		personellerMenu.addSeparator();
		
		JMenuItem personelDuzenleItem = new JMenuItem("Personel D�zenle");
		personellerMenu.add(personelDuzenleItem);
		personelDuzenleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PersonelIslemleriFE();
			}
		});
		
		
		/*M��TER� MEN�*/
		JMenu musterilerMenu = new JMenu("M��teriler");
		bar.add(musterilerMenu);
		JMenuItem musteriEkleItem = new JMenuItem("M��teri Ekle");
		musterilerMenu.add(musteriEkleItem);
		
		musteriEkleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						new MusteriEkleFE();
					}
				});
			}
		});
		
		
		musterilerMenu.addSeparator();
		JMenuItem musteriDuzenleItem = new JMenuItem("M��teri D�zenle");
		musterilerMenu.add(musteriDuzenleItem);
		
		musteriDuzenleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new MusteriDuzenleFE();
			}
		});
		
		Personel contract = (Personel) LoginFE.emailBox.getSelectedItem();  
		
		if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==2)
		{
			personellerMenu.hide();
		}
		else if(new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId()==3)
		{
			musterilerMenu.hide();
			personellerMenu.hide();
			urunlerMenu.hide();
		}
		
		
		urunEkleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						new UrunEkleFE();
					}
				});	
			}	
		});
		
		
		kategoriEkleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new KategoriEkleFE();
			}
		});
		
		
		personelEkleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						new PersonelEkleFE();	
					}		
				});
			}
		});
		
		
		yetkiEkleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run()
					{
						new YetkiEkleFE();	
					}
				});
			}
		});
		
		
		sifreBelirleItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new SifreIslemleriFE();	
			}
		});
		return bar;
	}
}