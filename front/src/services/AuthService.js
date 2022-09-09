import http from "../http-common";

class AuthService {
    registration(data) {
        return http.post('/auth/registration', data);
    }
    login(credentials) {
        return http.post('/auth/login', credentials)
    }
    logout() {
        localStorage.removeItem('jwt');
    }
}

export default new AuthService();