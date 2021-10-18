package sistema;

import java.util.Comparator;

public class CriterioPuntaje implements Comparator <Partida>{

    @Override
    public int compare(Partida p1, Partida p2) {
        return p1.getPuntaje() - p2.getPuntaje();
    }
    
}
