package fr.eni.bll;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import fr.eni.dao.DAOException;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.Article;
import fr.eni.bo.Category;

public final class ArticleManager {
	
	// Constants defined for each field of the form
	private static final String FIELD_ARTICLE_ID	 			= "articleId";
	private static final String FIELD_ARTICLE_NAME  			= "articleName";
    private static final String FIELD_ARTICLE_DESCRIPTION  		= "articleDescription";
    private static final String FIELD_ARTICLE_BID_START_DATE  	= "articleBidStartDate";
    private static final String FIELD_ARTICLE_BID_END_DATE  	= "articleBidEndDate";
    private static final String FIELD_ARTICLE_START_PRICE  		= "articleStartPrice";
    private static final String FIELD_ARTICLE_END_PRICE  		= "articleEndPrice";
    private static final String FIELD_ARTICLE_USER_ID		  	= "articleUserId";
    private static final String FIELD_ARTICLE_CATEGORY_ID  		= "articleCategoryId";
    private static final String FIELD_USER_ID	 				= "userId";
    private static final String FIELD_USER_OLD_ID	 			= "userOldId";
    private static final String FIELD_ARTICLE_NAME_SEARCH		= "articleNameSearch";
    
    private String result;
    private EnchereDAO enchereDAO;
    private List<Category> list_categories;
    private List<Article> list_articles;
    private Map<String, String> errors = new HashMap<String, String>();
    
    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public ArticleManager(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }
    
    // Register an article in the database
	public Article registerArticle(HttpServletRequest request) throws Exception {
        String articleName 			 		= getFieldValue(request, FIELD_ARTICLE_NAME);
        String articleDescription 	 		= getFieldValue(request, FIELD_ARTICLE_DESCRIPTION);
        
        String articleBidStartDate	 		= getFieldValue(request, FIELD_ARTICLE_BID_START_DATE);
        Date articleBidStartDateDT 	 		= null;
        		
        String articleBidEndDate	 		= getFieldValue(request, FIELD_ARTICLE_BID_END_DATE);
        Date articleBidEndDateDT     		= null;
       
        String articleStartPrice 	 		= getFieldValue(request, FIELD_ARTICLE_START_PRICE);
        Integer articleStartPriceInt		= null;
        
        String articleEndPrice   	 		= getFieldValue(request, FIELD_ARTICLE_START_PRICE);
        Integer articleEndPriceInt			= null;
        
        Integer articleUserId	 	 		= Integer.valueOf(getFieldValue(request, FIELD_ARTICLE_USER_ID));
        
        String articleCategoryId			= getFieldValue(request, FIELD_ARTICLE_CATEGORY_ID);
        Integer articleCategoryIdInt	 	= null;
        
        Article article = new Article();
        
        try {
            processArticleName(articleName, article);
            processArticleDescription(articleDescription, article);
            processArticleBidStartDate(articleBidStartDate, articleBidStartDateDT, article);
            processArticleBidEndDate(articleBidEndDate, articleBidEndDateDT, article);
            processArticleStartPrice(articleStartPrice, articleStartPriceInt,article);
            processArticleEndPrice(articleEndPrice, articleEndPriceInt, article);
            processArticleUserId(articleUserId, article);
            processArticleCategoryId(articleCategoryId, articleCategoryIdInt, article);
            
            if (errors.isEmpty()) {
            	enchereDAO.insertArticle(article);
                result = "Ajout d'article : succès.";
            } else {
                result = "Ajout d'article : échec.";
            }
        } catch (DAOException e) {
            result = "Ajout d'article : échec !";
            e.printStackTrace();
		}

        return article;
    }
	
	// Delete the article of the database by its ID
	public void deleteArticle(HttpServletRequest request) {
		
	     Integer articleId = Integer.parseInt(getFieldValue(request, FIELD_ARTICLE_ID));
	     if (getFieldValue(request, FIELD_USER_OLD_ID)!=null) {
	    	 enchereDAO.deleteBidsOfArticle(articleId);
	    	 enchereDAO.deleteArticle(articleId);
	     } else {
	    	 enchereDAO.deleteArticle(articleId);
	     }
	}
	
	// Return the list of the articles
	public List<Article> getListArticles(HttpServletRequest request) {
		if
			// Return articles associated with the search field
			(getFieldValue(request, FIELD_ARTICLE_NAME_SEARCH)!=null) {
			String articleNameSearch = "%"+getFieldValue(request, FIELD_ARTICLE_NAME_SEARCH)+"%";
			list_articles = enchereDAO.getListArticlesByArticleNameSearch(articleNameSearch);
		} else if
			// Return articles associated with the selected category
			(getFieldValue(request, FIELD_ARTICLE_CATEGORY_ID)!= null &&
			!getFieldValue(request, FIELD_ARTICLE_CATEGORY_ID).equals("noCategory")) {
			Integer articleCategoryId = Integer.parseInt(getFieldValue(request, FIELD_ARTICLE_CATEGORY_ID));
	    	list_articles = enchereDAO.getListArticlesByCategory(articleCategoryId);
		} else {
			// return all the articles
			list_articles = enchereDAO.getListArticles();
		} return list_articles;
    }
	
