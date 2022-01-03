
package TAE;

import com.finlogic.spring.inquiry.apps.InquiryService;
import com.finlogic.spring.inquiry.buissness.InquiryDataManager;
import com.finlogic.spring.offer.model.Offer;
import com.finlogic.spring.offer.utils.OfferBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author njuser
 */
public class TAE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InquiryService inquiryService = new InquiryService();
        InquiryDataManager inquiryDataManager = new InquiryDataManager();
        try {
            ResultSet inquiryList = inquiryDataManager.getInquiry("8ef4e7f7-ff2e-4c66-8ce5-12f475617587");
//            Offer offer = OfferBuilder.generateOffer(mapResultSetToEntityBean(inquiryList));
            System.out.println(inquiryList);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        
    }

}
