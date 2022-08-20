import http from "axios";

class PostService {
    getAllByUserId(userId) {
        return http.get(`api/posts?userId=${userId}`);
    }

    get(postId) {
        return http.get(`api/posts/${postId}`)
    }

    createPost(data, userId) {
        return http.post(`api/posts?userId=${userId}`, data)
    }
}

export default new PostService();