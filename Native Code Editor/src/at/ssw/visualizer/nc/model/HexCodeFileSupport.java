package at.ssw.visualizer.nc.model;


public class HexCodeFileSupport {
    public static String decode(String text) {
        return com.oracle.max.hcfdis.HexCodeFileDis.processEmbeddedString(text);
    }
}
