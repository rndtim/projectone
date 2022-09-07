<template>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container mx-auto">
      <div>
        <router-link to="/" class="navbar-brand">Home</router-link>
        <router-link :to="{name: 'posts'}" class="navbar-brand">Posts</router-link>
      </div>
      <div>
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <router-link :to="{name: 'login'}" class="nav-link">Login</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{name: 'registration'}" class="nav-link">Registration</router-link>
          </li>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a href="#" class="nav-link">Logout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "CustomNavbar"
}
</script>

<style scoped>

</style>