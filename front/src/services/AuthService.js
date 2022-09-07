import http from "../http-common";

class AuthService {
    registration(data) {
        return http.post('/auth/registration', data);
    }
    login(credentials) {
        return http.post('/auth/login', credentials)
    }
}

export default new AuthService();