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
          <ul class="list-group mt-3" v-if="posts != null">
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
              <div v-if="auth">
                <router-link :to="'/posts/' + post.id" class="btn btn-outline-warning me-3">Edit</router-link>
                <button class="btn btn-danger" @click="deletePost(post.id)">Delete</button>
              </div>
            </li>
          </ul>
<!--          <div class="mt-3 col-lg-4" v-else>-->
<!--            <p>No posts</p>-->
<!--          </div>-->
          <div class="mt-3 col-lg-4" v-if="messageDelete != null">
            <p>{{ messageDelete }}</p>
          </div>
        </div>
        <div>
          <router-link :to="{name:'add-post'}" class="btn btn-outline-primary mt-2" v-if="auth">Add new post</router-link>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import PostService from "@/services/PostService";
import {computed, onMounted, ref} from "vue";
import {useStore} from "vuex";

// let userId = ref(null)
let posts = ref([])
let messageDelete = ref(null)
const store = useStore();
const auth = computed(() => store.state.authenticated)

const showAllPosts = () => {
  PostService.getAllPosts()
      .then(response => {
        posts.value = response.data
      })
      .catch(error => {
        console.log(error)
      })
}

const deletePost = (postId) => {
  PostService.deletePost(postId)
      .then(() => {
        posts.value = posts.value.filter((post) => post.id !== postId)
        messageDelete.value = 'Post was deleted'
      })
      .catch(error => console.log(error))
}

onMounted(() => {
  showAllPosts()
  try {
    store.dispatch('setAuth', true)
  } catch (error) {
    store.dispatch('setAuth', false)
  }
})

</script>

<style scoped>

</style>