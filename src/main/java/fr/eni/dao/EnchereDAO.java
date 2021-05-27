package fr.eni.dao;

import java.util.List;

import fr.eni.bo.Article;
import fr.eni.bo.Bid;
import fr.eni.bo.Category;
import fr.eni.bo.User;

public interface EnchereDAO {
	
	void insertUser(User user) throws DAOException;

	User getUserByEmail(String email) throws DAOException;
	
	User getUserById(Integer userId) throws DAOException;
	
	void insertBid(Bid bid) throws DAOException;
	
	void insertArticle(Article article) throws DAOException;
	
	User getUserByNickname(String nickname) throws DAOException;
	
	Article getArticleById(Integer articleId) throws DAOException;

	List<Article> getListArticles() throws DAOException;
	
	List<Article> getListArticlesByCategory(Integer articleCategoryId) throws DAOException;
	
	List<Category> getListCategories() throws DAOException;
	
	void deleteUser(Integer userId) throws DAOException;

	void deleteArticle(Integer articleId) throws DAOException;
	
	void deleteBidsOfArticle(Integer articleId) throws DAOException;
	
	void updateUser(Integer userId, User user) throws DAOException;;
	
	void updateUserCredit(Integer userId, User user) throws DAOException;;
	
	void updateArticleEndPrice(Article article) throws DAOException;;
	
	Bid getBidByArticleId(Integer articleId) throws DAOException;;

}
