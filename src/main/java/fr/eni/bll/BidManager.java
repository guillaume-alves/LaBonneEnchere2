package fr.eni.bll;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import fr.eni.dao.DAOException;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.Bid;

public final class BidManager {
    private static final String FIELD_BID_PRICE  = "bidPrice";

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

	public Bid registerBid(HttpServletRequest request) throws Exception {
		Integer bidPrice = Integer.parseInt(getFieldValue(request, FIELD_BID_PRICE));

        Bid bid = new Bid();
        
        try {
            processBidPrice(bidPrice, bid);
            
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
    
    private void processBidPrice(Integer bidPrice, Bid bid) {
        try {
            bidPriceValidation(bidPrice);
        } catch (FormValidationException e) {
            setError(FIELD_BID_PRICE, e.getMessage());
        }
        bid.setBidPrice(bidPrice);
    }
    
    private void bidPriceValidation(Integer bidPrice) throws FormValidationException {
    	if (bidPrice == null) {
    		throw new FormValidationException("Champ requis.");
	        } else if (bidPrice <= 0) {
	        	throw new FormValidationException("L'ench�re doit �tre sup�rieure � 0.");
	        	}
    }

    private void setError(String field, String message) {
        errors.put(field, message);
    }

    private static String getFieldValue (HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        return value;
    }
}
