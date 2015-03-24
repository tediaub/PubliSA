package loading;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class FilePreview extends JPanel implements PropertyChangeListener{
	private JFileChooser chooser;
	private JLabel nom, taille, date, icone, description;
	
	public FilePreview(JFileChooser chooser){
		super(new GridLayout(0,1));	
		
		add(icone = new JLabel("Icône du fichier"));
		add(description = new JLabel("Description du fichier"));
		add(nom = new JLabel("Nom du fichier"));
		add(taille = new JLabel("Taille du fichier"));
		add(date = new JLabel("Dernière mod. du fichier"));
		
		this.chooser = chooser;
		this.chooser.addPropertyChangeListener(this);
		this.chooser.setFileView(new MonFileView());
		setBorder(new TitledBorder("Preview"));
	}
	
	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		
		if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)){
			
			File file = (File) e.getNewValue();
			if(file == null){
				clear();
				return;	
			}
			
			FileSystemView vueSysteme = FileSystemView.getFileSystemView(); 
			Locale locale = Locale.getDefault();
			NumberFormat nf = NumberFormat.getInstance(locale);
			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
			
			icone.setIcon(vueSysteme.getSystemIcon(file));
			
			nom.setText(vueSysteme.getSystemDisplayName(file));
			
			description.setText(vueSysteme.getSystemTypeDescription(file));
			
			String tailleFile = nf.format(file.length()/1024.0)+" Kb";
			taille.setText(tailleFile);
			
			String dateFile = dateFormat.format(new Date(file.lastModified()));
			date.setText("Dernière mod : "+dateFile);
			
		}else{
			clear();	
		}
	}
	private void clear(){
		icone.setIcon(null);
		nom.setText("Nom du fichier");	
		description.setText("Description fichier");
		taille.setText("Taille du fichier");	
		date.setText("Dernière mod. du fichier");
	}
	//Test
	public static void main(String[] args){
		JFileChooser chooser = new JFileChooser(".");
		chooser.setAccessory(new FilePreview(chooser));
		chooser.showOpenDialog(null);
	}
}