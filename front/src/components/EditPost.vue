<template>
  <div class="container mx-auto">
    <div class="row">
      <h1>Edit Post</h1>
      <div class="col-lg-6">
        <label for="inputTitle" class="form-label">Title</label>
        <input id="inputTitle" class="form-control" type="text" size="30" placeholder="Title" v-model="post.title">
        <label for="inputDescription" class="form-label">Description</label>
        <textarea class="form-control" id="inputDescription" rows="3" placeholder="Description"
                  v-model="post.description"></textarea>
        <button class="btn btn-outline-success mt-2 me-2" @click="editPost">Save</button>
        <router-link to="/posts" class="btn btn-outline-primary mt-2">Back</router-link>
        <p class="mt-3" v-if="message != null"> {{ message }}</p>
        <div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import PostService from "@/services/PostService";
import {useRoute} from "vue-router";

let post = ref({})
let message = ref(null)
let route = useRoute()
// let router = useRouter()

const getOnePost = () => {
  PostService.getOnePost(route.params.id)
      .then(response => post.value = response.data)
      .catch(error => console.log(error))
}

const editPost = () => {
  PostService.editPost(post.value.id, post.value)
      .then(() => message.value = 'Post was updated')
      .catch(error => console.log(error))
  // router.push('posts')
}

getOnePost()


</script>

<style scoped>

</style>