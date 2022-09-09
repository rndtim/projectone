import http from "../http-common";
import authHeader from '../auth-header'

class PostService {
    getAllPosts() {
        return http.get(`/posts`);
    }

    getOnePost(postId) {
        return http.get(`/posts/${postId}`, {headers: authHeader()})
    }

    createPost(data) {
        return http.post(`/posts`, data, {headers: authHeader()})
    }

    editPost(postId, data) {
        return http.put(`/posts/${postId}`, data, {headers: authHeader()})
    }

    deletePost(postId) {
        return http.delete(`/posts/${postId}`, {headers: authHeader()})
    }
}

export default new PostService();