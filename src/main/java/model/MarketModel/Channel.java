/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Channel {
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  ArrayList<MarketChannelAssignment> marketChannelCombinations;

  public Channel() {
    marketChannelCombinations = new ArrayList<MarketChannelAssignment>();
  }

  public void addmarketChannelCombs(MarketChannelAssignment mca) {
    marketChannelCombinations.add(mca);
  }

  public int calculateTotalAdvertisingCosts() {
    int totalCost = 0;
    if (marketChannelCombinations != null) {
        for (MarketChannelAssignment mca : marketChannelCombinations) {
            totalCost += mca.getAdvertisingBudget(); // 从 MarketChannelAssignment 获取广告成本
        }
    }
    return totalCost;
  }

}