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
	
	// Constants defined for each field of the form
    private static final String FIELD_BID_PRICE  				= "bidPrice";
    private static final String FIELD_ARTICLE_END_PRICE  		= "articleEndPrice";
    private static final String FIELD_USER_ID	 				= "userId";
    private static final String FIELD_USER_OLD_ID	 			= "userOldId";
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
    
    // Retrieve a bid by with the article ID in the database
    public Bid getBidByArticleId(HttpServletRequest request) {
    	Integer articleId = Integer.parseInt(getFieldValue(request, FIELD_ARTICLE_ID));
    	Bid bid = new Bid();
	    bid = enchereDAO.getBidByArticleId(articleId);
	    return bid;
    }

    // Insert a bid in the database
	public Bid insertBid(HttpServletRequest request) throws Exception {
		User user 			= new User();
		User userOld 		= null;
		Article article 	= new Article();
        Bid bid 			= new Bid();
        
        // Parsing String form data to Integer
		Integer userCredit 	= Integer.valueOf(getFieldValue(request, FIELD_USER_CREDIT));
		Integer userId 		= Integer.valueOf(getFieldValue(request, FIELD_USER_ID));
		
		// Checking if a previous bid has been made an retrieve the userId of the bidder
		if (getFieldValue(request, FIELD_USER_OLD_ID)!=null) {
			Integer userOldId 		= Integer.valueOf(getFieldValue(request, FIELD_USER_OLD_ID));
			userOld = enchereDAO.getUserById(userOldId);
		}
		
		Integer articleId 			= Integer.valueOf(getFieldValue(request, FIELD_ARTICLE_ID));
		String bidPrice 			= getFieldValue(request, FIELD_BID_PRICE);
		Integer bidPriceInt 		= null;
		
		String articleEndPrice 		= getFieldValue(request, FIELD_ARTICLE_END_PRICE);
		Integer articleEndPriceInt 	= null;
		
		
        try {
        	bid.setBidUserId(userId);
        	bid.setBidArticleId(articleId);
        	article.setArticleId(articleId);
            processBidPrice(bidPrice, bidPriceInt, articleEndPrice, articleEndPriceInt, bid, article);
            processUserCredit(bidPrice, bidPriceInt, userCredit, user, articleEndPrice, articleEndPriceInt);
            
            // if a previous bidder exist, refund his bid
            if (userOld != null) {
            	processUserCredit2(userOld, articleEndPrice, articleEndPriceInt);
            	enchereDAO.updateUserCredit(userOld.getUserId(), userOld);
            }
            
            if (errors.isEmpty()) {
            	enchereDAO.insertBid(bid);
            	enchereDAO.updateArticleEndPrice(article);
            	enchereDAO.updateUserCredit(userId, user);
                result = "Ench??re sur un article : succ??s.";
            } else {
                result = "Ench??re sur un article : ??chec.";
            }
        } catch (DAOException e) {
            result = "Echec de l'ench??re !";
            e.printStackTrace();
		}

        return bid;
    }
    
	// Methods to call validation methods and insert data in the beans
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
    
    private void processUserCredit2(User userOld,String articleEndPrice, Integer articleEndPriceInt) {
        try {
        	articleEndPriceInt = articleEndPriceValidation(articleEndPrice);
        } catch (FormValidationException e) {
            setError(FIELD_BID_PRICE, e.getMessage());
        }
        userOld.setUserCredit(userOld.getUserCredit()+articleEndPriceInt);
    }
    
    // Data form validation
    private Integer bidPriceValidation(String bidPrice, Integer articleEndPriceInt) throws FormValidationException {
    if (bidPrice == null) {
            throw new FormValidationException("Champ requis.");
    } else {
    	Integer bidPriceInt = Integer.valueOf(bidPrice);
    	if (bidPriceInt<=articleEndPriceInt) {
    		throw new FormValidationException("Votre ench??re doit ??tre sup??rieure au prix actuel.");
    	}
    	else return bidPriceInt;
    	}
	}
	
	private Integer articleEndPriceValidation(String articleEndPrice) throws FormValidationException {
	    if (articleEndPrice == null) {
	            throw new FormValidationException("Champ requis.");
	    } else {
	    	Integer articleEndPriceInt = Integer.valueOf(articleEndPrice);
	    	return articleEndPriceInt;
	    }
    }
	
	private Integer userCreditValidation(Integer userCredit, Integer bidPriceInt) throws FormValidationException {
		if (userCredit == 0 || userCredit<bidPriceInt) {
	            throw new FormValidationException("Vous n'avez pas assez de points pour ench??rir.");
	    } else {
	    	userCredit = userCredit-bidPriceInt;
	    	return userCredit;
	    }
    }
	
	// Utility method for setting errors in the list
    private void setError(String field, String message) {
        errors.put(field, message);
    }

    // Utility method for requesting form data	
    private static String getFieldValue (HttpServletRequest request, String field) {
 	   String value = request.getParameter(field);
 	   if (value == null) {value = null;}
 	   else if (value.trim().compareTo("") == 0) {value = null;}
       return value;
    }
}
