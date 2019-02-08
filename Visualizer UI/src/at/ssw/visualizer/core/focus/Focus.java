/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ssw.visualizer.core.focus;

import at.ssw.visualizer.core.selection.SelectionProvider;
import org.openide.windows.TopComponent;

public class Focus {
    public static interface SelectionClosure {
        public boolean matches(TopComponent tc);
    }

    public static boolean findEditor(Class c, Object data) {
        for (TopComponent tc : TopComponent.getRegistry().getOpened()) {
            if (tc.getClass() == c && ((SelectionProvider)tc).getSelection().get(data.getClass()) == data) {
//                                WindowManager wm = WindowManager.getDefault();
//                Mode mode = wm.findMode(tc);
//                if(mode != null && mode != wm.getCurrentMaximizedMode()) {
//                    wm.switchMaximizedMode(null);
//                }
                tc.requestActive();
                return true;
            }
        }
        return false;
    }
}
