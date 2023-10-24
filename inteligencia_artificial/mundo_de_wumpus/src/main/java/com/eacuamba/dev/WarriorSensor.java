package com.eacuamba.dev;

import com.eacuamba.dev.matrix.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarriorSensor {
    private Position position;

    /**
     * No quadrado contendo o WUMPUS ou nos quadrados diretamente
     * adjacentes (não na diagonal) o agente perceberá um cheiro ruim.
     */
    private WumpusSmell smell;

    /**
     * Nos quadrados diretamente adjacentes a um poço, o agente perceberá
     * uma brisa.
     */
    private WaterWellWind wind;

    /**
     *  No quadrado onde está o ouro, o agente perceberá um resplendor.
     */
    private Gold radiance;

    /**
     * Quando caminhar para uma parede, perceberá um impacto.
     */
    private Boolean impact;

    /**
     * Quando o WUMPUS é morto, ele emite um grito triste que pode ser
     * percebido em qualquer lugar na caverna.
     */
    private Boolean scream;
}
