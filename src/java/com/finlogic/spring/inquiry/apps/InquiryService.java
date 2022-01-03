
package com.finlogic.spring.inquiry.apps;


import com.finlogic.spring.inquiry.buissness.InquiryDataManager;
import com.finlogic.spring.inquiry.buissness.InquiryEntityBean;
import com.finlogic.spring.inquiry.constant.FloorType;
import com.finlogic.spring.inquiry.constant.RoofType;
import com.finlogic.spring.offer.model.Offer;
import com.finlogic.spring.offer.utils.OfferBuilder;
import finutils.errorhandler.FinLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * 
 *
 * @author njuser
 */
public class InquiryService {

    
    InquiryDataManager inquiryDataManager = new InquiryDataManager();
    private final String FILE_NAME = "/home/njuser/Desktop/logs/inquiry_logs.txt";
    
    
    // GET QUOTE PAGE CODE------------------------------------------------------
    public List getState() throws ClassNotFoundException , SQLException{

        return inquiryDataManager.getState();
    }
    public List getCity(String state) throws ClassNotFoundException , SQLException{

        return inquiryDataManager.getCity(state);
    }
    
    public int createInquiry(InquiryFormBean inquiryFormBean) throws 
            ClassNotFoundException,SQLException, ParseException
    {
        FinLogger.logData("in service create Inquiry", FILE_NAME);
        return inquiryDataManager.createInquiry(mapFormBeanToEntityBean(inquiryFormBean));
    }
    
    public InquiryEntityBean mapFormBeanToEntityBean(InquiryFormBean inquiryFormBean) throws ParseException
    {   
        InquiryEntityBean inquiryEntityBean = new InquiryEntityBean();
        
        inquiryEntityBean.setInquiryid(UUID.randomUUID().toString());
        inquiryEntityBean.setFirstname(inquiryFormBean.getFirstname());
        inquiryEntityBean.setLastname(inquiryFormBean.getLastname());
        
        //custom logic to covert String date to date type format 
        
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD-MM-YYYY");
//        inquiryEntityBean.setDob(simpleDateFormat.parse(inquiryFormBean.getDob()));

        inquiryEntityBean.setDob(inquiryFormBean.getDob());
        inquiryEntityBean.setAddress(inquiryFormBean.getAddress());
        inquiryEntityBean.setState(inquiryFormBean.getState());
        inquiryEntityBean.setCity(inquiryFormBean.getCity());
        inquiryEntityBean.setPincode(inquiryFormBean.getPincode());
        inquiryEntityBean.setCarpetarea(Integer.parseInt(inquiryFormBean.getCarpetarea()));
        
        //logic to set value of enumeration
        inquiryEntityBean.setRoof(inquiryFormBean.getRoof().equals("WOODEN") ? RoofType.WOODEN : RoofType.RCC);
        
        FinLogger.logData("roof" + inquiryEntityBean.getRoof() + inquiryFormBean.getRoof(), FILE_NAME);
        inquiryEntityBean.setFloor(inquiryFormBean.getFloor().equals("WOODEN") ? FloorType.WOODEN : FloorType.TILE);
        FinLogger.logData("floor" + inquiryEntityBean.getFloor() + inquiryFormBean.getFloor(), FILE_NAME);
        inquiryEntityBean.setSafety(Integer.parseInt(inquiryFormBean.getSafety())); 
       
        inquiryEntityBean.setYear(Integer.parseInt(inquiryFormBean.getBuiltyear()));
        return inquiryEntityBean;
    }      
    //VIEW OFFER PAGE CODE------------------------------------------------------
    
    public List getAllOffers() throws ClassNotFoundException, SQLException
    {
        return inquiryDataManager.getAllOffers();
    }
    
    public Offer generateOffer(String inquiryid) throws ClassNotFoundException, SQLException
    {
        ResultSet inquiryList = inquiryDataManager.getInquiry(inquiryid);
        FinLogger.logData("in service method of generate offer" + inquiryList, FILE_NAME);
//        FinLogger.logData("data"+inquiryList, FILE_NAME);
        InquiryEntityBean offerList = mapResultSetToEntityBean(inquiryList);
        
        FinLogger.logData("data "+offerList, FILE_NAME);
        Offer offer = OfferBuilder.generateOffer(offerList);
        if(offer == null)
        {
            return null;
        }
//          return OfferBuilder.generateOffer(mapResultSetToEntityBean(inquiryList));
        FinLogger.logData("in service method of create offer" + offer, FILE_NAME);
       //Insert offerid,coverage.premium,inquiryid into njct_offer_demo 
        int status = inquiryDataManager.createOffer(inquiryid,offer);
        FinLogger.logData("status of offer insertion "+status,FILE_NAME);
        
        int status1 = inquiryDataManager.AddBaseCoverageAndBasePremium(inquiryid, offer);
        FinLogger.logData("status of inquiry base covearge and premium insertion "+status1,FILE_NAME);
        
        return offer;
    }
    
