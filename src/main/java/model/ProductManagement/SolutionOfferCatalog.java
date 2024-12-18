/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    Business business;
    ArrayList<SolutionOffer> solutionoffers;

    public SolutionOfferCatalog(Business b) {
        business = b;
        solutionoffers = new ArrayList<SolutionOffer>();
    }

    public SolutionOffer newBundle(MarketChannelAssignment m, int tp, Product p) {
        SolutionOffer bundle = new SolutionOffer(m, tp, p);
        solutionoffers.add(bundle);
        return bundle;
    }

    public SolutionOffer pickRandomBundle() {
        if (solutionoffers == null || solutionoffers.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(solutionoffers.size());
        return solutionoffers.get(index); 
    }

}