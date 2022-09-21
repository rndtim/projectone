import http from "../http-common";

class AuthService {
    registration(data) {
        return http.post('/auth/registration', data)
            .then(response =>{
                localStorage.setItem('user', JSON.stringify(response.data))
                // return response.data
            })
    }
    login(credentials) {
        return http.post('/auth/login', credentials)
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('user', JSON.stringify(response.data))
                }
                return response.data
            })
    }
    logout() {
        localStorage.removeItem('user');
    }
}

export default new AuthService();