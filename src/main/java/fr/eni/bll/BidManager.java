package fr.eni.bll;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import fr.eni.bo.Article;
import fr.eni.bo.Bid;
import fr.eni.bo.User;
import fr.eni.dao.DAOException;
import fr.eni.dao.EnchereDAO;

public final class BidManager {
    private static final String FIELD_BID_PRICE  				= "bidPrice";
    private static final String FIELD_ARTICLE_END_PRICE  		= "articleEndPrice";
    private static final String FIELD_USER_ID	 				= "userId";
    private static final String FIELD_ARTICLE_ID				= "articleId";
    private static final String FIELD_USER_CREDIT				= "userCredit";

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
		Integer userCredit 			= Integer.valueOf(getFieldValue(request, FIELD_USER_CREDIT));
		Integer userId 				= Integer.valueOf(getFieldValue(request, FIELD_USER_ID));
		Integer articleId 			= Integer.valueOf(getFieldValue(request, FIELD_ARTICLE_ID));
		String bidPrice 			= getFieldValue(request, FIELD_BID_PRICE);
		Integer bidPriceInt 		= null;
		
		String articleEndPrice 		= getFieldValue(request, FIELD_ARTICLE_END_PRICE);
		Integer articleEndPriceInt 	= null;
		
		User user = new User();
		Article article = new Article();
        Bid bid = new Bid();
        
        try {
        	bid.setBidUserId(userId);
        	bid.setBidArticleId(articleId);
        	article.setArticleId(articleId);
            processBidPrice(bidPrice, bidPriceInt, articleEndPrice, articleEndPriceInt, bid, article);
            processUserCredit(bidPrice, bidPriceInt, userCredit, user, articleEndPrice, articleEndPriceInt);
            if (errors.isEmpty()) {
            	enchereDAO.insertBid(bid);
            	enchereDAO.updateArticleEndPrice(article);
            	enchereDAO.updateUserCredit(userId, user);
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
    
    private void processBidPrice(String bidPrice, Integer bidPriceInt, String articleEndPrice, Integer articleEndPriceInt, Bid bid, Article article) {
        try {
        	articleEndPriceInt = articleEndPriceValidation(articleEndPrice);
            bidPriceInt = bidPriceValidation(bidPrice, articleEndPriceInt);
        } catch (FormValidationException e) {
            setError(FIELD_BID_PRICE, e.getMessage());
        }
        bid.setBidPrice(bidPriceInt);
        article.setArticleEndPrice(bidPriceInt);
    }
    
    private void processUserCredit(String bidPrice, Integer bidPriceInt, Integer userCredit, User user, String articleEndPrice, Integer articleEndPriceInt) {
        try {
        	articleEndPriceInt = articleEndPriceValidation(articleEndPrice);
            bidPriceInt = bidPriceValidation(bidPrice, articleEndPriceInt);
        	userCredit = userCreditValidation(userCredit, bidPriceInt);
        } catch (FormValidationException e) {
            setError(FIELD_BID_PRICE, e.getMessage());
        }
        user.setUserCredit(userCredit);
    }
    
    private Integer bidPriceValidation(String bidPrice, Integer articleEndPriceInt) throws FormValidationException {
    if (bidPrice == null) {
            throw new FormValidationException("Required field.");
    } else {
    	Integer bidPriceInt = Integer.valueOf(bidPrice);
    	if (bidPriceInt<=articleEndPriceInt) {
    		throw new FormValidationException("Bid price must be superior to the actual price.");
    	}
    	else return bidPriceInt;
    	}
	}
	
	private Integer articleEndPriceValidation(String articleEndPrice) throws FormValidationException {
	    if (articleEndPrice == null) {
	            throw new FormValidationException("Required field.");
	    } else {
	    	Integer articleEndPriceInt = Integer.valueOf(articleEndPrice);
	    	return articleEndPriceInt;
	    }
   }
	
	private Integer userCreditValidation(Integer userCredit, Integer bidPriceInt) throws FormValidationException {
		if (userCredit == 0 || userCredit<bidPriceInt) {
	            throw new FormValidationException("Not enough points to make bid.");
	    } else {
	    	userCredit = userCredit-bidPriceInt;
	    	return userCredit;
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