	// Return the list of the sale articles
	public List<Article> getListSaleArticles(HttpServletRequest request) {
		Integer userId = Integer.parseInt(getFieldValue(request, FIELD_USER_ID));
		list_articles = enchereDAO.getListSaleArticles(userId);
		return list_articles;
    }
	
	// Return the list of the sale articles
	public List<Article> getListPurchasedArticles(HttpServletRequest request) {
		Integer userId = Integer.parseInt(getFieldValue(request, FIELD_USER_ID));
		list_articles = enchereDAO.getListPurchasedArticles(userId);
		return list_articles;
    }
	
	// Return the list of the categories
	public List<Category> getListCategories() {
    	list_categories = enchereDAO.getListCategories();
        return list_categories;
    }
	
	// Return article requested with its ID
	public Article getArticleById(HttpServletRequest request) {
		Article article = new Article();
	    Integer articleId = Integer.parseInt(getFieldValue(request, FIELD_ARTICLE_ID));
	    article = enchereDAO.getArticleById(articleId);
	    return article;
	}
	
	// Methods to call validation methods and insert data in the beans
	private void processArticleName(String articleName, Article article) {
		try {
			articleNameValidation(articleName);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_NAME, e.getMessage());
		} article.setArticleName(articleName);
	}
	
	private void processArticleDescription(String articleDescription, Article article) {
		try {
			articleDescriptionValidation(articleDescription);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_DESCRIPTION, e.getMessage());
		} article.setArticleDescription(articleDescription);
	}
	
	private void processArticleBidStartDate(String articleBidStartDate, Date articleBidStartDateDT, Article article) throws ParseException {
		try {
			articleBidStartDateDT = articleBidStartDateValidation(articleBidStartDate);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_BID_START_DATE, e.getMessage());
		} article.setArticleBidStartDate(articleBidStartDateDT);
	}
	
	private void processArticleBidEndDate(String articleBidEndDate, Date articleBidEndDateDT, Article article) throws ParseException {
		try {
			articleBidEndDateDT = articleBidEndDateValidation(articleBidEndDate);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_BID_END_DATE, e.getMessage());
		} article.setArticleBidEndDate(articleBidEndDateDT);
	}
	
	private void processArticleStartPrice(String articleStartPrice, Integer articleStartPriceInt, Article article) {
		try {
			articleStartPriceInt = articleStartPriceValidation(articleStartPrice);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_START_PRICE, e.getMessage());
		}
		article.setArticleStartPrice(articleStartPriceInt);
	}
	
	private void processArticleEndPrice(String articleEndPrice, Integer articleEndPriceInt, Article article) {
		try {
			articleEndPriceInt = articleEndPriceValidation(articleEndPrice);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_END_PRICE, e.getMessage());
		} article.setArticleEndPrice(articleEndPriceInt);
	}
	
	private void processArticleUserId(Integer articleUserId, Article article) {
		try {
			articleUserIdValidation(articleUserId);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_USER_ID, e.getMessage());
		} article.setArticleUserId(articleUserId);
	}
	
	private void processArticleCategoryId(String articleCategoryId, Integer articleCategoryIdInt, Article article) {
		try {
			articleCategoryIdInt = articleCategoryIdValidation(articleCategoryId);
		} catch (FormValidationException e) {
			setError(FIELD_ARTICLE_CATEGORY_ID, e.getMessage());
		} article.setArticleCategoryId(articleCategoryIdInt);
	}
	
	// Data form validation
	private void articleNameValidation(String articleName) throws FormValidationException {
	    if (articleName == null) {
	            throw new FormValidationException("Required field.");
	    }
   }
   
   private void articleDescriptionValidation(String articleDescription) throws FormValidationException {
	    if (articleDescription == null) {
	            throw new FormValidationException("Required field.");
	    }
   }
   
   private Date articleBidStartDateValidation(String articleBidStartDate) throws FormValidationException, ParseException {
	    if (articleBidStartDate == null) {
	            throw new FormValidationException("Required field.");
	    } else {
	    	Date articleBidStartDateDT = new SimpleDateFormat("yyyy-MM-dd").parse(articleBidStartDate);
	        return articleBidStartDateDT;
	    }
   }
   
   private Date articleBidEndDateValidation(String articleBidEndDate) throws FormValidationException, ParseException {
	    if (articleBidEndDate == null) {
	            throw new FormValidationException("Required field.");
	    } else {
	    	Date articleBidEndDateDT = new SimpleDateFormat("yyyy-MM-dd").parse(articleBidEndDate);
	        return articleBidEndDateDT;
	    }
   }
   
   private Integer articleStartPriceValidation(String articleStartPrice) throws FormValidationException {
	    if (articleStartPrice == null) {
	            throw new FormValidationException("Required field.");
	    } else {
	    	Integer articleStartPriceInt = Integer.valueOf(articleStartPrice);
	    	return articleStartPriceInt;
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
   
   private void articleUserIdValidation(Integer articleUserId) throws FormValidationException {
	    if (articleUserId == null) {
	            throw new FormValidationException("Required field.");
	    }
   }
   
   private Integer articleCategoryIdValidation(String articleCategoryId) throws FormValidationException { 
	   if (articleCategoryId == null) {
	            throw new FormValidationException("Required field.");
	   } else {
	    	Integer articleCategoryIdInt = Integer.valueOf(articleCategoryId);
	    	return articleCategoryIdInt;
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
