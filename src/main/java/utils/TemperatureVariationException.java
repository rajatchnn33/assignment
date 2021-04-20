package utils;

import java.math.BigDecimal;

public class TemperatureVariationException extends Exception {
    private BigDecimal variation;

    public TemperatureVariationException(BigDecimal variation) {
        this.variation = variation;
    }

    public String toString() {
        return ("Variation "+variation+ " between two Temperature values does not lie in prescribed limits");
    }
}
