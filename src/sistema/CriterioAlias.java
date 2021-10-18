package sistema;

import java.util.Comparator;

public class CriterioAlias  implements Comparator <Partida>{

    @Override
    public int compare(Partida p1, Partida p2) {
        return p2.getJugador().getAlias().compareTo(p1.getJugador().getAlias());
    }
    
}
