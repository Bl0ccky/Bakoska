// License: GPL. For details, see Readme.txt file.
package App;

import org.openstreetmap.gui.jmapviewer.tilesources.AbstractOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;


public class MyTileSource extends OsmTileSource {


    public static class Mapnik1 extends AbstractOsmTileSource {

        private static final String PATTERN = "https://%s.tile.openstreetmap.de";

        private static final String[] SERVER = {"a", "b", "c"};

        private int serverNum;


        public Mapnik1() {
            super("OpenStreetMap MyTileSource", PATTERN, "standard");
            modTileFeatures = true;
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, SERVER[serverNum]);
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }
    }    
}
