import CoinsType.Coin;
import CoinsType.ILS;
import CoinsType.USD;

public class CoinFactory {
    public static Coin getCoinInstance(Coins coin){
        switch (coin){
            case ILS: return new ILS();
            case USD: return new USD();
            default:return null;
        }

    }
}
