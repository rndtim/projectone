<template>
  <section>
    <div class="container mx-auto">
      <div class="row">
        <h1>List of cocktails</h1>
        <div class="col-lg-6">
          <!--          <form class="d-flex" role="search">-->
          <!--            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
          <!--            <button class="btn btn-success" type="submit">Search</button>-->
          <!--          </form>-->
          <div>
            <router-link :to="{name:'add-post'}" class="btn btn-primary mt-2" v-if="auth">Add new Cocktail
            </router-link>
          </div>
          <div class="mt-3 col-lg-4" v-if="messageDelete != null">
            <h3>{{ messageDelete }}</h3>
          </div>
        </div>
        <div class="row row-cols-lg-4 row-cols-sm-2 row-cols-md-3 g-4 mt-3" v-if="posts.length !== 0">
          <div class="col" v-for="post in posts.sort((a,b) => a.id - b.id)"
               :key="post.id">
            <div class="card shadow-sm h-100">
              <img :src="require('../assets/post_images/' + post.author.username + '/' + post.image)" class="card-img-top img-fluid"
                   :alt="post.title">
              <div class="card-body">
                <h5 class="card-title">{{ post.title }}</h5>
                <p class="card-text">{{ post.description }}</p>
              </div>
              <div class="card-footer">
                <div class="d-flex justify-content-between align-items-center">
                  <div class="d-inline-grid" v-if="auth && store.state.auth.user.id === post.author.id">
                    <router-link :to="'/posts/' + post.id" class="btn btn-warning me-2 py-1">Edit</router-link>
                    <button class="btn btn-danger py-1" @click="deletePost(post.id)">Delete</button>
                  </div>
                  <h5 class="text-muted"> {{ post.author.username }}</h5>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div v-else>
          <h3>No posts</h3>
        </div>

      </div> <!-- row -->
    </div> <!-- container -->
  </section>
</template>

<script setup>
import PostService from "@/services/PostService";
import {computed, onMounted, ref} from "vue";
import {useStore} from "vuex";

let posts = ref([])
let messageDelete = ref(null)
const store = useStore();
const auth = computed(() => store.state.auth.status.loggedIn)

const showAllPosts = () => {
  PostService.getAllPosts()
      .then(response => {
        posts.value = response.data
      })
      .catch(error => {
        console.log(error)
      })
}

// const getImgUrl = (author, img) => {
//   return require('../assets/post_images/' + author + '/' + img);
// }

const deletePost = (postId) => {
  PostService.deletePost(postId)
      .then(() => {
        posts.value = posts.value.filter((post) => post.id !== postId)
        messageDelete.value = 'Post was deleted'
      })
      .catch(error => console.log(error))
}

showAllPosts()

onMounted(() => {

})

</script>

<style scoped lang="scss">
.card {
  img {
    min-height: 300px;
    max-height: 300px;
    object-fit: cover;
  }

  .card-body {
    color: black;

    .card-text {
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  }
}
</style>