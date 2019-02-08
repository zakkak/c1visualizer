package at.ssw.positionmanager.export;

import at.ssw.positionmanager.LayoutGraph;
import at.ssw.positionmanager.Link;
import at.ssw.positionmanager.Vertex;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Stefan Loidl
 */
public class GMLFileExport implements LayoutGraphExporter{

    private File exportFile;
    private Map<Vertex,String> names;
    Map<Vertex,Color> colors;

    /** Creates a new instance of GMLFileExport */
    public GMLFileExport(File f) {
        exportFile=f;
    }

    public GMLFileExport(File f, Map<Vertex,String> nameList, Map<Vertex,Color> colors){
        exportFile=f;
        names=nameList;
        this.colors=colors;
    }

    public boolean export(LayoutGraph lg) {
        if(lg==null || exportFile==null) return false;
        Hashtable<Vertex,Integer> index=new Hashtable<Vertex,Integer>();
        try{
            FileWriter fw=new FileWriter(exportFile);
            fw.write("Creator \"Graph Visualizer SSW\"\nVersion 1.0");
            fw.write("\ngraph [\n\tdirected 1\n");

            int i=0;
            //export nodes
            for(Vertex v: lg.getVertices()){
                index.put(v,new Integer(i));
                exportVertex(v,i,fw);
                i++;
            }
            //export edges
            for(Link l: lg.getLinks()){
                int from=index.get(l.getFrom().getVertex()).intValue();
                int to=index.get(l.getTo().getVertex()).intValue();
                exportLink(l,from,to,fw);
            }

            fw.write("]");
            fw.close();
        } catch(Exception e){
            return false;
        }
        return true;
    }



    private void exportVertex(Vertex v, int id, FileWriter fw) throws IOException {
        StringBuffer buf=new StringBuffer();
        buf.append("\tnode [\n\t\tid ");
        buf.append(id);
        buf.append("\n\t\tlabel \"");
        if(names!=null){
            buf.append(names.get(v));
        }else buf.append(id);
        buf.append("\"\n\t\tlabelAnchor \"c\"\n\t\tgraphics [\n\t\t\tx ");
        buf.append((double)v.getPosition().x);
        buf.append("\n\t\t\ty ");
        buf.append((double)v.getPosition().y);
        buf.append("\n\t\t\tw ");
        buf.append((double)v.getSize().width);
        buf.append("\n\t\t\th ");
        buf.append((double)v.getSize().height);
        buf.append("\n\t\t\ttype \"rectangle\"\n\t\t\tfill \"#");
        if(colors!=null){
            Color c=colors.get(v);
            if(c==null) c=Color.WHITE;
            buf.append(Integer.toHexString(c.getRed()));
            buf.append(Integer.toHexString(c.getGreen()));
            buf.append(Integer.toHexString(c.getBlue()));
        }
        else buf.append("FFFFFF");
        buf.append("\"");
        buf.append("\n\t\t\toutline \"#000000\"\n\t\t]");
        buf.append("\n\t\tLabelGraphics [\n\t\t\ttype \"text\"\n\t\t\tfill \"#000000\"");
        buf.append("\n\t\t\tanchor \"c\"\n\t\t]\n\t]\n");
        fw.write(buf.toString());
    }

    private void exportLink(Link l, int from, int to, FileWriter fw) throws IOException {
        StringBuffer buf=new StringBuffer();
        buf.append("\tedge [\n\t\tsource ");
        buf.append(from);
        buf.append("\n\t\ttarget ");
        buf.append(to);
        buf.append("\n\t\tgraphics [\n\t\t\ttype \"line\"");
        buf.append("\n\t\t\tarrow \"last\"");
        if(l.getControlPoints()!=null && l.getControlPoints().size()>0){
            buf.append("\n\t\t\tLine [");
            for(Point p:l.getControlPoints()){
                buf.append("\n\t\t\t\tpoint [ x ");
                buf.append((double)p.x);
                buf.append(" y ");
                buf.append((double)p.y);
                buf.append(" ]");
            }
            buf.append("\n\t\t\t]");
        }
        buf.append("\n\t\t]\n\t]\n");
        fw.write(buf.toString());
    }

}
