package myJTree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
	
@SuppressWarnings("serial")
public class TreeCellRenderer extends DefaultTreeCellRenderer {

    public TreeCellRenderer() {
        setBackgroundNonSelectionColor(null);
        setBackground(null);
    }
	
    @Override
    public Color getBackgroundNonSelectionColor() {
        return (null);
    }

    @Override
    public Color getBackground() {
        return (null);
    }
    
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus)
	{
		final Component ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

	    this.setText(value.toString());
	    return ret;
	}
}