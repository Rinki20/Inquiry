

package com.finlogic.spring.inquiry.buissness;

import com.finlogic.spring.offer.model.Offer;
import java.util.List;
import com.finlogic.util.persistence.SQLUtility;
import finutils.errorhandler.FinLogger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author njuser
*/

public class InquiryDataManager {
    
   SQLUtility sQLUtility = new SQLUtility();
   private final String FILE_NAME = "/home/njuser/Desktop/logs/inquiry_logs.txt";
    private final String CONNECTION_ALIAS = "njindiainvest_offline";
    
    //GET QUOTE PAGE CODE-------------------------------------------------------
    // it will fetch state of the INDIA in which each state having city 
    public List getState() throws ClassNotFoundException, SQLException
    {
        String query = "select s.GEO_NAME from GEO_MAST c \n" +
                        "join GEO_MAST s  on c.PAR_GEO_ID = s.GEO_ID\n" +
                        "where s.PAR_GEO_ID = 52\n" +
                        "group by s.geo_id \n" +
                        "order by s.GEO_NAME asc;";
        
       return sQLUtility.getList(CONNECTION_ALIAS,query);
//        List<String> list = new ArrayList<String>();
//        list.add("rinki");
//        list.add("neha");
//        list.add("mayur");
//        return list;
        
    }
    //It will fetch city of the selected state
    public List getCity(String state) throws ClassNotFoundException, SQLException
    {
        System.out.println(state);
        String query ="select GEO_NAME from GEO_MAST\n" +
                        "where PAR_GEO_ID = (select GEO_ID from GEO_MAST\n" +
                        "where GEO_NAME = '" + state + "' AND GEO_LVL_ID=3)\n" +
                        "order by GEO_NAME;";
        System.out.println(query);
        
       return sQLUtility.getList(CONNECTION_ALIAS,query);
      
        
    }
    
    //Insert Statement 
    //Insert Inquiry data into Inquiry_demo table 

    public int createInquiry(InquiryEntityBean inquiryEntityBean) throws ClassNotFoundException,SQLException
    {
        
       String query = "insert into NJCT_INQUIRY_DEMO(inquiryid,firstname,lastname,dob,address,state,city,pincode,carpetarea,roof,\n" +
                       "floor,firesafety,year,offerid) values('"
                    + inquiryEntityBean.getInquiryid() + "','"
                    + inquiryEntityBean.getFirstname() + "','"
                    + inquiryEntityBean.getLastname() + "','"
                    + inquiryEntityBean.getDob()+ "','"
                    + inquiryEntityBean.getAddress() + "','"
                    + inquiryEntityBean.getState() + "','"
                    + inquiryEntityBean.getCity() + "','"
                    + inquiryEntityBean.getPincode() + "',"
                    + inquiryEntityBean.getCarpetarea() + ",'"
                    + inquiryEntityBean.getRoof() + "','"
                    + inquiryEntityBean.getFloor()+ "',"
                    + inquiryEntityBean.getSafety() + ","
                    + inquiryEntityBean.getYear() + ","
                    + inquiryEntityBean.getOfferid() + ");";
       
                    FinLogger.logData("In Create Inquiry data manager \n"+ query , FILE_NAME);
       return sQLUtility.persist(CONNECTION_ALIAS,query);
    }
    //VIEW OFFER PAGE CODE------------------------------------------------------
    //This method will show all data from njct_inquiry_demo
    public List getAllOffers() throws ClassNotFoundException, SQLException
    {
        String query = "select inquiryid,firstname,lastname,dob,address,state,city,pincode,carpetarea,roof,\n" +
                       "floor,firesafety,year,offerid from NJCT_INQUIRY_DEMO";
        
        FinLogger.logData(query, FILE_NAME);
        return sQLUtility.getList(CONNECTION_ALIAS, query);
    }
    //This method will show data from njct_inquiry_demo for specific inquiry id
    public ResultSet  getInquiry(String inquiryid) throws ClassNotFoundException, SQLException
    {
        String query = "select inquiryid,firstname,lastname,dob,address,state,city,pincode,carpetarea,roof,\n" +
                       "floor,firesafety,year,offerid from NJCT_INQUIRY_DEMO where inquiryid ="+"'" + inquiryid +"'";

        FinLogger.logData(query, FILE_NAME);
        return sQLUtility.getResultSet(CONNECTION_ALIAS, query);
    }
    //This method will insert data into njct_offer_demo from selected inquiryid
     public int createOffer(String inquiryid,Offer offer) throws ClassNotFoundException, SQLException
     {
         String query = "insert into NJCT_OFFER_DEMO(offerid,premium,coverage,inquiryid) values "
                 + "('"+offer.getOfferid()+"',"+offer.getPremium()+","+offer.getCoverage()+",'"+inquiryid+"')";
         
        FinLogger.logData(query, FILE_NAME);
        return sQLUtility.persist(CONNECTION_ALIAS, query);
     
     }
     //This method will show data from njct_offer_demo for specific inquiryid
     public List viewOffer(String inquiryid) throws ClassNotFoundException, SQLException
     {
         String query = "select offerid,coverage,premium from NJCT_OFFER_DEMO"
                 + " where offerid =(select offerid from NJCT_INQUIRY_DEMO where inquiryid ="+ "'"+ inquiryid+"')" ;
        
        FinLogger.logData(query, FILE_NAME); 
        return sQLUtility.getList(CONNECTION_ALIAS, query);
     }
     
