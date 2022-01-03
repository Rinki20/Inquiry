/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finlogic.spring.inquiry.apps;

import com.finlogic.spring.offer.model.Offer;
import finutils.errorhandler.FinLogger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author njuser
 */
public class InquiryController extends MultiActionController {

    InquiryService inquiryService = new InquiryService();
    private final String FILE_NAME = "/home/njuser/Desktop/logs/inquiry_logs.txt";
    
    public ModelAndView loadHome(HttpServletRequest request,HttpServletResponse Response)
    {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }
    //cmdAction=loadGetQuote
    //It will load List of State from database
    public ModelAndView loadGetQuote(HttpServletRequest request,HttpServletResponse response)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","getQuote");
        try {
            
            modelAndView.addObject("stateList", inquiryService.getState());
            
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        } catch (Exception ex)
        {
           FinLogger.logError(ex, FILE_NAME);
        }
        
        return modelAndView;
    }
//    cmdAction=loadChangeCoverage
    public ModelAndView loadChangeCoverage(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("offer/quote");
        modelAndView.addObject("process","changeCoverage");
        try {
            List inquiryidList = inquiryService.GetInquiryIdOfGeneratedOffer();
            
            
            if(!inquiryidList.isEmpty())
            {
                modelAndView.addObject("status", 1);
                modelAndView.addObject("inquiryidList",inquiryidList);
            }
            else
            {
                modelAndView.addObject("status", 0);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }catch (Exception ex)
        {
           FinLogger.logError(ex, FILE_NAME);
        }
        return modelAndView;
    }
//    cmdAction=loadViewOffer
//    It will give list of inquiry details from database
    public ModelAndView loadViewOffer(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("offer/quote");
        modelAndView.addObject("process","viewOffer");
        
        try {
            List offerList = inquiryService.getAllOffers();
            if(!offerList.isEmpty())
            {
                modelAndView.addObject("status",1);
                modelAndView.addObject("offerList",offerList);
            }
            else
            {
                modelAndView.addObject("status",0);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
           FinLogger.logError(ex, FILE_NAME);
        } catch (Exception ex)
        {
           FinLogger.logError(ex, FILE_NAME);
        }
        return modelAndView;
    }
    //cmdAction=loadUpdateQuote
    public ModelAndView loadUpdateQuote(HttpServletRequest request,HttpServletResponse response)
    { 
        ModelAndView modelAndView = new ModelAndView("offer/quote");
        modelAndView.addObject("process","updateQuote");
        String inquiryid = request.getParameter("inquiryid");
        try {
            
            List inquirylist = inquiryService.getInquiry(inquiryid);
            if(!inquirylist.isEmpty())
            {
                modelAndView.addObject("status", 1);
                modelAndView.addObject("inquirylist",inquirylist);
                modelAndView.addObject("inquiryid", inquiryid);
            }
            else
            {
                modelAndView.addObject("status", 0);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
        
        return modelAndView;
    }
    //GET QUOTE PAGE CODE-------------------------------------------------------
    //cmdAction=loadCity
    //City will be loaded according to selected state
    public ModelAndView loadCity(HttpServletRequest request,HttpServletResponse response)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","loadCity");
            String state = request.getParameter("state");
            modelAndView.addObject("state", state);
        try {
            
            modelAndView.addObject("cityList", inquiryService.getCity(state));
            
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logData(ex.toString(), FILE_NAME);
        } catch (Exception ex)
        {
           FinLogger.logData(ex.toString(), FILE_NAME);
        }
        
        return modelAndView;
    }
 //  VIEW OFFER PAGE CODE-------------------------------------------------------   
    //cmdAction=insertInquiry
//    It will insert form data into database
    public ModelAndView insertInquiry(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
        ModelAndView modelAndView = new ModelAndView("offer/quote");
        modelAndView.addObject("process","Insert");
        FinLogger.logData("In Insert Inquiry", FILE_NAME);
        try {
            modelAndView.addObject("status",inquiryService.createInquiry(inquiryFormBean));
            
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
        
        return modelAndView;
    
    }
    //It will generate offer and insert into database for specific inquiry id
//    cmdAction=generateOffer
    public ModelAndView generateOffer(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
        ModelAndView modelAndView = new ModelAndView("offer/offer");
        modelAndView.addObject("process","generateOffer");
        //log
        FinLogger.logData("In Insert Inquiry", FILE_NAME);
        
        try {
                String inquiryid = request.getParameter("inquiryid");
                //log
                FinLogger.logData("in controller of generate offer", FILE_NAME);

                Offer offer = inquiryService.generateOffer(inquiryid);
                if(offer != null)
                {
                    modelAndView.addObject("status","1");
                    modelAndView.addObject("offer",offer);    
                }
                else
                {
                    modelAndView.addObject("status","0");
                }

            } catch (ClassNotFoundException | SQLException ex) {
                FinLogger.logError(ex, FILE_NAME);
            } catch (Exception ex)
            {
                FinLogger.logError(ex, FILE_NAME);
            }
        
        return modelAndView;
    
    }
    
    
    //this method show the generated offerid from the database;
//    cmdAction=viewOffer
    public ModelAndView viewOffer(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
            ModelAndView modelAndView = new ModelAndView("offer/offer");
            modelAndView.addObject("process","viewOffer");
        try {

                String inquiryid = request.getParameter("inquiryid");
                List offer = inquiryService.viewOffer(inquiryid);
                FinLogger.logData("list in controller" + offer, FILE_NAME);
                
                if (!offer.isEmpty())
                {
                    modelAndView.addObject("status","1");
                    modelAndView.addObject("Offer",offer); 
                }
                else
                {
                    modelAndView.addObject("status","0");
                }
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        } catch (Exception ex)
        {
            FinLogger.logError(ex, FILE_NAME);
        }
        return modelAndView;
    }
    
    // CHANGE THE COVERAGE PAGE CODE--------------------------------------------
    //load coverage and premium 
    public ModelAndView loadCoverageAndPremium(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","viewCoveragePremium");
            String inquiryid = request.getParameter("inquiryid");
        try {
            List coveragePremium = inquiryService.LoadCoverageAndPremium(inquiryid);
            if(!coveragePremium.isEmpty())
            {
                modelAndView.addObject("status", 1);
                modelAndView.addObject("coveragePremium",coveragePremium);
            }
            else
            {
                modelAndView.addObject("status", 0);
            }
            FinLogger.logData("successfully executed", FILE_NAME);
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
            
        return modelAndView;
    }
//    load fname and lname and min max for selected inquiryid
    public ModelAndView loadFnameLname(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","viewFnameLname");
            String inquiryid = request.getParameter("inquiryid");
        try {
            List fnameLnameList = inquiryService.LoadFnameLname(inquiryid);
            if(!fnameLnameList.isEmpty())
            {
                modelAndView.addObject("status", "1");
                modelAndView.addObject("fnameLnameList",fnameLnameList);
            }
            else
            {
                modelAndView.addObject("status", "0");
            }
            FinLogger.logData("successfully executed", FILE_NAME);
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
            
        return modelAndView;
    }
    
    //change Premium
    public ModelAndView changePremium(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","loadPremium");
            String inquiryid = request.getParameter("inquiryid");
            int coverage = Integer.parseInt(request.getParameter("coverage"));
            
        try {
            int newPremium = inquiryService.GetPremium(inquiryid,coverage);
            modelAndView.addObject("premium", newPremium);
            FinLogger.logData("successfully executed"+newPremium, FILE_NAME);
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
        return modelAndView;
    }
    
    //update offer
     public ModelAndView OfferUpdate(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
            ModelAndView modelAndView = new ModelAndView("offer/quote");
            modelAndView.addObject("process","updatedOffer");
            String inquiryid = request.getParameter("inquiryid");
             String offerid = request.getParameter("offerid");
            int coverage = Integer.parseInt(request.getParameter("coverage"));
            int premium = Integer.parseInt(request.getParameter("premium"));
            
        try {
            int status = inquiryService.OfferUpdate(inquiryid,offerid,coverage, premium);
            modelAndView.addObject("status", 1);
            FinLogger.logData("successfully executed"+status, FILE_NAME);
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
        return modelAndView;
    }
    public ModelAndView updateInquiry(HttpServletRequest request,HttpServletResponse response,InquiryFormBean inquiryFormBean)
    {
        ModelAndView modelAndView = new ModelAndView("offer/quote");
        modelAndView.addObject("process","updateInquiry");
        String inquiryid = request.getParameter("inquiryid");
        FinLogger.logData("In update Inquiry", FILE_NAME);
        try {
            int status = inquiryService.updateInquiry(inquiryFormBean,inquiryid);
            if(status > 0)
            {
                modelAndView.addObject("status",1);
            }
            else
            {
                modelAndView.addObject("status",0);
            }
            
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
        
        return modelAndView;
    
    }
    public ModelAndView loadReport(HttpServletRequest request,HttpServletResponse response)
    {
            ModelAndView modelAndView = new ModelAndView("offer/report");
            modelAndView.addObject("process","viewReport");
        try {
            List ReportList = inquiryService.getReport();
            if(!ReportList.isEmpty())
            {
                modelAndView.addObject("status",1);
                modelAndView.addObject("ReportList",ReportList);
            }
            else
            {
                modelAndView.addObject("status",0);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
            

        return modelAndView;
    }
    public ModelAndView loadOfferDetail(HttpServletRequest request,HttpServletResponse response)
    {
            ModelAndView modelAndView = new ModelAndView("offer/report");
            modelAndView.addObject("process","viewOfferList");
             String inquiryid = request.getParameter("inquiryid");
             modelAndView.addObject("modalid",inquiryid);
        try {
            List OfferList = inquiryService.OfferList(inquiryid);
            List generatedOfferList = inquiryService.LoadCoverageAndPremium(inquiryid);
            
            if(!generatedOfferList.isEmpty())
            {
                modelAndView.addObject("result",1);
                modelAndView.addObject("generatedOfferList",generatedOfferList);
                
            }
            else
            {
                modelAndView.addObject("result",0);
            }
            
            if(!OfferList.isEmpty())
            {
                modelAndView.addObject("status",1);
                modelAndView.addObject("OfferList",OfferList);
                
            }
            else
            {
                modelAndView.addObject("status",0);
            } 
        } catch (ClassNotFoundException | SQLException ex) {
            FinLogger.logError(ex, FILE_NAME);
        }
            

        return modelAndView;
    }
    
    
}
