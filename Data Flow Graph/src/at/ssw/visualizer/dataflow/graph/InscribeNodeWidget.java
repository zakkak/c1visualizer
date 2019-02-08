package at.ssw.visualizer.dataflow.graph;

import at.ssw.visualizer.dataflow.instructions.Instruction;
import java.awt.Color;
import java.awt.Font;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.widget.LabelWidget;

/**
 *
 * @author Stefan Loidl
 */
public class InscribeNodeWidget extends LabelWidget{

    private final static int LEFTINSET=2;
    private final static int TOPINSET=1;


    /** Creates a new instance of InscribeNodeWidget */
    public InscribeNodeWidget(Instruction i, InstructionNodeGraphScene s) {
        super(s);

        if(i.getInstructionType()==Instruction.InstructionType.PARAMETER)
            setLabel(i.getID());
        else{
            setLabel(InstructionNodeWidget.modifiyStringLength(i.getID()+": "+i.getInstructionString(),
                    InstructionNodeWidget.MAXSTRINGLENGTH,InstructionNodeWidget.MAXSTRINGLENGTH,
                    InstructionNodeWidget.ABBREV));
        }

        setFont(Font.decode(InstructionNodeWidget.DEFAULTFONT).deriveFont(Font.BOLD));
        setForeground(InstructionNodeWidget.getLineColor());
        setOpaque(false);
        InstructionNodeWidget nw=s.getNodeWidget(i.getID());
        if(nw!=null){
            setToolTipText(nw.createToolTip());
        }

        Color c=InstructionNodeWidget.getColorByType(i.getInstructionType(),false);
        Color dc=InstructionNodeWidget.getLineColor();
        setBorder(BorderFactory.createRoundedBorder(0,0,LEFTINSET,TOPINSET,c,dc));

    }

}
