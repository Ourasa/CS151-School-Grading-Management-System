package project.UI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.painter.AbstractLayoutPainter.HorizontalAlignment;

/**
 *
 * @author nirguberman
 */
public class AutoComplete extends JComboBox {

        /**
         * Creates new form AutoComplete
         */
        String[] options;

        public AutoComplete(String[] options) {
                this.options = options;

                this.setModel(new DefaultComboBoxModel<String>(this.options));
                this.setEditable(true);
                this.setBounds(100, 120, 172, 30);
                AutoCompleteDecorator.decorate(this);
                this.setVisible(true);

        }

		
}