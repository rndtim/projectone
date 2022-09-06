<template>
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
        <div>
          <router-link :to="'/posts/' + post.id" class="btn btn-outline-warning me-3">Edit</router-link>
          <button class="btn btn-danger" @click="deletePost(post.id)">Delete</button>
        </div>
      </li>
    </ul>
    <div class="mt-3 col-lg-4" v-else>
      <p>You don't have posts</p>
    </div>
    <div class="mt-3 col-lg-4" v-if="messageDelete != null">
      <p>{{ messageDelete }}</p>
    </div>
  </div>
</template>

<script setup>


</script>

<style scoped>

</style>