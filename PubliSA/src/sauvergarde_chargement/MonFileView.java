package sauvergarde_chargement;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
 
public class MonFileView extends FileView {
  
	public Icon getIcon(File f) {
		Icon icon = null; 
		
		icon = FileSystemView.getFileSystemView().getSystemIcon(f); 
		
		return icon;
	}
}
