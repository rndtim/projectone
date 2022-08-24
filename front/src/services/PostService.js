import http from "../http-common";

class PostService {
    getAllPosts(userId) {
        return http.get(`api/posts?userId=${userId}`);
    }

    getOnePost(postId) {
        return http.get(`api/posts/${postId}`)
    }

    createPost(userId, data) {
        return http.post(`api/posts?userId=${userId}`, data)
    }

    editPost(postId, data) {
        return http.put(`api/posts/${postId}`, data)
    }

    deletePost(postId) {
        return http.delete(`api/posts/${postId}`)
    }
}

export default new PostService();