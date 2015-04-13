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
import loading.FilterXLS;
import loading.LoadSaveFile;
import controller.ControllerFrame;

public class ReportStep2 {
	
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
	
	public ReportStep2(ControllerFrame control, String[] columns) throws WriteException{
		String path = LoadSaveFile.saveFrame(control.getModel().getMainDelivery().getPathReport(), "Sauvegarder", new FilterXLS(), "xls");
		control.getModel().getMainDelivery().setPathReport(path);
		if (path == null){
			return;
		}
		
		try {
			workbook = Workbook.createWorkbook(new File(path));
			
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
			Label fichier_nom = new Label(2, 4, control.getModel().getMainDelivery().getPathOGC());
			sheet.addCell(fich);
			sheet.addCell(fichier_nom);
			sheet.mergeCells(2, 4, 5, 4);
			
			// Numéro de DCR
			Label DCR = new Label(1, 5, "Numéro(s) de DCR : ", arial10gFormat);
			Label DCR_aff = new Label(2, 5, control.getModel().getMainDelivery().getDCR());
			sheet.addCell(DCR);
			sheet.addCell(DCR_aff);
			//*******************************//
			
			//*****Données*******//
			sheet.mergeCells(1, 8, 2, 8);
						
			// Entêtes
			for (int i = 0; i < columns.length; i++){
				Label col = new Label(i, 8, columns[i], arial12format);
				sheet.addCell(col);
			}
			
			for (int i = 0; i < control.getModel().getMainDelivery().getDataStep2().size(); i++){
				sheet.mergeCells(1, i+9, 2, i+9);
				String s = control.getModel().getMainDelivery().getDataStep2().get(i)[control.getModel().getMainDelivery().getDataStep2().get(i).length];
				WritableCellFormat wf;
				if(s.contains(GestLangue.getLocalizedText(IHM.INDICATEUR_ERR.getLabel()))){
					wf = arial10oFormat;
				}
				else if(!s.isEmpty()){
					wf = arial10rFormat;
				}
				else{
					wf = arial10okFormat;
				}
				
				for (int j = 0; j < control.getModel().getMainDelivery().getDataStep2().get(i).length; j++) {
					Label cell = new Label(j, i + 9, control.getModel().getMainDelivery().getDataStep2().get(i)[j], wf);
					sheet.addCell(cell);
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
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
