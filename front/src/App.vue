<template>
  <nav class="navbar navbar-expand-lg bg-light">
    <div class="container mx-auto">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link to="/" class="nav-link active">Home</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/posts" class="nav-link">Posts</router-link>
        </li>
      </ul>
    </div>
  </nav>
  <router-view/>
</template>

<style>

</style>
