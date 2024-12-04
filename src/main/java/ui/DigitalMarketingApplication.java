/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;



import java.util.Random;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.MarketModel.Channel;
import model.MarketModel.ChannelCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;

/**
 *
 * @author kal bugrara
 */
public class DigitalMarketingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    // TODO - Digital Marketing
    /*
     * ## Part 1 - Populating the model
     * 
     * 1. Your company sells products to three different markets and uses four
     * channels. You can create your own names of the company, markets and channels.
     * 
     * 2. You sell more than thirty different products, pricing them differently for
     * different markets. You are also using different channels to reach your
     * customers.
     * 
     * 3. Every quarter your advertising agency gives you a breakdown of advertising
     * costs, for each market and by each channel. Link to advertising expense
     * table:
     * (https://northeastern-my.sharepoint.com/:i:/g/personal/
     * a_lelashvili_northeastern_edu/Ea1mOtQvG9pGqrSyVmxA_e4BFZx-LZCVa-nu4XLYW3h5Uw?
     * e=0YozCW)
     * 
     * 4. Autogenerate sales orders and randomly pick the following:
     * - Solution Offer
     * - Market
     * - Channel
     * - Price
     */

 
    // Part 1 - Create the business and load data
    Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Amazon", 50, 30, 100, 200, 20);

    // Create Markets
    Market market1 = new Market("Market 1");
    Market market2 = new Market("Market 2");
    Market market3 = new Market("Market 3");

    // Create ChannelCatalog and Channels
    ChannelCatalog channelCatalog = new ChannelCatalog();

    Channel channel1 = channelCatalog.addChannel();
    channel1.setName("Channel 1");
    Channel channel2 = channelCatalog.addChannel();
    channel2.setName("Channel 2");
    Channel channel3 = channelCatalog.addChannel();
    channel3.setName("Channel 3");
    Channel channel4 = channelCatalog.addChannel();
    channel4.setName("Channel 4");

    // Combine every Market with every Channel
    MarketChannelAssignment[][] marketChannelAssignments = new MarketChannelAssignment[3][4];
    Market[] markets = {market1, market2, market3};
    Channel[] channels = {channel1, channel2, channel3, channel4};

    for (int i = 0; i < markets.length; i++) {
        for (int j = 0; j < channels.length; j++) {
            marketChannelAssignments[i][j] = markets[i].getMarketChannelComb(channels[j]);
        }
    }

    // Create and assign Solution Offers
    SolutionOfferCatalog solutionOfferCatalog = new SolutionOfferCatalog(business);
    Faker faker = new Faker();

    for (int i = 0; i < 30; i++) {
        String productName = faker.commerce().productName();
        int targetPrice = faker.number().numberBetween(20, 100);
        Product product = new Product(productName, targetPrice, targetPrice - 10, targetPrice + 10);

        // Randomly pick a Market-Channel Assignment
        int randomMarketIndex = faker.number().numberBetween(0, markets.length);
        int randomChannelIndex = faker.number().numberBetween(0, channels.length);
        MarketChannelAssignment randomAssignment = marketChannelAssignments[randomMarketIndex][randomChannelIndex];

        SolutionOffer solutionOffer = solutionOfferCatalog.newBundle(randomAssignment, targetPrice, product);
        System.out.println("Created Solution Offer: " + solutionOffer.getBundleName());
    }

    // Advertising Expense Breakdown (assign budgets for every combination)
    for (int i = 0; i < markets.length; i++) {
        for (int j = 0; j < channels.length; j++) {
            marketChannelAssignments[i][j].setAdvertisingBudget(faker.number().numberBetween(5000, 10000));
        }
    }

    // Calculate advertising costs for a single market
    int market1Costs = market2.calculateTotalAdvertisingCosts();
    System.out.println("Market 3 Total Advertising Costs: " + market1Costs);
        
    // Calculate advertising costs for a single channel
    int channel1Costs = channel2.calculateTotalAdvertisingCosts();
    System.out.println("Channel 3 Total Advertising Costs: " + channel1Costs);
    
    // Calculate total advertising costs
    int totalCosts = calculateTotalAdvertisingCostsForAll(markets, channels);
    System.out.println("Total Advertising Costs for All Markets and Channels: " + totalCosts);

    // Generate Sales Orders
    for (int i = 0; i < 25; i++) {
        SolutionOffer randomBundle = solutionOfferCatalog.pickRandomBundle();
        if (randomBundle != null) {
            System.out.println("Generated Order for Bundle: " + randomBundle.getBundleName() +
                    " | Target Price: $" + randomBundle.getTargetPrice());
        }
    }



    /*
     * ## Part 2 – Build reports
     * 
     * 1. Create Market profitability report. This report should show how same
     * product sold under different solution offers performed in different Markets.
     * You should show total sales revenues as well as advertising costs and profit.
     * 
     * 2. Create Channel profitability report. This report should show which channel
     * was easier to sell, required less advertising budget and still sold well.
     * 
     * 3. Advertising Efficiency. This report should do a breakdown of the
     * advertising budget for the solution offers sold in each market sold through
     * each channel. Create a way to allocate Advertising costs and show the results
     * in a table.
     * 
     * 4. Implement a user interaction where the user (with Admin role) can choose
     * from list of reports and see the report.
     * 
     * ## Part 3 - User Interaction
     * 
     * 1. Implement a user interaction through the terminal.
     * 2. The user can choose options from menus and pick its own choices.
     * 3. Implement the following features:
     * - User can identify (or pick) its profile with Market and Channel choices.
     * - User will see an advertisement based on the choices above.
     * - User can see list of available solution offers and prices and can choose
     * one or more to add to a shopping cart.
     * - User can complete the purchase and get order confirmation.
     * 
     * 4. Add any additional features you think will improve user experience
     */



  }

  public static int calculateTotalAdvertisingCostsForAll(Market[] markets, Channel[] channels) {
    int totalCost = 0;

    for (Market market : markets) {
        totalCost += market.calculateTotalAdvertisingCosts();
    }

    for (Channel channel : channels) {
        totalCost += channel.calculateTotalAdvertisingCosts();
    }

    return totalCost;
  }

}
