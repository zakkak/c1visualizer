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
 * TopComponent displaying the LIR view.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
final class LIRViewTopComponent extends AbstractTextViewTopComponent {
    private LIRViewTopComponent() {
        super(new IREditorKit());
        setName("LIR");
        setToolTipText("Low-level Intermediate Representation");
    }

    @Override
    protected String getContent(ControlFlowGraph cfg, BasicBlock[] blocks) {
        IRTextBuilder builder = new IRTextBuilder();
        return builder.buildLir(cfg, blocks);
    }

    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    private static final String PREFERRED_ID = "LIRViewTopComponent";
    private static LIRViewTopComponent instance;

    public static synchronized LIRViewTopComponent getDefault() {
        if (instance == null) {
            instance = new LIRViewTopComponent();
        }
        return instance;
    }

    public static synchronized LIRViewTopComponent findInstance() {
        return (LIRViewTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
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
            return LIRViewTopComponent.getDefault();
        }
    }
    // </editor-fold>
}
