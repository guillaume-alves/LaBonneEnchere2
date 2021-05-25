package fr.eni.bll;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import fr.eni.bo.Bid;
import fr.eni.dao.DAOException;
import fr.eni.dao.EnchereDAO;

public final class BidManager {
    private static final String FIELD_BID_PRICE  				= "bidPrice";
    private static final String FIELD_ARTICLE_END_PRICE  		= "articleEndPrice";
    private static final String FIELD_USER_ID	 				= "userId";
    private static final String FIELD_ARTICLE_ID				= "articleId";

    private String result;
    
    private EnchereDAO enchereDAO;
    
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public BidManager(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

	public Bid insertBid(HttpServletRequest request) throws Exception {
		Integer userId = Integer.valueOf(getFieldValue(request, FIELD_USER_ID));
		Integer articleId = Integer.valueOf(getFieldValue(request, FIELD_ARTICLE_ID));
		String bidPrice = getFieldValue(request, FIELD_BID_PRICE);
		Integer bidPriceInt = null;
		String articleEndPrice = getFieldValue(request, FIELD_ARTICLE_END_PRICE);

        Bid bid = new Bid();
        
        try {
        	bid.setBidUserId(userId);
        	bid.setBidArticleId(articleId);
            processBidPrice(bidPrice, bidPriceInt, articleEndPrice, bid);
            
            if (errors.isEmpty()) {
            	enchereDAO.insertBid(bid);
                result = "Making bid succeed.";
            } else {
                result = "Making bid failed.";
            }
        } catch (DAOException e) {
            result = "Making bid failed !";
            e.printStackTrace();
		}

        return bid;
    }
    
    private void processBidPrice(String bidPrice, Integer bidPriceInt, String articleEndPrice, Bid bid) {
        try {
            bidPriceInt = bidPriceValidation(bidPrice, articleEndPrice);
        } catch (FormValidationException e) {
            setError(FIELD_BID_PRICE, e.getMessage());
        }
        bid.setBidPrice(bidPriceInt);
    }

	private Integer bidPriceValidation(String bidPrice, String articleEndPrice) throws FormValidationException {
    if (bidPrice == null) {
            throw new FormValidationException("Required field.");
    } else {
    	Integer articleEndPriceInt = Integer.valueOf(articleEndPrice);
    	Integer bidPriceInt = Integer.valueOf(bidPrice);
    	if (bidPriceInt<=articleEndPriceInt) {
    		throw new FormValidationException("Bid price must be superior to the actual price.");
    	}
    	else return bidPriceInt;
    	}
	}

    private void setError(String field, String message) {
        errors.put(field, message);
    }

    private static String getFieldValue (HttpServletRequest request, String field) {
 	   String value = request.getParameter(field);
 	   if (value == null) {value = null;}
 	   else if (value.trim().compareTo("") == 0) {value = null;}
        return value;
    }
}