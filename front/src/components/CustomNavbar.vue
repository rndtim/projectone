<template>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <div class="container mx-auto">
      <div>
        <router-link :to="{name: 'home'}" class="navbar-brand">Home</router-link>
        <router-link :to="{name: 'posts'}" class="navbar-brand">Posts</router-link>
      </div>
      <div>
        <ul class="navbar-nav me-auto mb-2 mb-md-0" v-if="!auth">
          <li class="nav-item">
            <router-link :to="{name: 'login'}" class="nav-link">Login</router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{name: 'registration'}" class="nav-link">Registration</router-link>
          </li>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-md-0" v-if="auth">
          <li class="nav-item nav-link">Hi, {{ name }}</li>
          <li class="nav-item">
            <router-link href="#" class="nav-link" @click="logout" :to="{name: 'home'}">Logout</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script setup>
  import {useStore} from "vuex";
  import {computed} from "vue";

  const store = useStore()
  const auth = computed(() => store.state.auth.status.loggedIn)
  const name = computed(() => store.state.auth.user.username)

  const logout = () => {
    store.dispatch("auth/logout")
  }

</script>

<style scoped>

</style>