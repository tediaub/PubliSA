package myJTree;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import langue.GestLangue;
import langue.IHM;
import model.Delivery;
import model.User;

import org.jdom.Element;

import etape.Etape0;
import etape.Etape1;
import etape.Etape2;
import etape.Etape3;
import etape.Etape4;
import affichage.FenetrePrincipale;
import XML.XML;


@SuppressWarnings("serial")
public class AffichageTree extends JTree implements TreeSelectionListener, MouseListener, ActionListener{
	
	private static AffichageTree instance = new AffichageTree();
	
	private static DefaultTreeModel treeModel;
	private JPopupMenu popUp;
	private boolean excep1 = false;
	private JMenuItem supprimer;
	private JMenuItem details;
	private String node;
	private boolean excep2;
	private String nodeParent;
	private static TreePath tp;
	private boolean clikDroit = false;
	
	public static AffichageTree getInstance(){
		return instance;
	}
	
	public AffichageTree(){
		
		addMouseListener(this);
		setCellRenderer(new TreeCellRenderer());
		treeModel = new DefaultTreeModel(new DefaultMutableTreeNode(GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel())));
		setModel(treeModel);
		addTreeSelectionListener(this);
		setOpaque(false);
		
		popUp = new JPopupMenu();
		supprimer = new JMenuItem();
		details = new JMenuItem(GestLangue.getInstance().getLocalizedText(IHM.DETAIL_LIV.getLabel()));
		popUp.add(supprimer);
		popUp.add(details);
		supprimer.addActionListener(this);
		details.addActionListener(this);
	}
	
	public static void miseAjour() {
		treeModel = new DefaultTreeModel(
				new DefaultMutableTreeNode(GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel())) {
					{
						DefaultMutableTreeNode nodeUti;
						ArrayList<Element> utilisateur = new ArrayList<Element>();
						ArrayList<Element> livraison = new ArrayList<Element>();
						
						if(XML.getModeUTI()){
							utilisateur = XML.getUTI();
							for(int i=0; i<utilisateur.size();i++){
								nodeUti = new DefaultMutableTreeNode(utilisateur.get(i).getName());
								livraison = XML.getLivraison(utilisateur.get(i).getName());
								for(int j=0; j<livraison.size();j++){
									nodeUti.add(new DefaultMutableTreeNode(XML.getNomLiv(utilisateur.get(i).getName(),livraison.get(j).getName())));
								}
								add(nodeUti);
							}
						}
						if(!XML.getModeUTI()){
							nodeUti = new DefaultMutableTreeNode(User.getNom());
							livraison = XML.getLivraison(User.getNom());
							for(int j=0; j<livraison.size();j++){
								nodeUti.add(new DefaultMutableTreeNode(XML.getNomLiv(User.getNom(),livraison.get(j).getName())));
							}
							add(nodeUti);
						}
					}
				}
			);
		instance.setModel(treeModel);
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		boolean exception = false;
		
		if(!clikDroit){
			try{
				if(getLastSelectedPathComponent().toString().contentEquals(Delivery.getNom())){
					return;
				}
				else if(getLastSelectedPathComponent().toString().contentEquals(GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()))){
					return;
				}
				
				for(int i=0; i<XML.getLivraison(User.getNom()).size();i++){
					if(getLastSelectedPathComponent().toString().contentEquals(XML.getNomLiv(User.getNom(),XML.getLivraison(User.getNom()).get(i).getName()))){
						exception = true;
					}
				}
				
				for(int i=0; i<XML.getUTI().size();i++){
					if(getLastSelectedPathComponent().toString().contentEquals(XML.getUTI().get(i).getName())){
						return;
					}
				}
			}catch(Exception ex){return;}
			
			int option = JOptionPane.NO_OPTION;
			if (exception){
				option = JOptionPane.showConfirmDialog(null, GestLangue.getInstance().getLocalizedText(IHM.CHANGE_LIV.getLabel()),
					GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			}
			else if(!exception){
				option = JOptionPane.showConfirmDialog(null, GestLangue.getInstance().getLocalizedText(IHM.CHANGE_LIV_UTI.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
			}
			
			//if user want to change livraison
			if(option != JOptionPane.NO_OPTION &&
				option != JOptionPane.CANCEL_OPTION &&
				option != JOptionPane.CLOSED_OPTION){
				
				if (exception){
					new Delivery(User.getNom(), getLastSelectedPathComponent().toString());
				}
				else if(!exception){
					new User(getSelectionPath().getPathComponent(1).toString()); 
					new Delivery(User.getNom(), getLastSelectedPathComponent().toString());
				}
				
				FenetrePrincipale.lblNom.setText(User.getNom() + "  |  " + Delivery.getNom());
				
				CardLayout cl = (CardLayout) FenetrePrincipale.getCardEtape().getLayout();
				switch (Delivery.getEtape())
				{
				case 1:
					cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE1);
					Etape1.miseAjour();
					FenetrePrincipale.getInstance().repaint();
					break;
				case 2:
					cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE2);
					Etape2.miseAjour();
					FenetrePrincipale.getInstance().repaint();
					break;
				case 3:
					cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE3);
					Etape3.miseAjour();
					FenetrePrincipale.getInstance().repaint();
					break;
				case 4:
					cl.show(FenetrePrincipale.getCardEtape(), FenetrePrincipale.ETAPE4);
					Etape4.miseAjour();
					FenetrePrincipale.getInstance().repaint();
					break;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			clikDroit = true;
			
			excep1 = false;
			excep2 = false;
			int positionX= e.getX();
			int positionY= e.getY();
			
			// Récupère le chemin
			try{
				tp = getPathForLocation(positionX,positionY);
				node = tp.getLastPathComponent().toString();
				nodeParent = tp.getPath()[1].toString();
				
				setSelectionPath(getPathForLocation(positionX, positionY));
				
				if(node.contentEquals(GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()))){
					return;
				}
				
				for(int i=0; i<XML.getLivraison(User.getNom()).size();i++){
					if(node.contentEquals(XML.getNomLiv(User.getNom(),XML.getLivraison(User.getNom()).get(i).getName()))){
						excep1 = true;
					}
				}
				
				for(int i=0; i<XML.getUTI().size();i++){
					if(node.contentEquals(XML.getUTI().get(i).getName())){
						excep2 = true;
					}
				}			
			}catch(Exception ex){return;}
			if(excep2){
				supprimer.setText(GestLangue.getInstance().getLocalizedText(IHM.SUPPR_UTI.getLabel()));
				details.setVisible(false);
				popUp.show(this, e.getX(), e.getY());
			}
			else{
				supprimer.setText(GestLangue.getInstance().getLocalizedText(IHM.SUPPR_LIV.getLabel()));
				details.setVisible(true);
				popUp.show(this, e.getX(), e.getY());
			}
	    }
		
		if(e.getButton() == MouseEvent.BUTTON3) {
			clikDroit = false;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == supprimer){
			if(excep1){
				int option = JOptionPane.showConfirmDialog(null, GestLangue.getInstance().getLocalizedText(IHM.SUPPR_LIV_QUESTION.getLabel()) + node + " ?",
						GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					XML.supprLiv(User.getNom(), "L" + node);
					if(node.contentEquals(Delivery.getNom())){
						CardLayout cl = (CardLayout) FenetrePrincipale.getCards().getLayout();
						cl.show(FenetrePrincipale.getCards(), FenetrePrincipale.ETAPE0);
						Etape0.miseAjour();
					}
					else{
						miseAjour();
					}
				}
			}
			else if(excep2){
				int option = JOptionPane.showConfirmDialog(null, GestLangue.getInstance().getLocalizedText(IHM.SUPPR_UTI_QUESTION.getLabel()) + node + " ?",
						GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				
				if(option != JOptionPane.NO_OPTION &&
						option != JOptionPane.CLOSED_OPTION){
					
					if(node.contentEquals(XML.getFirstUti())){
						XML.setFirtUti(null);
					}
					XML.supprUti(node);
					
					if(node.contentEquals(User.getNom())){
						CardLayout cl = (CardLayout) FenetrePrincipale.getCards().getLayout();
						cl.show(FenetrePrincipale.getCards(), FenetrePrincipale.ETAPE0);
						Etape0.miseAjour();
					}
					else{
						miseAjour();
					}
				}
			}
			else{
				JOptionPane.showConfirmDialog(null, GestLangue.getInstance().getLocalizedText(IHM.SUPPR_LIV_IMPOSSIBLE.getLabel()),
						GestLangue.getInstance().getLocalizedText(IHM.NOM_APPLI.getLabel()),
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		
		if(e.getSource() == details){
			new Detail(nodeParent,node);
		}
	}
}