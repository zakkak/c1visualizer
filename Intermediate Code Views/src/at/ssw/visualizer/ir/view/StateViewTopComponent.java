package at.ssw.visualizer.ir.view;

import at.ssw.visualizer.ir.IREditorKit;
import at.ssw.visualizer.model.cfg.BasicBlock;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.ir.model.IRTextBuilder;
import at.ssw.visualizer.texteditor.view.AbstractTextViewTopComponent;
import java.io.Serializable;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * TopComponent displaying the state view.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
final class StateViewTopComponent extends AbstractTextViewTopComponent {
    private StateViewTopComponent() {
        super(new IREditorKit());
        setName("State");
        setToolTipText("State of Local Variables and Operand Stack");
    }

    @Override
    protected String getContent(ControlFlowGraph cfg, BasicBlock[] blocks) {
        IRTextBuilder builder = new IRTextBuilder();
        return builder.buildState(cfg, blocks);
    }

    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    private static final String PREFERRED_ID = "StateViewTopComponent";
    private static StateViewTopComponent instance;

    public static synchronized StateViewTopComponent getDefault() {
        if (instance == null) {
            instance = new StateViewTopComponent();
        }
        return instance;
    }

    public static synchronized StateViewTopComponent findInstance() {
        return (StateViewTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    static final class ResolvableHelper implements Serializable {
        private static final long serialVersionUID = 1L;
        public Object readResolve() {
            return StateViewTopComponent.getDefault();
        }
    }
    // </editor-fold>
}
