package fr.eni.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import fr.eni.bo.Article;
import fr.eni.bo.Bid;
import fr.eni.bo.Category;
import fr.eni.bo.User;
import static fr.eni.dao.DAOTools.*;

public class EnchereDAOImpl implements EnchereDAO {
	
	private static final String SQL_INSERT_USER 			 = "INSERT INTO Users (user_nickname, user_name, user_firstname, user_email, user_phone, user_street, user_postal_code, user_city, user_password, user_credit, user_admin) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_USER_BY_EMAIL 	 = "SELECT * FROM Users WHERE user_email = ?";
    private static final String SQL_INSERT_BID 				 = "INSERT INTO Bids (bid_user_id, bid_article_id, bid_date, bid_price) VALUES (?, ?, GETDATE(), ?)";
    private static final String SQL_SELECT_USER_BY_NICKNAME  = "SELECT * FROM Users WHERE user_nickname = ?";
    private static final String SQL_INSERT_ARTICLE 			 = "INSERT INTO Articles (article_name, article_description, article_start_date, article_end_date, article_start_price, article_end_price, article_user_id, article_category_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_ALL_ARTICLES 	 = "SELECT * FROM Articles INNER JOIN Users ON Articles.article_user_id = Users.user_id";
    private static final String SQL_SELECT_USER_BY_ID 		 = "SELECT * FROM Users WHERE (user_id=?)";
    private static final String SQL_SELECT_ALL_CATEGORIES 	 = "SELECT * FROM Categories";
    private static final String SQL_DELETE_USER_BY_ID 		 = "DELETE FROM Users WHERE user_Id = ?";
    private static final String SQL_UPDATE_USER 			 = "UPDATE Users SET user_nickname=?, user_name=?, user_firstname=?, user_email=?, user_phone=?, user_street=?, user_postal_code=?, user_city=?, user_password=? WHERE user_id=?";
    private static final String SQL_UPDATE_ARTICLE_END_PRICE = "UPDATE Articles SET article_end_price=? WHERE article_id=?";
    private static final String SQL_SELECT_ARTICLE_BY_ID 	 = "SELECT * FROM Categories INNER JOIN Articles ON Categories.category_id = Articles.article_category_id INNER JOIN Users ON Articles.article_user_id = Users.user_id WHERE article_id=?";
    
    private DAOFactory daoFactory;

