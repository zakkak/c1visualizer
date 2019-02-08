package at.ssw.visualizer.bc.view;

import at.ssw.visualizer.bc.BCEditorKit;
import at.ssw.visualizer.bc.model.BCTextBuilder;
import at.ssw.visualizer.model.cfg.BasicBlock;
import at.ssw.visualizer.model.cfg.ControlFlowGraph;
import at.ssw.visualizer.texteditor.view.AbstractTextViewTopComponent;
import java.io.Serializable;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays the bytecode for a selected block.
 *
 * @author Alexander Reder
 * @author Christian Wimmer
 */
final class BCViewTopComponent extends AbstractTextViewTopComponent {

    private BCViewTopComponent() {
        super(new BCEditorKit());
        setName("Bytecodes");
        setToolTipText("Bytecodes");
    }
    
    @Override
    protected String getContent(ControlFlowGraph cfg, BasicBlock[] blocks) {
        BCTextBuilder builder = new BCTextBuilder();
        return builder.buildView(cfg, blocks);
    }

    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    private static final String PREFERRED_ID = "BCViewTopComponent";
    private static BCViewTopComponent instance;

    public static synchronized BCViewTopComponent getDefault() {
        if (instance == null) {
            instance = new BCViewTopComponent();
        }
        return instance;
    }

    public static synchronized BCViewTopComponent findInstance() {
        return (BCViewTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
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
            return BCViewTopComponent.getDefault();
        }
    }
    // </editor-fold>
}
