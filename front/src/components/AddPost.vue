<template>
  <div class="container mx-auto">
    <div class="row">
      <h1>Add Post</h1>
      <div class="col-lg-6">
        <label for="inputTitle" class="form-label">Title</label>
        <input id="inputTitle" class="form-control" type="text" size="30" placeholder="Title" v-model="title">
        <label for="inputDescription" class="form-label">Description</label>
        <textarea class="form-control" id="inputDescription" rows="10" placeholder="Description"
                  v-model="description"></textarea>
        <label for="inputImage" class="form-label">Image</label>
        <input id="inputImage" class="form-control" type="file" @change="addImage">
        <button class="btn btn-success mt-2" @click="createPost">Add new Cocktail</button>
        <h4 class="mt-3" v-if="postCreated != null"> {{ postCreated }} </h4>
        <div>
          <router-link to="/posts" class="btn btn-primary mt-2">Back</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import PostService from "@/services/PostService";

let title = ref(null)
let description = ref(null)
let postCreated = ref(null)
let image = ref(null)

const addImage = (e) => {
  let files = e.target.files || e.dataTransfer.files;
  if (!files.length)
    return;
  image.value = files[0];
  // console.log(image.value)
}

const createPost = () => {
  let data = reactive({
      title: title.value,
      description: description.value
  })

  let formData = new FormData();
  formData.append("post", new Blob([JSON.stringify(data)], {type: "application/json"}));
  formData.append("image", image.value);
  PostService.createPost(formData)
      .then(() => {
        // console.log(response.data);
        postCreated.value = 'Post was created';
      })
      .catch(error => console.log(error))

  title.value = ''
  description.value = ''
  image.value = null
}
</script>

<style scoped>

</style>