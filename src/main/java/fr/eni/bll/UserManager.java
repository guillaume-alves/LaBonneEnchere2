package fr.eni.bll;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import fr.eni.dao.DAOException;
import fr.eni.dao.EnchereDAO;
import fr.eni.bo.User;

public final class UserManager {
	private static final String FIELD_USER_ID	 	 	= "userId";
    private static final String FIELD_USER_NAME		   	= "userName";
    private static final String FIELD_USER_FIRSTNAME	= "userFirstname";
    private static final String FIELD_USER_NICKNAME 	= "userNickname";
    private static final String FIELD_USER_PASSWORD  	= "userPassword";
    private static final String FIELD_USER_CONFIRMATION	= "userConfirmation";
    private static final String FIELD_USER_EMAIL    	= "userEmail";
    private static final String FIELD_USER_PHONE    	= "userPhone";
    private static final String FIELD_USER_STREET    	= "userStreet";
    private static final String FIELD_USER_POSTALCODE	= "userPostalCode";
    private static final String FIELD_USER_CITY    		= "userCity";
    private static final String FIELD_USER_CONNECTION 	= "connection";
    
	private String result;
    
    private EnchereDAO enchereDAO;
    
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public UserManager(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

	public User registerUser(HttpServletRequest request) throws Exception {
        String name 		= getFieldValue(request, FIELD_USER_NAME);
        String firstname 	= getFieldValue(request, FIELD_USER_FIRSTNAME);
        String nickname 	= getFieldValue(request, FIELD_USER_NICKNAME);
        String email 		= getFieldValue(request, FIELD_USER_EMAIL);
        String password 	= getFieldValue(request, FIELD_USER_PASSWORD);
        String confirmation = getFieldValue(request, FIELD_USER_CONFIRMATION);
        String phone 		= getFieldValue(request, FIELD_USER_PHONE);
        String street 		= getFieldValue(request, FIELD_USER_STREET);
        String postalCode 	= getFieldValue(request, FIELD_USER_POSTALCODE);
        String city 		= getFieldValue(request, FIELD_USER_CITY);
        Integer credit 		= 100;
        Boolean admin = false;

        User user = new User();
        
        try {
        	processName(name, user);
        	processNickname(nickname, user);
        	processFirstname(firstname, user);
        	processEmail(email, user);
            processPassword(password, user);
            processConfirmation(confirmation);
            processPasswordConfirmation(password, confirmation, user);
            processPhone(phone, user);
            processStreet(street, user);
            processPostalCode(postalCode, user);
            processCity(city, user);
            processCredit(credit,user);
            processAdmin(admin,user);

            if (errors.isEmpty()) {
                enchereDAO.insertUser(user);
                result = "Registration succeed.";
            } else {
                result = "Registration failed.";
            }
        } catch (DAOException e) {
            result = "Registration failed !";
            e.printStackTrace();
		}

        return user;
    }
	
	public User connectUser(HttpServletRequest request) throws Exception {

		String nickname = getFieldValue(request, FIELD_USER_NICKNAME);
		String password = getFieldValue(request, FIELD_USER_PASSWORD);

		User user = new User();

		try {
			processPassword(password, user);
			processNicknameConnection(nickname, user);
			processConnection(nickname, password);
			user = enchereDAO.getUserByNickname(nickname);
		
			if (errors.isEmpty()) {
				result = "Connection succeed.";
			} else {
				result = "Connection failed.";
		    }
		} catch (DAOException e) {
			result = "Connection failed !";
            e.printStackTrace();
		}

		return user;
	}
	
	public User getUserById(HttpServletRequest request) {
		 User user = new User();	 
	     Integer userId = Integer.parseInt(getFieldValue(request, FIELD_USER_ID));
	     user = enchereDAO.getUserById(userId);
	     return user;
	 }
	
	public void deleteUser(HttpServletRequest request) {
	     Integer userId = Integer.parseInt(getFieldValue(request, FIELD_USER_ID));
	     enchereDAO.deleteUser(userId);
	 }
	
	public User updateUser(HttpServletRequest request) throws Exception {
		User userOld = getUserById(request);
		
		Integer userId 		= Integer.valueOf(getFieldValue(request, FIELD_USER_ID));
        String name 		= getFieldValue(request, FIELD_USER_NAME);
        String firstname 	= getFieldValue(request, FIELD_USER_FIRSTNAME);
        String nickname 	= getFieldValue(request, FIELD_USER_NICKNAME);
        String email 		= getFieldValue(request, FIELD_USER_EMAIL);
        String password 	= getFieldValue(request, FIELD_USER_PASSWORD);
        String confirmation = getFieldValue(request, FIELD_USER_CONFIRMATION);
        String phone 		= getFieldValue(request, FIELD_USER_PHONE);
        String street 		= getFieldValue(request, FIELD_USER_STREET);
        String postalCode 	= getFieldValue(request, FIELD_USER_POSTALCODE);
        String city 		= getFieldValue(request, FIELD_USER_CITY);

        User user = new User();
        
        try {
        	user.setUserId(userOld.getUserId());
        	updateName(name, user);
        	updateNickname(nickname, user, userOld);
        	updateFirstname(firstname, user);
        	updateEmail(email, user, userOld);
            updatePassword(password, user);
            updateConfirmation(confirmation);
            updatePasswordConfirmation(password, confirmation, user);
            updatePhone(phone, user);
            updateStreet(street, user);
            updatePostalCode(postalCode, user);
            updateCity(city, user);

            if (errors.isEmpty()) {
                enchereDAO.updateUser(userId, user);
                result = "Update succeed.";
            } else {
                result = "Update failed.";
            }
        } catch (DAOException e) {
            result = "Update failed !";
            e.printStackTrace();
		}

        return user;
    }
	
	// Integration of data in the bean
    
	private void processName(String name, User user) throws Exception {
	       try {
	           nameValidation(name);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_NAME, e.getMessage());
	       }
	       user.setUserName(name);
	}
	
