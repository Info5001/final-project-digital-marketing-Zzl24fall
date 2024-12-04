package model.MarketModel;

import java.util.ArrayList;

import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class Market {
    ArrayList<SolutionOffer> so;
    ArrayList<MarketChannelAssignment> marketChannelCombs;
    ArrayList<String> characteristics;
    ArrayList<Market> submarkets;
    int size;

    public Market(String s) {
        characteristics = new ArrayList<>();
        characteristics.add(s);
        marketChannelCombs = new ArrayList<>(); // Initialize the marketChannelCombs list
    }

    public MarketChannelAssignment getMarketChannelComb(Channel c) {
        for (MarketChannelAssignment mca : marketChannelCombs) {
            if (mca.getChannel().equals(c)) {
                return mca;
            }
        }
        MarketChannelAssignment newMca = new MarketChannelAssignment(this, c);
        marketChannelCombs.add(newMca);
        return newMca;
    }
}
