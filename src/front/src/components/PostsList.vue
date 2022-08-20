<template>
  <section>
    <div class="container mx-auto">
      <div class="row">
        <div class="col-md-8">
          <h4>Posts List</h4>
          <div class="col-lg-6">
            <input class="form-control" type="text" size="30" placeholder="User id" v-model="userId">
            <button class="btn btn-outline-primary mt-1" @click="showAllPosts">Show user's posts</button>
          </div>
          <ul class="list-group mt-3 col-lg-6">
            <li class="list-group-item"
                v-for="post in posts"
                :key="post.id"
            >
              {{ post.title }}: {{ post.description }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import PostService from "@/serveces/PostService";
import {ref} from "vue";

let userId = ref(null)
let posts = ref([])

const showAllPosts = async () => {
  await PostService.getAllByUserId(userId.value)
      .then(res => {
        posts.value = res.data
        console.log(posts)
      })
}

// onMounted(() => {
//   showAllPosts()
// })
</script>

<style scoped>

</style>