	private void processConnection(String nickname, String password) {
        try {
        	connectionValidation(nickname, password);
        } catch (FormValidationException e) {
            setError(FIELD_USER_CONNECTION, e.getMessage());
        }
    }
	
	private void connectionValidation(String nickname, String password) throws FormValidationException {
		if (enchereDAO.getUserByNickname(nickname) != null) {
			if (enchereDAO.getUserByNickname(nickname).getUserPassword().equals(password)) {}
			else {
				throw new FormValidationException("Wrong nickname OR password.");
			}
		}
		else throw new FormValidationException("Nickname not registered.");
	}
	
	private void processNickname(String nickname, User user) throws Exception {
	       try {
	           nicknameValidation(nickname);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_NICKNAME, e.getMessage());
	       }
	       user.setUserNickname(nickname);
	}
	
	private void processNicknameConnection(String nickname, User user) throws Exception {
	       try {
	           nicknameValidationConnection(nickname);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_NICKNAME, e.getMessage());
	       }
	       user.setUserNickname(nickname);
	}
	
	private void processFirstname(String firstname, User user) throws Exception {
	       try {
	           firstnameValidation(firstname);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_FIRSTNAME, e.getMessage());
	       }
	       user.setUserFirstname(firstname);
	}

    private void processEmail(String email, User user) {
    	try {
    		emailValidation(email);
        } catch (FormValidationException e) {
            setError(FIELD_USER_EMAIL, e.getMessage());
        }
        user.setUserEmail(email);
    }
	
	private void processPassword(String password, User user) {
        try {
        	passwordValidation(password);
        } catch (FormValidationException e) {
            setError(FIELD_USER_PASSWORD, e.getMessage());
        }
        user.setUserPassword(password);
    }
	
	private void processConfirmation(String confirmation) {
        try {
        	confirmationValidation(confirmation);
        } catch (FormValidationException e) {
            setError(FIELD_USER_PASSWORD, e.getMessage());
        }
    }

    private void processPasswordConfirmation(String password, String confirmation, User user) throws Exception {
        try {
            passwordConfirmationValidation(password, confirmation);
        } catch (FormValidationException e) {
    	    setError(FIELD_USER_PASSWORD, e.getMessage());
            setError(FIELD_USER_CONFIRMATION, null);
        }
        user.setUserPassword(password);
    }
    
    private void processPhone(String phone, User user) throws Exception {
	       try {
	           phoneValidation(phone);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_PHONE, e.getMessage());
	       }
	       user.setUserPhone(phone);
	}
    
    private void processStreet(String street, User user) throws Exception {
	       try {
	           streetValidation(street);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_STREET, e.getMessage());
	       }
	       user.setUserStreet(street);
	}
    
    private void processPostalCode(String postalCode, User user) throws Exception {
	       try {
	           postalCodeValidation(postalCode);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_POSTALCODE, e.getMessage());
	       }
	       user.setUserPostalCode(postalCode);
	}
    
    private void processCity(String city, User user) throws Exception {
	       try {
	           cityValidation(city);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_CITY, e.getMessage());
	       }
	       user.setUserCity(city);
	}
    
    private void processCredit(Integer credit, User user) throws Exception {
	       user.setUserCredit(credit);
	}
    
    private void processAdmin(Boolean admin, User user) throws Exception {
	       user.setUserAdmin(admin);
	}
    
   // Data form validation
   
   private void nameValidation(String name) throws FormValidationException {
       if (name == null) {
           throw new FormValidationException("Name required.");
       }
   }
   
   private void firstnameValidation(String firstname) throws FormValidationException {
       if (firstname == null) {
           throw new FormValidationException("Firstname required.");
       }
   }
   
   private void nicknameValidation(String nickname) throws FormValidationException {
       if (nickname == null) {
           throw new FormValidationException("Nickname required.");
       }
       else if (enchereDAO.getUserByNickname(nickname) != null) {
    	   throw new FormValidationException("Nickname already used. Please choose another.");
	   }
   }
   
   private void nicknameValidationConnection(String nickname) throws FormValidationException {
       if (nickname == null) {
           throw new FormValidationException("Nickname required.");
       }
   }
   
   private void emailValidation(String email) throws FormValidationException {
	   if (email == null) {
		   throw new FormValidationException("Email required.");
	   }
	   else if (!email.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$")) {
		   throw new FormValidationException("Invalid email format.");
	   }
	   else if (enchereDAO.getUserByEmail(email) != null) {
	    	   throw new FormValidationException("Email already used. Please choose another.");
		   }
   }
   
   private void passwordConfirmationValidation(String password, String confirmation) throws FormValidationException {
	   if (password != null && confirmation != null) {
		   if (!password.equals(confirmation)) {
               throw new FormValidationException("Passwords are differents !");
		   }
	   }
   }
   
   private void passwordValidation(String password) throws FormValidationException {
		if (password == null) {
			throw new FormValidationException("Password required.");
		}
	}
   
   private void confirmationValidation(String confirmation) throws FormValidationException {
		if (confirmation == null) {
			throw new FormValidationException("Confirmation required.");
		}
	}
      
   private void phoneValidation(String phone) throws FormValidationException {
	    if (phone == null) {
	            throw new FormValidationException("Telephone required.");
	    }
   }
   
   private void streetValidation(String street) throws FormValidationException {
	    if (street == null) {
	            throw new FormValidationException("Street required.");
	    }
   }
   
   private void postalCodeValidation(String postalCode) throws FormValidationException {
	    if (postalCode == null) {
	            throw new FormValidationException("Postalcode required.");
	    }
   }
   
   private void cityValidation(String city) throws FormValidationException {
	    if (city == null) {
	            throw new FormValidationException("City required.");
	    }
   }
   
   // UPDATE PART
   
	private void updateName(String name, User user) throws Exception {
	       try {
	           nameValidationUpdate(name);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_NAME, e.getMessage());
	       }
	       user.setUserName(name);
	}
	
	private void updateNickname(String nickname, User user, User userOld) throws Exception {
	       try {
	           nicknameValidationUpdate(nickname, userOld);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_NICKNAME, e.getMessage());
	       }
	       user.setUserNickname(nickname);
	}
	
	private void updateFirstname(String firstname, User user) throws Exception {
	       try {
	           firstnameValidationUpdate(firstname);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_FIRSTNAME, e.getMessage());
	       }
	       user.setUserFirstname(firstname);
	}

    private void updateEmail(String email, User user, User userOld) {
    	try {
    		emailValidationUpdate(email, userOld);
        } catch (FormValidationException e) {
            setError(FIELD_USER_EMAIL, e.getMessage());
        }
        user.setUserEmail(email);
    }
	
	private void updatePassword(String password, User user) {
        try {
        	passwordValidationUpdate(password);
        } catch (FormValidationException e) {
            setError(FIELD_USER_PASSWORD, e.getMessage());
        }
        user.setUserPassword(password);
    }
	
	private void updateConfirmation(String confirmation) {
        try {
        	confirmationValidationUpdate(confirmation);
        } catch (FormValidationException e) {
            setError(FIELD_USER_PASSWORD, e.getMessage());
        }
    }

    private void updatePasswordConfirmation(String password, String confirmation, User user) throws Exception {
        try {
            passwordConfirmationValidationUpdate(password, confirmation);
        } catch (FormValidationException e) {
    	    setError(FIELD_USER_PASSWORD, e.getMessage());
            setError(FIELD_USER_CONFIRMATION, null);
        }
        user.setUserPassword(password);
    }
    
    private void updatePhone(String phone, User user) throws Exception {
	       try {
	           phoneValidationUpdate(phone);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_PHONE, e.getMessage());
	       }
	       user.setUserPhone(phone);
	}
    
    private void updateStreet(String street, User user) throws Exception {
	       try {
	           streetValidationUpdate(street);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_STREET, e.getMessage());
	       }
	       user.setUserStreet(street);
	}
    
    private void updatePostalCode(String postalCode, User user) throws Exception {
	       try {
	           postalCodeValidationUpdate(postalCode);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_POSTALCODE, e.getMessage());
	       }
	       user.setUserPostalCode(postalCode);
	}
    
    private void updateCity(String city, User user) throws Exception {
	       try {
	           cityValidationUpdate(city);
	       } catch (FormValidationException e) {
	           setError(FIELD_USER_CITY, e.getMessage());
	       }
	       user.setUserCity(city);
	}
    
   // Data form validation
   
   private void nameValidationUpdate(String name) throws FormValidationException {
       if (name == null) {
           throw new FormValidationException("Name required.");
       }
   }
   
   private void firstnameValidationUpdate(String firstname) throws FormValidationException {
       if (firstname == null) {
           throw new FormValidationException("Firstname required.");
       }
   }
   
   private void nicknameValidationUpdate(String nickname, User userOld) throws FormValidationException {
       if (nickname == null) {
           throw new FormValidationException("Nickname required.");
       }
       else if (enchereDAO.getUserByNickname(nickname) != null) {
    	   if (nickname.equals(userOld.getUserNickname())) {}
    	   else {
    	   throw new FormValidationException("Nickname already used. Please choose another.");
    	   }
       }
   }
   
   private void emailValidationUpdate(String email, User userOld) throws FormValidationException {
	   if (email == null) {
		   throw new FormValidationException("Email required.");
	   }
	   else if (!email.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$")) {
		   throw new FormValidationException("Invalid email format.");
	   }
	   else if (enchereDAO.getUserByNickname(email) != null) {
    	   if (email.equals(userOld.getUserNickname())) {}
    	   else {
    	   throw new FormValidationException("Nickname already used. Please choose another.");
    	   }
	   }
   }
   
   private void passwordConfirmationValidationUpdate(String password, String confirmation) throws FormValidationException {
	   if (password != null && confirmation != null) {
		   if (!password.equals(confirmation)) {
               throw new FormValidationException("Passwords are differents !");
		   }
	   }
   }
   
   private void passwordValidationUpdate(String password) throws FormValidationException {
		if (password == null) {
			throw new FormValidationException("Password required.");
		}
	}
   
   private void confirmationValidationUpdate(String confirmation) throws FormValidationException {
		if (confirmation == null) {
			throw new FormValidationException("Confirmation required.");
		}
	}
      
   private void phoneValidationUpdate(String phone) throws FormValidationException {
	    if (phone == null) {
	            throw new FormValidationException("Telephone required.");
	    }
   }
   
   private void streetValidationUpdate(String street) throws FormValidationException {
	    if (street == null) {
	            throw new FormValidationException("Street required.");
	    }
   }
   
   private void postalCodeValidationUpdate(String postalCode) throws FormValidationException {
	    if (postalCode == null) {
	            throw new FormValidationException("Postalcode required.");
	    }
   }
   
   private void cityValidationUpdate(String city) throws FormValidationException {
	    if (city == null) {
	            throw new FormValidationException("City required.");
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