    //This method will update maxcoverage,minCoverage,basecoverage,basepremium into 
    // njct_inquiry_demo     
     public int AddBaseCoverageAndBasePremium(String inquiryid,Offer offer) throws ClassNotFoundException, SQLException
     {
        int maxcoverage = offer.getCoverage();
        int mincoverage = offer.getCoverage();
        
        maxcoverage += (0.2 * maxcoverage);
        mincoverage -= (0.2 * mincoverage);
        
        String query = "update NJCT_INQUIRY_DEMO " +
                        "set basecoverage ="+offer.getCoverage()+",basepremium ="+offer.getPremium()+",maxcoverage ="
                        +maxcoverage+",mincoverage ="+mincoverage
                       + " where inquiryid ='"+inquiryid+"'"; 
                 
        FinLogger.logData(query, FILE_NAME);
        return sQLUtility.persist(CONNECTION_ALIAS,query);
        
     }
    
     //CHANGE COVERAGE PAGE CODE------------------------------------------------
    
     public List GetInquiryIdOfGeneratedOffer() throws ClassNotFoundException, SQLException
     {
        String query = "select inquiryid from NJCT_INQUIRY_DEMO where offerid is not null";
        FinLogger.logData(query, FILE_NAME);
        return sQLUtility.getList(CONNECTION_ALIAS, query);
     }
     public List LoadCoverageAndPremium(String inquiryid) throws ClassNotFoundException, SQLException
     {
         String query = "select offerid,coverage,premium from NJCT_OFFER_DEMO where inquiryid='"+inquiryid+"'";
         
         FinLogger.logData(query, FILE_NAME);
         return sQLUtility.getList(CONNECTION_ALIAS, query);
     }
     public List LoadFnameLname(String inquiryid) throws ClassNotFoundException, SQLException
     {
         String query = "select firstname,lastname,dob,address,state,city,pincode,carpetarea,roof,\n" +
                       "floor,firesafety,year,maxcoverage,mincoverage,offerid from NJCT_INQUIRY_DEMO where inquiryid='"+inquiryid+"'";
         
         FinLogger.logData(query, FILE_NAME);
         return sQLUtility.getList(CONNECTION_ALIAS, query);
     }
     
     public ResultSet GetPremium(String inquiryid) throws ClassNotFoundException, SQLException
     {
         String query = "select basecoverage,basepremium from NJCT_INQUIRY_DEMO where inquiryid='"+inquiryid+"'";
         FinLogger.logData(query, FILE_NAME);
         return sQLUtility.getResultSet(CONNECTION_ALIAS,query);
     }
     public int OfferUpdate(String inquiryid,String offerid,int coverage,int premium) throws ClassNotFoundException, SQLException
     {
     
         String query = "update NJCT_OFFER_DEMO set coverage="+coverage+",premium="+premium
                 + " where inquiryid='"+inquiryid+"' and offerid='"+offerid+"'";
         FinLogger.logData(query, FILE_NAME);
         return sQLUtility.persist(CONNECTION_ALIAS, query);
     }
//     ----------------------------update the quote-----------------------------

    public int updateInquiry(InquiryEntityBean inquiry, String inquiryid) throws ClassNotFoundException, SQLException {
        
        String query = "Update NJCT_INQUIRY_DEMO "
                + " set carpetarea="+inquiry.getCarpetarea()+","
                + "roof='"+inquiry.getRoof()+"',"
                + "floor='"+inquiry.getFloor()+"',"
                + "firesafety="+inquiry.getSafety()+","
                + "year="+inquiry.getYear()+","
                + "offerid=null" +
                " where inquiryid='"+inquiryid+"'";
                
             FinLogger.logData(query, FILE_NAME);    
                return sQLUtility.persist(CONNECTION_ALIAS, query);
    }
    
    
 //---------------------------------report------------------------------
    
   public List OfferList(String inquiryid) throws ClassNotFoundException, SQLException
   {
       String query = "select offerid,carpetarea,roof,floor,firesafety,year from \n" +
                        " NJCT_QUOTE_LOG_DEMO \n" +
                        "where inquiryid = '"+inquiryid+"'";
       FinLogger.logData(query, FILE_NAME);    
       return sQLUtility.getList(CONNECTION_ALIAS, query);
   }
}
 