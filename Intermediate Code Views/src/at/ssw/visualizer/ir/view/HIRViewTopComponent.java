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
 * TopComponent displaying the HIR view.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
final class HIRViewTopComponent extends AbstractTextViewTopComponent {
    
    private HIRViewTopComponent() {
        super(new IREditorKit());
        setName("HIR");
        setToolTipText("High-level Intermediate Representation");
    }

    @Override
    protected String getContent(ControlFlowGraph cfg, BasicBlock[] blocks) {
        IRTextBuilder builder = new IRTextBuilder();
        return builder.buildHir(cfg, blocks);
    }

    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    private static final String PREFERRED_ID = "HIRViewTopComponent";
    private static HIRViewTopComponent instance;

    public static synchronized HIRViewTopComponent getDefault() {
        if (instance == null) {
            instance = new HIRViewTopComponent();
        }
        return instance;
    }

    public static synchronized HIRViewTopComponent findInstance() {
        return (HIRViewTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
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
            return HIRViewTopComponent.getDefault();
        }
    }
    // </editor-fold>
}
