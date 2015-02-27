package verification;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import langue.GestLangue;
import langue.IHM;
import sauvergarde_chargement.Filtre_XLS;
import sauvergarde_chargement.SauvegardeRapport;
import view.guiComponents.table.TableEtape2;
import etape.Etape2;

public class CreationRapportEtape2 {
	
	//Classeur Excel
	private WritableWorkbook workbook;
	//Feuille Excel
	private WritableSheet sheet;
	//Titre feuille
	private WritableFont arial20font = new WritableFont(WritableFont.ARIAL, 18,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE ,Colour.BLUE, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial20format = new WritableCellFormat(arial20font);
	//Titre colonne
	private WritableFont arial12font = new WritableFont(WritableFont.ARIAL, 12,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial12format = new WritableCellFormat(arial12font);
	//Information générale
	private WritableFont arial10green = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,Colour.GREEN, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial10gFormat = new WritableCellFormat(arial10green);
	//Ligne erreur
	private WritableFont arial10red = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,Colour.RED, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial10rFormat = new WritableCellFormat(arial10red);
	//Ligne ok
	private WritableFont arial10ok = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.GREEN, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial10okFormat = new WritableCellFormat(arial10ok);
	//Ligne warning
	private WritableFont arial10orange = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD, true, UnderlineStyle.NO_UNDERLINE,Colour.LIGHT_ORANGE, ScriptStyle.NORMAL_SCRIPT); 
	private WritableCellFormat arial10oFormat = new WritableCellFormat(arial10orange);
	
	public CreationRapportEtape2() throws WriteException{
		SauvegardeRapport sr = new SauvegardeRapport();
		File fichier = sr.SauvegardeRap(new Filtre_XLS());
		
		if (fichier.equals(null)){
			return;
		}
		
		try {
			workbook = Workbook.createWorkbook(fichier);
			
			sheet = workbook.createSheet("Analyse vérification", 0);
			
			arial20format.setAlignment(Alignment.CENTRE);
			arial12format.setAlignment(Alignment.CENTRE);
			arial10rFormat.setAlignment(Alignment.CENTRE);
			arial10okFormat.setAlignment(Alignment.CENTRE);
			arial10oFormat.setAlignment(Alignment.CENTRE);
			arial10rFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			arial12format.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			arial10okFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			arial10oFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			//****Information générales*****//
			// Titre
			Label infos = new Label(0, 0, "Vérification des fichiers AKKA pour UBIK", arial20format); 
			sheet.addCell(infos);
			sheet.mergeCells(0, 0, 5, 0);
			
			// Date de l'analyse
			Date date_actuelle = new Date(); 
			SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); 
			String dateFormatee = formatDateJour.format(date_actuelle); 
			Label date = new Label(1, 2,"Date de l'analyse : ", arial10gFormat);
			Label date_valeur = new Label(2, 2, dateFormatee);
			sheet.addCell(date);
			sheet.addCell(date_valeur);
			
			// Nom du fichier
			Label fich = new Label(1, 4, "Fichier OGC : ", arial10gFormat);
			Label fichier_nom = new Label(2, 4, Etape2.getOGC());
			sheet.addCell(fich);
			sheet.addCell(fichier_nom);
			sheet.mergeCells(2, 4, 5, 4);
			
			// Numéro de DCR
			Label DCR = new Label(1, 5, "Numéro(s) de DCR : ", arial10gFormat);
			Label DCR_aff = new Label(2, 5, Etape2.getDCR());
			sheet.addCell(DCR);
			sheet.addCell(DCR_aff);
			//*******************************//
			
			//*****Données*******//
			sheet.mergeCells(1, 8, 2, 8);
			int k = 0;
			
			// Entêtes
			for (int i = 0; i<TableEtape2.getTable().getColumnCount()+1; i++){
				if(i==2){i = 3;}
				Label col = new Label(i, 8, TableEtape2.getTable().getColumnName(k), arial12format);
				sheet.addCell(col);
				k++;
			}
			
			//Corps des données
			for (int i = 0; i<TableEtape2.getTable().getRowCount(); i++){
				sheet.mergeCells(1, i+9, 2, i+9);
				k = 0;
				for (int j = 0; j<TableEtape2.getTable().getColumnCount()+1; j++){
					if(j==2) {j = 3;}
					Label cell = new Label(j, i + 9, TableEtape2.getTable().getValueAt(i, k).toString(), arial10okFormat);
					sheet.addCell(cell);
					if(j==5 && !cell.getContents().isEmpty()){
						for(int colonne = 0; colonne<6; colonne ++){
							if(colonne==2) {colonne = 3;}
							if(cell.getContents().contains(GestLangue.getInstance().getLocalizedText(IHM.INDICATEUR_ERR.getLabel()))){
								Label cell2 = new Label(colonne, i + 9, sheet.getCell(colonne, i+9).getContents(), arial10oFormat);
								sheet.addCell(cell2);
							}else{
								Label cell2 = new Label(colonne, i + 9, sheet.getCell(colonne, i+9).getContents(), arial10rFormat);
								sheet.addCell(cell2);
							}
							
						}
					}
					k++;
				}
			}
			
			sheet.setColumnView(0,6);
			sheet.setColumnView(1,20);
			sheet.setColumnView(2,10);
			sheet.setColumnView(3,30);
			sheet.setColumnView(4,9);
			sheet.setColumnView(5,82);
			
			sheet.getSettings().setShowGridLines(false);
			
			workbook.write();
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
