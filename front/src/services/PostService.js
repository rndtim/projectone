import http from "../http-common";

class PostService {
    getAllPosts(userId) {
        return http.get(`/posts?userId=${userId}`);
    }

    getOnePost(postId) {
        return http.get(`/posts/${postId}`)
    }

    createPost(userId, data) {
        return http.post(`/posts?userId=${userId}`, data)
    }

    editPost(postId, data) {
        return http.put(`/posts/${postId}`, data)
    }

    deletePost(postId) {
        return http.delete(`/posts/${postId}`)
    }
}

export default new PostService();