    public InquiryEntityBean mapResultSetToEntityBean(ResultSet rs) throws SQLException
    {
            InquiryEntityBean inquiryEntityBean = new InquiryEntityBean();
            while(rs.next())
            {
               
                inquiryEntityBean.setInquiryid(rs.getString("inquiryid"));
                inquiryEntityBean.setFirstname(rs.getString("firstname"));
                inquiryEntityBean.setLastname(rs.getString("lastname"));
                inquiryEntityBean.setDob(rs.getString("dob"));
                inquiryEntityBean.setAddress(rs.getString("address"));
                inquiryEntityBean.setState(rs.getString("state"));
                inquiryEntityBean.setCity(rs.getString("city"));
                inquiryEntityBean.setPincode(rs.getString("pincode"));
                inquiryEntityBean.setCarpetarea(rs.getInt("carpetarea"));
                inquiryEntityBean.setRoof(rs.getString("roof").equals("WOODEN")?RoofType.WOODEN:RoofType.RCC);
                inquiryEntityBean.setFloor(rs.getString("floor").equals("WOODEN")?FloorType.WOODEN:FloorType.TILE);
                inquiryEntityBean.setSafety(rs.getInt("firesafety"));
                inquiryEntityBean.setYear(rs.getInt("year"));
                inquiryEntityBean.setOfferid(rs.getString("offerid")); 
               
            }
             FinLogger.logData("inquiryid "+ inquiryEntityBean.getInquiryid(), FILE_NAME);
            return inquiryEntityBean;
    }
    public InquiryEntityBean mapResultSetToEntityBeanForPremium(ResultSet rs) throws SQLException
    {
            InquiryEntityBean inquiryEntityBean = new InquiryEntityBean();
            while(rs.next())
            {
                inquiryEntityBean.setBasecoverage(rs.getInt("basecoverage"));
                inquiryEntityBean.setBasepremium(rs.getInt("basepremium"));
                
            }
            return inquiryEntityBean;
    }
    
    public List viewOffer(String inquiryid) throws ClassNotFoundException, SQLException
    {
        FinLogger.logData("in viewoffer method in service", FILE_NAME);
        return  inquiryDataManager.viewOffer(inquiryid);
    }
    
    
    // CHANGE THE COVERAGE PAGE CODE--------------------------------------------
   
    public List GetInquiryIdOfGeneratedOffer() throws ClassNotFoundException, SQLException
     {
        
        return inquiryDataManager.GetInquiryIdOfGeneratedOffer();
     }
    
    public List LoadCoverageAndPremium(String inquiryid) throws ClassNotFoundException, SQLException
     {
         
         return inquiryDataManager.LoadCoverageAndPremium(inquiryid);
     }
    public List LoadFnameLname(String inquiryid) throws ClassNotFoundException, SQLException
    {
         
         
         FinLogger.logData("in service method of loadfnamelname", FILE_NAME);
         return inquiryDataManager.LoadFnameLname(inquiryid);
    }
    public int GetPremium(String inquiryid,int coverage) throws ClassNotFoundException, SQLException
    {
        ResultSet CoverageAndPremium = inquiryDataManager.GetPremium(inquiryid);
        FinLogger.logData("in service method of getPremium after resultset", FILE_NAME);
        int premium = OfferBuilder.getPremium(mapResultSetToEntityBeanForPremium(CoverageAndPremium),coverage);
        FinLogger.logData("in service method of getPremium", FILE_NAME);
        
        return premium;
    }
    
    public int OfferUpdate(String inquiryid,String offerid,int coverage,int premium) throws ClassNotFoundException, SQLException
    {
     
         return inquiryDataManager.OfferUpdate(inquiryid, offerid, coverage, premium);
    }
    // update the quote
    
    public List getInquiry(String inquiryid) throws ClassNotFoundException, SQLException
    {
       
        return inquiryDataManager.LoadFnameLname(inquiryid);
    }
    public int updateInquiry(InquiryFormBean inquiryFormBean,String inquiryid) throws ParseException, ClassNotFoundException, SQLException
    {
        
        return inquiryDataManager.updateInquiry(mapFormBeanToEntityBean(inquiryFormBean),inquiryid);
    
    }
    public List getReport() throws ClassNotFoundException, SQLException
    {
        
        return inquiryDataManager.getAllOffers();
    
    }
    public List OfferList(String inquiryid) throws ClassNotFoundException, SQLException
   {
       
       return inquiryDataManager.OfferList(inquiryid);
   }
    
 
}

