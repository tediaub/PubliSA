package tutoriel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import affichage.FenReglage;
import affichage.FenetrePrincipale;
import etape.Etape0;
import etape.Etape1;
import etape.Etape2;
import etape.Etape3;
import etape.Etape4;

public class Tutoriel {
	
	private static TutoPanel pTuto;
	private static TutoPanel pTutoReglage;
	
	public static int etape;
	
	private static JLabel info1;
	private static JLabel info2;
	
	public Tutoriel(){
		
		FenetrePrincipale.boolTuto = true;
		pTuto = FenetrePrincipale.pTuto;
		
		new MessDebut();
		
		pTuto.changeTaille(
				Etape0.cbUtilisateur.getX()+ Etape0.utilisateur.getX()+ 8,
				Etape0.cbUtilisateur.getY() + Etape0.utilisateur.getY() + 8,
				Etape0.cbUtilisateur.getWidth(),
				(Etape0.btnewUTI.getY()- Etape0.cbUtilisateur.getY())+ Etape0.btnewUTI.getHeight());
		
		info1 = new JLabel("Choisisez un utilisateur ...");
		info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
		info1.setForeground(Color.WHITE);
		info1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));		
		pTuto.pHaut.add(info1,BorderLayout.SOUTH);
		
		info2 = new JLabel("... ou créé en un nouveau");
		info2.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
		info2.setForeground(Color.WHITE);
		info2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		info2.setHorizontalAlignment(SwingConstants.LEFT);
		pTuto.pBas.add(info2,BorderLayout.NORTH);
		
		pTuto.setVisible(true);
		
		Etape0.setEnabled(false);
		Etape0.cbUtilisateur.setEnabled(true);
		Etape0.btnewUTI.setEnabled(true);
		FenetrePrincipale.getInstance().getContentPane().repaint();
		
		etape = 1;
	}
	
	public static void next(int etap){
		if(etap != -1){
			etape = etap;
		}
		switch(etape){
			case 1 :
				pTuto.changeTaille(
							Etape0.continuer.getX() + Etape0.Livraisons.getX()+ 8,
							Etape0.continuer.getY() + Etape0.Livraisons.getY()+ 8,
							Etape0.continuer.getWidth(),
							Etape0.continuer.getHeight());
			
					Etape0.cbUtilisateur.setEnabled(false);
					Etape0.btnewUTI.setEnabled(false);
					
					info1.setText("Ici, vous pouvez continuer une livraison débuté précedement ...");
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					info2.setVisible(false);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 2 :
				
				pTuto.changeTaille(
						Etape0.nouveau.getX() + Etape0.Livraisons.getX()+ 8,
						Etape0.nouveau.getY() + Etape0.Livraisons.getY()+ 8,
						Etape0.nouveau.getWidth(),
						Etape0.nouveau.getHeight());
		
				Etape0.setEnabled(false);
				Etape0.btOKNew.setEnabled(true);
				Etape0.rbLivThales.setEnabled(true);
				Etape0.rbLivUbik.setEnabled(true);
				Etape0.tfLivraison.setEnabled(true);
				
				info1.setText("Et là, créer une nouvelle livraison");
				info1.setHorizontalTextPosition(SwingConstants.RIGHT);
				info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
				pTuto.pHaut.add(info1,BorderLayout.SOUTH);
				
				FenetrePrincipale.getInstance().getContentPane().repaint();
				
				etape++;
				
				break;
					
			case 3 :pTuto.changeTaille(
							FenetrePrincipale.scrollPane.getX(),
							FenetrePrincipale.scrollPane.getY(),
							FenetrePrincipale.scrollPane.getWidth(),
							FenetrePrincipale.scrollPane.getHeight());
			
					Etape0.setEnabled(true);
					Etape1.setEnabled(false);
					
					info1.setText("<html>Voici l'abre principale de PubliSA.<br><br>Il permet de changer de livraison,<br>de les supprimer<br>ou voir leurs détail grâce à un clic droit.<br><br><br>(Cliquez pour continuer)</html>");
					info1.setHorizontalTextPosition(SwingConstants.LEFT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoGauche.png")));
					pTuto.pGauche.add(info1,BorderLayout.EAST);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 4 :pTuto.changeTaille(
							FenetrePrincipale.getInstance().getWidth()-20,
							FenetrePrincipale.getInstance().getHeight(),
							0,
							0);
					
					FenetrePrincipale.tree.setEnabled(false);
					
					info1.setText("<html>Cette icone indique le mode utilisateur de PubliSA<br>(Multi-Utilisateur ou non)<br><br>(Cliquez pour continuer)</html>");
					info1.setHorizontalTextPosition(SwingConstants.LEFT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.RIGHT);
					pTuto.pGauche.add(info1,BorderLayout.NORTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					etape++;
					
					break;
					
			case 5 :pTuto.changeTaille(
							FenetrePrincipale.getInstance().getWidth()- 125,
							FenetrePrincipale.getInstance().getHeight(),
							0,
							0);
					
					FenetrePrincipale.tree.setEnabled(false);
					
					info1.setText("<html>Ici est marqué le nom de l'utilisateur et de la livraison en cours<br><br>(Cliquez pour continuer)</html>");
					info1.setHorizontalTextPosition(SwingConstants.LEFT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.RIGHT);
					pTuto.pGauche.add(info1,BorderLayout.NORTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					etape++;
					
					break;
			case 6 :pTuto.changeTaille(
							FenetrePrincipale.reglage.getX(),
							FenetrePrincipale.getInstance().getHeight(),
							0,
							0);
					
					FenetrePrincipale.tree.setEnabled(false);
					
					info1.setText("<html>Allons maintenant dans les réglages<br>Réglages > Gestion Email</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTuto.pDroit.add(info1,BorderLayout.NORTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					etape++;
					
					break;
					
			case 7 :pTutoReglage = FenReglage.pTuto;
					pTutoReglage.changeTaille(
							FenReglage.comboBox_1.getX(),
							FenReglage.comboBox_1.getY() + 30,
							FenReglage.comboBox_1.getWidth(),
							FenReglage.comboBox_1.getHeight());
					
					info1.setText("<html>Sélectionner un type de message</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.getContentPane().repaint();
					etape++;
					
					break;
			
			case 8 :FenReglage.comboBox_1.setEnabled(false);
					FenReglage.tfCorrespondantAkkaU.requestFocusInWindow();
			
					pTutoReglage.changeTaille(
							FenReglage.tfCorrespondantAkkaU.getParent().getX() + FenReglage.cards.getX(),
							FenReglage.tfCorrespondantAkkaU.getParent().getY() + FenReglage.cards.getY() + 30,
							FenReglage.tfCorrespondantAkkaU.getParent().getWidth(),
							FenReglage.tfCorrespondantAkkaU.getParent().getHeight());
					
					info1.setText("<html>Ici, vous pouvez changer le correspondant du type de message selectionné</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
					
					break;
					
			case 9 :FenReglage.tfObjetAkkaU.requestFocusInWindow();
					
					pTutoReglage.changeTaille(
							FenReglage.tfObjetAkkaU.getParent().getX() + FenReglage.cards.getX(),
							FenReglage.tfObjetAkkaU.getParent().getY() + FenReglage.cards.getY() + 30,
							FenReglage.tfObjetAkkaU.getParent().getWidth(),
							FenReglage.tfObjetAkkaU.getParent().getHeight());
					
					info1.setText("<html>Ici, vous pouvez changer l'objet du type de message selectionné</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
			
					break;
			
			case 10 :FenReglage.taAkkaU.requestFocusInWindow();
			
					pTutoReglage.changeTaille(
							FenReglage.pMessage.getX() + FenReglage.cards.getX(),
							FenReglage.pMessage.getY() + FenReglage.cards.getY() + 30,
							FenReglage.pMessage.getWidth(),
							FenReglage.pMessage.getHeight());
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.BOTTOM);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pHaut.add(info1,BorderLayout.SOUTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
			
					break;
			
			case 11 :
					pTutoReglage.changeTaille(
							60,
							0,
							128,
							30);
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
			
					break;
					
			case 12 :
					pTutoReglage.changeTaille(
							FenReglage.pUti.getX(),
							FenReglage.pUti.getY()+ 30,
							FenReglage.pUti.getWidth(),
							FenReglage.pUti.getHeight());
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
			
					break;
					
			case 13 :
					pTutoReglage.changeTaille(
							FenReglage.pLiv.getX(),
							FenReglage.pLiv.getY()+ 30,
							FenReglage.pLiv.getWidth(),
							FenReglage.pLiv.getHeight());
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
		
					break;
				
			case 14 :
					pTutoReglage.changeTaille(
							188,
							0,
							100,
							30);
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
	
					break;
					
			case 15 :
					pTutoReglage.changeTaille(
							FenReglage.pEYDT.getX(),
							FenReglage.pEYDT.getY() + 30 ,
							FenReglage.pEYDT.getWidth(),
							FenReglage.pFileCheck.getHeight() + FenReglage.pEYDT.getHeight() + 12);
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setVerticalTextPosition(SwingConstants.TOP);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pBas.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
	
					break;
					
			case 16 :
					pTutoReglage.changeTaille(
							FenReglage.okButton.getX(),
							450,
							0,
							0);
					
					info1.setText("<html>Ici, vous pouvez changer le corps du type de message selectionné.<br>Pour y ajouter les informations fourni par l'application, utilisé les différents marqueurs à disposition</html>");
					info1.setHorizontalTextPosition(SwingConstants.LEFT);
					info1.setVerticalTextPosition(SwingConstants.BOTTOM);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoBas.png")));
					info1.setHorizontalAlignment(SwingConstants.LEFT);
					pTutoReglage.pGauche.add(info1,BorderLayout.NORTH);
					
					pTutoReglage.setVisible(true);
					
					//FenReglage.dialReglage.getContentPane().repaint();
					etape++;
	
					break;
					
			case 17 :Etape1.tfDCR1.requestFocusInWindow();
					pTuto.changeTaille(
							Etape1.pDcr1.getX()+ 8,
							Etape1.pDcr1.getY()+ 8,
							Etape1.pDcr1.getWidth(),
							Etape1.pDcr1.getHeight());
			
					Etape1.setEnabled(true);
					
					info1.setText("Ici, vous pouvez continuer une livraison débuté précedement ...");
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 18 :Etape1.tfStandard.requestFocusInWindow();

					pTuto.changeTaille(
							Etape1.pCalculateur.getX()+ 8,
							Etape1.pCalculateur.getY()+ 8,
							Etape1.pCalculateur.getWidth(),
							Etape1.pCalculateur.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 19 :Etape1.btCreation.requestFocusInWindow();
	
					pTuto.changeTaille(
							Etape1.btCreation.getX()+ 8,
							Etape1.btCreation.getY()+ 8,
							Etape1.btCreation.getWidth(),
							Etape1.btCreation.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 20 :Etape2.tfDCR2.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.pDcr2.getX()+ 8,
							Etape2.pDcr2.getY()+ 8,
							Etape2.pDcr2.getWidth(),
							Etape2.pDcr2.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 21 :Etape2.btParcourir.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.pParcourir.getX()+ 8,
							Etape2.pParcourir.getY()+ 8,
							Etape2.pParcourir.getWidth(),
							Etape2.pParcourir.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 22 :Etape2.btCompteRendu.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.pVerification.getX()+ 8,
							Etape2.pVerification.getY()+ 8,
							Etape2.pVerification.getWidth(),
							Etape2.pVerification.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 23 :Etape2.btsupprEntete.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.btsupprEntete.getX()+ Etape2.pOutil.getX()+ 8,
							Etape2.btsupprEntete.getY()+ Etape2.pOutil.getY()+ 8,
							Etape2.btsupprEntete.getWidth(),
							Etape2.btsupprEntete.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 24 :Etape2.btDOC_EXE.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.btDOC_EXE.getX()+ Etape2.pOutil.getX()+ 8,
							Etape2.btDOC_EXE.getY()+ Etape2.pOutil.getY()+ 8,
							Etape2.btDOC_EXE.getWidth(),
							Etape2.btDOC_EXE.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 25 :Etape2.btEtape2.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape2.btEtape2.getX()+ Etape2.pOutil.getX()+ 8,
							Etape2.btEtape2.getY()+ Etape2.pOutil.getY()+ 8,
							Etape2.btEtape2.getWidth(),
							Etape2.btEtape2.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 26 :Etape3.pCalculateur.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape3.pCalculateur.getX()+ 8,
							Etape3.pCalculateur.getY()+ 8,
							Etape3.pCalculateur.getWidth(),
							Etape3.pCalculateur.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 27 :Etape3.pFichJoint.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape3.pFichJoint.getX()+ 8,
							Etape3.pFichJoint.getY()+ 8,
							Etape3.pFichJoint.getWidth(),
							Etape3.pFichJoint.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 28 :Etape3.btCreation.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape3.btCreation.getX()+ 8,
							Etape3.btCreation.getY()+ 8,
							Etape3.btCreation.getWidth(),
							Etape3.btCreation.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 29 :Etape4.btOGC.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape4.btOGC.getX()+ 8 + Etape4.panel.getX(),
							Etape4.btOGC.getY()+ 8 + Etape4.panel.getY(),
							Etape4.btOGC.getWidth(),
							Etape4.btOGC.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 30 :Etape4.btCSV.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape4.btCSV.getX()+ Etape4.panel.getX()+ 8,
							Etape4.btCSV.getY()+ Etape4.panel.getY()+ 8,
							Etape4.btCSV.getWidth(),
							Etape4.btCSV.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 31 :Etape4.btVerif.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape4.btVerif.getX()+ Etape4.pOutil.getX() + 8,
							Etape4.btVerif.getY()+ Etape4.pOutil.getY() + 8,
							Etape4.btVerif.getWidth(),
							Etape4.btVerif.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 32 :Etape4.panel.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape4.panel.getX()+ 8,
							Etape4.panel.getY()+ 8,
							Etape4.panel.getWidth(),
							Etape4.panel.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 33 :Etape4.btEtape4.requestFocusInWindow();
			
					pTuto.changeTaille(
							Etape4.btEtape4.getX()+ 8,
							Etape4.btEtape4.getY()+ 8,
							Etape4.btEtape4.getWidth(),
							Etape4.btEtape4.getHeight());
			
					Etape0.setEnabled(false);
					Etape0.btOKNew.setEnabled(true);
					Etape0.rbLivThales.setEnabled(true);
					Etape0.rbLivUbik.setEnabled(true);
					Etape0.tfLivraison.setEnabled(true);
					
					info1.setText("Et là, créer une nouvelle livraison");
					info1.setHorizontalTextPosition(SwingConstants.RIGHT);
					info1.setIcon(new ImageIcon(Tutoriel.class.getResource("/image/icones/redoHaut.png")));
					pTuto.pHaut.add(info1,BorderLayout.SOUTH);
					
					FenetrePrincipale.getInstance().getContentPane().repaint();
					
					etape++;
					
					break;
					
			case 34 : System.out.println("FIN!!!!!!!");
		}
	}
}