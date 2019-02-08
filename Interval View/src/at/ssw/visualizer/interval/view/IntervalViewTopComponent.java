package at.ssw.visualizer.interval.view;

import at.ssw.visualizer.core.selection.Selection;
import at.ssw.visualizer.core.selection.SelectionManager;
import at.ssw.visualizer.model.interval.ChildInterval;
import at.ssw.visualizer.model.interval.Interval;
import at.ssw.visualizer.model.interval.IntervalList;
import java.awt.BorderLayout;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTreeTable;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * TopComponent showing the interval TreeTable.
 *
 * @author Bernhard Stiftner
 * @author Christian Wimmer
 */
final class IntervalViewTopComponent extends TopComponent {
    private IntervalList curIntervalList;
    private ChildInterval curChild;

    private JXTreeTable intervalTable;
    private IntervalTableModel tableModel;
    private boolean selectionUpdating;

    private IntervalViewTopComponent() {
        setName("Intervals");
        setToolTipText("List of Intervals");

        tableModel = new IntervalTableModel();
        intervalTable = new JXTreeTable(tableModel);
        intervalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        intervalTable.setRootVisible(false);
        intervalTable.setShowsRootHandles(true);
        intervalTable.setClosedIcon(null);
        intervalTable.setLeafIcon(null);
        intervalTable.setOpenIcon(null);
        intervalTable.putClientProperty("JTree.lineStyle", "None");
        intervalTable.setRowMargin(0);
        intervalTable.getColumnModel().setColumnMargin(0);
        intervalTable.setShowGrid(false);
        intervalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < IntervalTableModel.COLUMN_WIDTHS.length; i++) {
            intervalTable.getColumnModel().getColumn(i).setPreferredWidth(IntervalTableModel.COLUMN_WIDTHS[i]);
        }
        intervalTable.getTreeSelectionModel().addTreeSelectionListener(treeSelectionListener);

        JScrollPane scrollPane = new JScrollPane(intervalTable);
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        setLayout(new BorderLayout());
        add(scrollPane);
    }

    @Override
    protected void componentShowing() {
        super.componentShowing();
        SelectionManager.getDefault().addChangeListener(selectionChangeListener);
        updateContent();
    }

    @Override
    protected void componentHidden() {
        super.componentHidden();
        SelectionManager.getDefault().removeChangeListener(selectionChangeListener);
        selectionUpdating = true;
        curIntervalList = null;
        curChild = null;
        tableModel.setIntervals(null);
        selectionUpdating = false;
    }


    private ChangeListener selectionChangeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent event) {
            updateContent();
        }
    };

    protected void updateContent() {
        if (selectionUpdating) {
            return;
        }
        selectionUpdating = true;
        Selection selection = SelectionManager.getDefault().getCurSelection();
        IntervalList newIntervalList = selection.get(IntervalList.class);
        ChildInterval newChild = selection.get(ChildInterval.class);

        if (curIntervalList != newIntervalList) {
            // This resets a user-defined sorting.
            intervalTable.setAutoCreateRowSorter(true);
            tableModel.setIntervals(newIntervalList);
            curChild = null;
        }

        if (newChild != null && curChild != newChild) {
            TreePath selPath = new TreePath(new Object[]{tableModel.getRoot(), newChild.getParent(), newChild});
            intervalTable.expandPath(selPath.getParentPath());
            intervalTable.getTreeSelectionModel().setSelectionPath(selPath);
            intervalTable.scrollPathToVisible(selPath);
        }
        
        curIntervalList = newIntervalList;
        curChild = newChild;
        selectionUpdating = false;
    }
    
    
    private TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
        public void valueChanged(TreeSelectionEvent event) {
            updateSelection();
        }
    };
    
    private void updateSelection() {
        if (selectionUpdating) {
            return;
        }
        selectionUpdating = true;

        if (intervalTable.getTreeSelectionModel().getSelectionCount() > 0) {
            TreePath selPath = intervalTable.getTreeSelectionModel().getSelectionPath();
            Object element = selPath.getLastPathComponent();

            if (element instanceof Interval) {
                curChild = ((Interval) element).getChildren().get(0);
            } else if (element instanceof ChildInterval) {
                curChild = (ChildInterval) element;
            }
            Selection selection = SelectionManager.getDefault().getCurSelection();
            selection.put(curChild);
        }
        selectionUpdating = false;
    }

    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    
    // <editor-fold defaultstate="collapsed" desc=" Singleton and Persistence Code ">
    private static final String PREFERRED_ID = "IntervalViewTopComponent";
    private static IntervalViewTopComponent instance;

    public static synchronized IntervalViewTopComponent getDefault() {
        if (instance == null) {
            instance = new IntervalViewTopComponent();
        }
        return instance;
    }

    public static synchronized IntervalViewTopComponent findInstance() {
        return (IntervalViewTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
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
            return IntervalViewTopComponent.getDefault();
        }
    }
    // </editor-fold>
}
