export default function authHeader() {
    const userStr = localStorage.getItem("user");
    
    let user = null;
    if (userStr)
      user = JSON.parse(userStr);
  
    if (user && user.accessToken) {
      return { Authorization: 'Basic ' + user.accessToken }; // for Spring Boot back-end
    } else {
      return { Authorization: '' }; // for Spring Boot back-end
    }
  }