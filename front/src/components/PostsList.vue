<template>
  <section>
    <div class="container mx-auto">
      <div class="row">
        <h4>Posts List</h4>
        <div class="col-lg-6">
          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
        <div class="row">
          <ul class="list-group mt-3">
            <li class="list-group-item d-flex justify-content-between align-items-center"
                v-for="post in posts.sort((a,b) => a.id - b.id)"
                :key="post.id"
            >
              <div>
                {{ post.title }}
              </div>
              <div>
                {{ post.description }}
              </div>
              <div>
                <router-link :to="'/' + post.id" class="btn btn-outline-warning me-3">Edit</router-link>
                <button class="btn btn-danger" @click="deletePost(post.id)">Delete post</button>
              </div>
            </li>
          </ul>
          <div class="mt-3 col-lg-4" v-if="postDeleted != null">
            <p>{{ postDeleted }}</p>
          </div>
        </div>
        <div>
          <router-link to="/add" class="btn btn-outline-primary mt-2">Add new post</router-link>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import PostService from "@/services/PostService";
import {ref} from "vue";

// let userId = ref(null)
let posts = ref([])
let postDeleted = ref(null)

const showAllPosts = () => {
  PostService.getAllPosts(4)
      .then(response => {
        posts.value = response.data
      })
      .catch(error => {
        posts.value = ["No posts by this user"]
        console.log(error)
      })
}

const deletePost = (postId) => {
  PostService.deletePost(postId)
      .then(() => {
        posts.value = posts.value.filter((post) => post.id !== postId)
        postDeleted.value = 'Post was deleted'
      })
      .catch(error => console.log(error))
}

  showAllPosts()

</script>

<style scoped>

</style>