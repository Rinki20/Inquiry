/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finlogic.spring.offer.utils;

import com.finlogic.spring.inquiry.buissness.InquiryEntityBean;
import com.finlogic.spring.offer.model.Offer;
import finutils.errorhandler.FinLogger;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author njuser
 */
public class OfferBuilder {
    private static final String FILE_NAME = "/home/njuser/Desktop/logs/inquiry_logs.txt";
    public static Offer generateOffer(InquiryEntityBean inquiry)
    {  
        FinLogger.logData( "in offerbuilder is" + inquiry.getCarpetarea() , FILE_NAME );
        int basePremium;
        int baseCoverage;
        Offer newOffer = new Offer();
        List<Integer> listPremiumCoverage = coverageOnCarpetArea(inquiry.getCarpetarea());
        
        FinLogger.logData( "in offerbuilder is" + listPremiumCoverage, FILE_NAME );
        
        if(listPremiumCoverage == null)
        {
            FinLogger.logData( " return null" + listPremiumCoverage, FILE_NAME );
            return null;
        }
        if(listPremiumCoverage.size() > 0 )
        {
        
            baseCoverage = listPremiumCoverage.get(0);
            basePremium = listPremiumCoverage.get(1);
            
           // it will count coverage based on Roof And Floor from baseCoverage 
           int updatedCoverage = updateCoverageOnRoofAndFloor(inquiry , baseCoverage); 
           // It will count premium based on Fire Safety And Built Year from basePremium
           int updatedPremium = updatePremiumOnSafetyAndYear(inquiry, basePremium);
           
           
           newOffer.setOfferid(UUID.randomUUID().toString()); 
           newOffer.setCoverage(updatedCoverage);
           newOffer.setPremium(updatedPremium);
          FinLogger.logData( "data is " + newOffer ,  FILE_NAME );
           return newOffer;
        }
        return null;

    }
    //It will generate basepremium and basecoverage from carpetarea
    private static List<Integer> coverageOnCarpetArea(int carpetArea)
    {
        List <Integer> coveragePremiumList = new ArrayList<>();
        
        if(carpetArea >= 30 && carpetArea <=80)
        {
            coveragePremiumList.add(70000);
            coveragePremiumList.add(1000);
            return coveragePremiumList;
            
        }else if(carpetArea >= 81 && carpetArea <=200)
        {
            coveragePremiumList.add(80000);
            coveragePremiumList.add(1200);
            return coveragePremiumList;
            
        }else if(carpetArea >= 201 && carpetArea <=1000)
        {
            coveragePremiumList.add(95000);
            coveragePremiumList.add(1800);
            return coveragePremiumList;
            
        }
        else if(carpetArea>=1001)
        {
            coveragePremiumList.add(125000);
            coveragePremiumList.add(2800);
            return coveragePremiumList;
            
        }
        return null;    
    }
    
    private static int updateCoverageOnRoofAndFloor(InquiryEntityBean inquiry , int baseCoverage)
    {
        int updatedCoverage = baseCoverage;
        
        switch(inquiry.getRoof())
        {
            case RCC:
                updatedCoverage += (0.05 * updatedCoverage);
                break;
            case WOODEN:
                updatedCoverage -= (0.05 * updatedCoverage);
                break;
        }
        switch(inquiry.getFloor())
        {
            case TILE:
                updatedCoverage += (0.05 * updatedCoverage);
                break;
            case WOODEN:
                updatedCoverage -= (0.05 * updatedCoverage);
                break;
        }

        return updatedCoverage;
    }
    
    private static int updatePremiumOnSafetyAndYear(InquiryEntityBean inquiry,int basePremium)
    {
        int updatedPremium = basePremium;
        
        if(inquiry.getSafety() == 0 )
        {
            updatedPremium += (0.2 * updatedPremium);
            
        }else if(inquiry.getSafety() == 1)
        {
            updatedPremium -= (0.1 * updatedPremium);
        }
        
        //To get current year 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        int builtYear = inquiry.getYear();
        int yearDiff = currentYear - builtYear;
        if(yearDiff <= 10)
        {
            updatedPremium -= (0.1 * updatedPremium);
            
        }else if(yearDiff > 10)
        {
            updatedPremium += (0.2 * updatedPremium);
        }
        return updatedPremium;
    }
    public static int getPremium(InquiryEntityBean inquiry,int coverage)
    {
        int premium = LogicOfGetPremium(inquiry.getBasecoverage(),inquiry.getBasepremium(),coverage);
        return premium;
    }
    
    private static int LogicOfGetPremium(int baseCoverage,int basePremium,int newCoverage)
    {
        final double PercentOnCoverage = 0.2;
        double newPremium = basePremium;
        int diffNewAndBaseCoverage;
        double baseCoveragePercentValue = baseCoverage * PercentOnCoverage;
        double percentOfCoverage;
        
        if(baseCoverage > newCoverage)// decrease premium
        {
            diffNewAndBaseCoverage = baseCoverage - newCoverage;
            percentOfCoverage = (diffNewAndBaseCoverage * PercentOnCoverage * 100)/baseCoveragePercentValue;
            newPremium -= (basePremium * percentOfCoverage)/100; 
            
            return (int) round(newPremium);
            
        }else if(baseCoverage < newCoverage) // increase premium
        {
            diffNewAndBaseCoverage = newCoverage - baseCoverage;
            percentOfCoverage = (diffNewAndBaseCoverage * PercentOnCoverage * 100)/baseCoveragePercentValue;
            newPremium += (basePremium * percentOfCoverage)/100;
            
            return (int) round(newPremium);
        }
        return 0;   
    }
    
}
