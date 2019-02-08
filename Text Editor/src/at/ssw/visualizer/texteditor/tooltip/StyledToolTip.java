package at.ssw.visualizer.texteditor.tooltip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.text.EditorKit;

/**
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 * @author Alexander Reder
 */
public class StyledToolTip extends JPanel {

    private final String text;
    private final JEditorPane toolTipPane;

    public StyledToolTip(String text, EditorKit editorKit) {
        this.text = text;

        setBackground(new Color(235, 235, 163));
        setBorder(new LineBorder(Color.BLACK));
        setOpaque(true);

        toolTipPane = new JEditorPane();
        toolTipPane.putClientProperty("document-view-accurate-span", true);
        toolTipPane.setEditorKit(editorKit);
        toolTipPane.setText(text);
        
        setLayout(new BorderLayout());
        add(toolTipPane, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Workaround: Add a new line at the end so that the caret is not in the visible area.
        toolTipPane.setText(text + "\n");
        super.paintComponent(g);
    }
}
