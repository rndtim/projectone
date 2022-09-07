<template>
  <div class="container mx-auto">
    <div class="row">
      <h1>Add Post</h1>
      <div class="col-lg-6">
        <label for="inputTitle" class="form-label">Title</label>
        <input id="inputTitle" class="form-control" type="text" size="30" placeholder="Title" v-model="title">
        <label for="inputDescription" class="form-label">Description</label>
        <textarea class="form-control" id="inputDescription" rows="3" placeholder="Description"
                  v-model="description"></textarea>
        <button class="btn btn-outline-primary mt-2" @click="createPost">Create new post</button>
        <p class="mt-3" v-if="postCreated != null"> {{ postCreated }} </p>
        <div>
          <router-link to="/posts" class="btn btn-outline-primary mt-2">Back</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import PostService from "@/services/PostService";

let title = ref(null)
let description = ref(null)
let postCreated = ref(null)

const createPost = () => {
  let data = {
    title: title.value,
    description: description.value
  }
  PostService.createPost(data)
      .then(() => postCreated.value = 'Post was created')
      .catch(error => console.log(error))

  title.value = ''
  description.value = ''
}
</script>

<style scoped>

</style>