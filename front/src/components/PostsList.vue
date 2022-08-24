<template>
  <section>
    <div class="container mx-auto">
      <div class="row">
        <div class="col-md-8">
          <h4>Posts List</h4>
          <div class="col-lg-6">
            <input class="form-control" type="text" size="30" placeholder="User id" v-model="userId">
            <button class="btn btn-outline-primary mt-2" @click="showAllPosts">Show user's posts</button>
          </div>
          <ul class="list-group mt-3 col-lg-6 ">
            <li class="list-group-item"
                v-for="post in posts"
                :key="post.id"
            >
              <div>
                {{ post.title }}
              </div>
              <div>
                {{ post.description }}
              </div>
              <button class="btn btn-outline-primary mt-2" @click="deletePost(post.id)">Delete post</button>
            </li>
          </ul>
          <div class="mt-3 col-lg-4">
            <p>{{ postDeleted }}</p>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-6">
            <label for="inputTitle" class="form-label">Title</label>
            <input id="inputTitle" class="form-control" type="text" size="30" placeholder="Title" v-model="title">
            <label for="inputDescription" class="form-label">Description</label>
            <!--            <input id="inputDescription" class="form-control mt-1" type="text" size="30" placeholder="Description" v-model="description">-->
            <textarea class="form-control" id="inputDescription" rows="3" placeholder="Description"
                      v-model="description"></textarea>
            <button class="btn btn-outline-primary mt-2" @click="createPost">Create Post</button>
            <p class="mt-3"> {{ postCreated }} </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import PostService from "@/services/PostService";
import {ref} from "vue";

let userId = ref(null)
let title = ref(null)
let description = ref(null)
let posts = ref([])
let postCreated = ref(null)
let postDeleted = ref(null)

const showAllPosts = async () => {
  await PostService.getAllByUserId(userId.value)
      .then(res => {
        posts.value = res.data
      })
}

const createPost = async () => {
  let data = {
    title: title.value,
    description: description.value
  }
  await PostService.createPost(data, 4)
      .then(() => postCreated.value = 'Post was created')
      .catch(err => console.log(err))

  title.value = ''
  description.value = ''
}

const deletePost = async (postId) => {
  await PostService.deletePost(postId)
      .then(() => {
        posts.value = posts.value.filter((post) => post.id !== postId)
        postDeleted.value = 'Post was deleted'
      })
      .catch(err => console.log(err))
}

// onMounted(() => {
//   showAllPosts()
// })
</script>

<style scoped>

</style>