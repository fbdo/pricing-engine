package groovy

/**
 * Created by fabio on 28/09/14.
 */
public class PriceCalculator {
    public BigDecimal run(attrs) {
        if (attrs['amount'] < 100.00)  2.50
        else if (attrs['amount'] > 100.00 && attrs['amount'] < 1000.00) 1.00 + attrs['amount'] * 0.01
        else attrs['amount'] * 0.005
    }
}