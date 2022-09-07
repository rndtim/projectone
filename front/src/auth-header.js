export default function authHeader() {
    let jwt = localStorage.getItem('jwt')
    if (jwt) {
        return {Authorization: 'Bearer ' + jwt};
    } else {
        return {};
    }
}