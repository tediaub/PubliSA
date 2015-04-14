package view.guiComponents.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.plaf.basic.ComboPopup;

import view.guiComponents.scrollBar.ButtonScrollBar;


public class ComboBoxFlatUI extends BasicComboBoxUI {

	private Color background = new Color(240,240,240);
	private Color listRollOverBackgroung = new Color(0,63,113);
	private Color buttonPressedBackgroung = new Color(0,119,175);

	protected JButton createArrowButton() {
		ButtonScrollBar button = new ButtonScrollBar(SwingConstants.SOUTH, background, listRollOverBackgroung, buttonPressedBackgroung);
        button.setName("ComboBox.arrowButton");
        return button;
    }
	
    public void paintCurrentValueBackground(Graphics g,Rectangle bounds,boolean hasFocus) {
    	Color t = g.getColor();
    	g.setColor(background);
        g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
        g.setColor(t);
    }
    
    /**
     * Paints the currently selected item.
     */
    @SuppressWarnings("unchecked")
	public void paintCurrentValue(Graphics g,Rectangle bounds,boolean hasFocus) {
        ListCellRenderer renderer = comboBox.getRenderer();
        Component c;

        if ( hasFocus && !isPopupVisible(comboBox) ) {
            c = renderer.getListCellRendererComponent( listBox,
                                                       comboBox.getSelectedItem(),
                                                       -1,
                                                       true,
                                                       false );
            c.setForeground(Color.BLACK);
            
        }
        else {
            c = renderer.getListCellRendererComponent( listBox,
                                                       comboBox.getSelectedItem(),
                                                       -1,
                                                       false,
                                                       false );
        }
        c.setFont(comboBox.getFont());
        c.setBackground(background);
        
        // Fix for 4238829: should lay out the JPanel.
        boolean shouldValidate = false;
        if (c instanceof JPanel)  {
            shouldValidate = true;
        }
        
        int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
        if (padding != null) {
            x = bounds.x + padding.left;
            y = bounds.y + padding.top;
            w = bounds.width - (padding.left + padding.right);
            h = bounds.height - (padding.top + padding.bottom);
        }
        
        currentValuePane.paintComponent(g,c,comboBox,x,y,w,h,shouldValidate);
    }
    
    /**
     * Installs the default colors, default font, default renderer, and default
     * editor into the JComboBox.
     */
    protected void installDefaults() {
        LookAndFeel.installColorsAndFont( comboBox,
                                          "ComboBox.background",
                                          "ComboBox.foreground",
                                          "ComboBox.font" );
        LookAndFeel.installProperty( comboBox, "opaque", Boolean.TRUE);

        padding = UIManager.getInsets("ComboBox.padding");
    }
    
    /**
     * Creates the popup portion of the combo box.
     *
     * @return an instance of <code>ComboPopup</code>
     * @see ComboPopup
     */
    protected ComboPopup createPopup() {
        return new PopUpFlat( comboBox );
    }
    
    @SuppressWarnings("serial")
	class PopUpFlat extends BasicComboPopup{
    	    	
    	@SuppressWarnings("unchecked")
		public PopUpFlat(JComboBox combo) {
			super(combo);
			setUI(new BasicPopupMenuUI());
		}

		/**
         * Configures the popup portion of the combo box. This method is called
         * when the UI class is created.
         */
        protected void configurePopup() {
            setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
            setOpaque(false);
    		setBorderPainted(false);
    		setBackground(Color.WHITE);
            add( scroller );
            setDoubleBuffered( true );
            setFocusable( false );
        }
        
        @SuppressWarnings("unchecked")
		protected void configureList() {
            list.setFont( comboBox.getFont() );
            list.setForeground( Color.BLACK );
            list.setBackground( background );
            list.setSelectionForeground( Color.WHITE );
            list.setSelectionBackground( listRollOverBackgroung );
            list.setBorder( null );
            list.setCellRenderer( comboBox.getRenderer() );
            list.setFocusable( false );
            list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            setListSelection( comboBox.getSelectedIndex() );
            installListListeners();
        }
        
        /**
         * Sets the list selection index to the selectedIndex. This
         * method is used to synchronize the list selection with the
         * combo box selection.
         *
         * @param selectedIndex the index to set the list
         */
        private void setListSelection(int selectedIndex) {
            if ( selectedIndex == -1 ) {
                list.clearSelection();
            }
            else {
                list.setSelectedIndex( selectedIndex );
                list.ensureIndexIsVisible( selectedIndex );
            }
        }
    }
}
