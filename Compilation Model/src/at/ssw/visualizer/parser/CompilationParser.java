package at.ssw.visualizer.parser;

import at.ssw.visualizer.modelimpl.CompilationModelImpl;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Cancellable;

/**
 *
 * @author Christian Wimmer
 */
public class CompilationParser {
    public static String parseInputFile(String fileName, CompilationModelImpl compilationData) {
        ProgressHandle progressHandle = ProgressHandleFactory.createHandle("Parsing input file \"" + fileName + "\"", new CancelParsing());
        Scanner scanner = null;
        try {
            scanner = new Scanner(fileName, progressHandle);
            Parser parser = new Parser(scanner);
            parser.setCompilationModel(compilationData);
            parser.Parse();

            if (parser.hasErrors()) {
                return parser.getErrors();
            } else {
                return null;
            }
        } catch (UserCanceledError ex) {
            // user canceled parsing, so report no error
            return null;
        } catch (Error ex) {
            return ex.getMessage();
        } catch (Throwable ex) {
            // catch everything else that might happen
            return ex.getClass().getName() + (ex.getMessage() != null ? ": " + ex.getMessage() : "");
        } finally {
            progressHandle.finish();
        }
    }

    private static class CancelParsing implements Cancellable {
        public boolean cancel() {
            throw new UserCanceledError();
        }
    }

    private static class UserCanceledError extends Error {
    }
}