	public EnchereDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    private static User fetchUser(ResultSet resultSet) throws SQLException {
    	User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserNickname(resultSet.getString("user_nickname"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserFirstname(resultSet.getString("user_firstname"));
        user.setUserEmail(resultSet.getString("user_email"));
        user.setUserPhone(resultSet.getString("user_phone"));
        user.setUserStreet(resultSet.getString("user_street"));
        user.setUserPostalCode(resultSet.getString("user_postal_code"));
        user.setUserCity(resultSet.getString("user_city"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setUserCredit(resultSet.getInt("user_credit"));
        user.setUserAdmin(resultSet.getBoolean("user_admin"));
        
        return user;
    }
    
    // Interface EnchereDAO implementation
    @Override
	public User getUserByEmail(String email) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
        	// Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_SELECT_USER_BY_EMAIL, email);
            resultSet = preparedStatement.executeQuery();
            // Processing from resulSet
            if (resultSet.next()) {
                user = fetchUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	silentClosing(resultSet, preparedStatement, conn);
        }

        return user;
    }
    
    public User getUserById(Integer userId) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
        	// Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_SELECT_USER_BY_ID, userId);
            resultSet = preparedStatement.executeQuery();
            // Processing from resulSet
            if (resultSet.next()) {
            	user = fetchUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
        	silentClosing(resultSet, preparedStatement, conn);
        }

        return user;
    }
   
    
    // Interface EnchereDAO implementation
    @Override
    public void insertUser(User user) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_INSERT_USER, user.getUserNickname(), user.getUserName(), user.getUserFirstname(), user.getUserEmail(), user.getUserPhone(), user.getUserStreet(), user.getUserPostalCode(), user.getUserCity(), user.getUserPassword(), user.getUserCredit(), user.getUserAdmin());
            int statut = preparedStatement.executeUpdate();
            // Analysing statut
            if (statut == 0) {
                throw new DAOException("Operation failed, no lign added.");
            }
        } catch (SQLException e) {
            throw new DAOException (e);
        } finally {
        	silentClosing(resultSet, preparedStatement, conn);
        }
    }
    
    @Override
	public void deleteUser(Integer userId) throws DAOException {
    	 Connection conn = null;
         PreparedStatement preparedStatement = null;

         try {
        	 conn = daoFactory.getConnection();
             preparedStatement = initPreparedStatement( conn, SQL_DELETE_USER_BY_ID, userId);
             int statut = preparedStatement.executeUpdate();
             if ( statut == 0 ) {
                 throw new DAOException("Operation failed, no lign deleted.");
             }
         } catch ( SQLException e ) {
             throw new DAOException( e );
         } finally {
        	 silentClosing( preparedStatement, conn );
         }		
	}
 // Interface EnchereDAO implementation
    @Override
    public void updateUser(Integer userId, User user) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_UPDATE_USER, user.getUserNickname(), user.getUserName(), user.getUserFirstname(), user.getUserEmail(), user.getUserPhone(), user.getUserStreet(), user.getUserPostalCode(), user.getUserCity(), user.getUserPassword(), userId);
            int statut = preparedStatement.executeUpdate();
            // Analysing statut
            if (statut == 0) {
                throw new DAOException("Operation failed, no lign added." );
            }
        } catch (SQLException e) {
            throw new DAOException (e);
        } finally {
        	silentClosing(resultSet, preparedStatement, conn);
        }
    }
    
    // Interface EnchereDAO implementation
    @Override
    public void insertBid(Bid bid) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
        	// Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_INSERT_BID, bid.getBidUserId(), bid.getBidArticleId(), bid.getBidPrice());
            int statut = preparedStatement.executeUpdate();
            // Analysing statut
            if (statut == 0) {
                throw new DAOException("Operation failed, no lign added." );
            }
        } catch (SQLException e) {
            throw new DAOException (e);
        } finally {
        	silentClosing(preparedStatement, conn);
        }
    }
    
    // Interface EnchereDAO implementation
	@Override
    public void insertArticle(Article article) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_INSERT_ARTICLE, article.getArticleName(), article.getArticleDescription(), article.getArticleBidStartDate(), article.getArticleBidEndDate(), article.getArticleStartPrice(), article.getArticleEndPrice(), article.getArticleUserId(), article.getArticleCategoryId());
            int statut = preparedStatement.executeUpdate();
            // Analysing statut
            if (statut == 0) {
                throw new DAOException("Operation failed, no lign added." );
            }
        } catch (SQLException e) {
            throw new DAOException (e);
        } finally {
        	silentClosing(preparedStatement, conn);
        }
    }

	@Override
	public User getUserByNickname(String nickname) throws DAOException {
		 Connection conn = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        User user = null;

	        try {
	        	// Getting connection from factory
	            conn = daoFactory.getConnection();
	            preparedStatement = initPreparedStatement(conn, SQL_SELECT_USER_BY_NICKNAME, nickname);
	            resultSet = preparedStatement.executeQuery();
	            // Processing from resulSet
	            if (resultSet.next()) {
	                user = fetchUser(resultSet);
	            }
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        } finally {
	        	silentClosing(resultSet, preparedStatement, conn);
	        }

	        return user;
    }
	
	@Override
	public List<Article> getListArticles() throws DAOException {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> list_articles = new ArrayList<Article>();
        Article article;
        try {
            // Getting connection from Factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_SELECT_ALL_ARTICLES);
            resultSet = preparedStatement.executeQuery();
           // Request reading
            while (resultSet.next()) {
                article = fetchArticle(resultSet);
                list_articles.add(article);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosing(resultSet, preparedStatement, conn);
        }
        return list_articles;
    }
	
	//Method linked to the previous method getListArticles()
		private static Article fetchArticle(ResultSet resultSet) throws SQLException {
			Article article = new Article();
			User user = new User();
			article.setArticleId(resultSet.getInt("article_id"));
			article.setArticleName(resultSet.getString("article_name"));
			article.setArticleDescription(resultSet.getString("article_description"));
			article.setArticleBidStartDate(resultSet.getDate("article_start_date"));
			article.setArticleBidEndDate(resultSet.getDate("article_end_date"));
			article.setArticleStartPrice(resultSet.getInt("article_start_price"));
			article.setArticleEndPrice(resultSet.getInt("article_end_price"));
			article.setArticleUserId(resultSet.getInt("article_user_id"));
			article.setArticleCategoryId(resultSet.getInt("article_category_id"));
			user.setUserId(resultSet.getInt("user_id"));
			user.setUserNickname(resultSet.getString("user_nickname"));
			article.setArticleUser(user);
	        return article;
		}
	
	@Override
	public List<Category> getListCategories() throws DAOException {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Category> list_categories = new ArrayList<Category>();
        Category category;

        try {
            // Getting connection from Factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_SELECT_ALL_CATEGORIES);
            resultSet = preparedStatement.executeQuery();
           // Request reading
            while (resultSet.next()) {
                category = fetchCategory(resultSet);
                list_categories.add(category);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosing(resultSet, preparedStatement, conn);
        }
        return list_categories;
    }
	
	@Override
	public Article getArticleById(Integer articleId) throws DAOException {
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Article article = new Article();

        try {
            // Getting connection from Factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_SELECT_ARTICLE_BY_ID, articleId);
            resultSet = preparedStatement.executeQuery();
           // Request reading
            while (resultSet.next()) {
            	article = fetchArticle2(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosing(resultSet, preparedStatement, conn);
        }
        return article;
    }
	
	//Method linked to the previous method getArticleById()
	private static Article fetchArticle2(ResultSet resultSet) throws SQLException {
		Article article = new Article();
		Category category = new Category();
		User user = new User();
		Bid bid = new Bid();
		article.setArticleId(resultSet.getInt("article_id"));
		article.setArticleName(resultSet.getString("article_name"));
		article.setArticleDescription(resultSet.getString("article_description"));
		article.setArticleBidStartDate(resultSet.getDate("article_start_date"));
		article.setArticleBidEndDate(resultSet.getDate("article_end_date"));
		article.setArticleStartPrice(resultSet.getInt("article_start_price"));
		article.setArticleEndPrice(resultSet.getInt("article_end_price"));
		article.setArticleUserId(resultSet.getInt("article_user_id"));
		article.setArticleCategoryId(resultSet.getInt("article_category_id"));
		category.setCategoryId(resultSet.getInt("category_id"));
		category.setCategoryName(resultSet.getString("category_name"));
		user.setUserNickname(resultSet.getString("user_nickname"));
		user.setUserStreet(resultSet.getString("user_street"));
		user.setUserPostalCode(resultSet.getString("user_postal_code"));
		user.setUserCity(resultSet.getString("user_city"));
		article.setArticleCategory(category);
		article.setArticleUser(user);
		//bid.setBidPrice(resultSet.getInt("bid_price"));
		//article.setArticleBid(bid);
        return article;
	}
	
	//Method linked to the previous method getListCategories()
	private static Category fetchCategory(ResultSet resultSet) throws SQLException {
		Category category = new Category();
		category.setCategoryId(resultSet.getInt("category_id"));
		category.setCategoryName(resultSet.getString("category_name"));
			
		return category;
	}

	@Override
    public void updateArticleEndPrice(Article article) throws DAOException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Getting connection from factory
            conn = daoFactory.getConnection();
            preparedStatement = initPreparedStatement(conn, SQL_UPDATE_ARTICLE_END_PRICE, article.getArticleEndPrice(), article.getArticleId());
            int statut = preparedStatement.executeUpdate();
            // Analysing statut
            if (statut == 0) {
                throw new DAOException("Operation failed, no lign added." );
            }
        } catch (SQLException e) {
            throw new DAOException (e);
        } finally {
        	silentClosing(resultSet, preparedStatement, conn);
        }
    }
	
}
