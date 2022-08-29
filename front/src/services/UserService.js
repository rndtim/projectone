import http from "../http-common";

class UserService {
    registration(data) {
        return http.post('/users/registration', data);
    }
}

export default new UserService();