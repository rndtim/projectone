<template>
  <div class="container mx-auto">
    <div class="form-signin">
      <form @submit.prevent="login">
        <h1 class="h3 mb-3 fw-normal">Login</h1>

        <input v-model="credentials.username" class="form-control" placeholder="Username" required>

        <input v-model="credentials.password" type="password" class="form-control" placeholder="Password" required>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Login</button>
      </form>
    </div>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {useRouter} from "vue-router";
import AuthService from "@/services/AuthService";

const router = useRouter();
let credentials = ref({
  username: '',
  password: ''
})

const login = () => {
  console.log('login')
  AuthService.login(credentials.value)
      .then(response => {
        localStorage.setItem('user', JSON.stringify(response.data))
      })
      .catch(error => console.error(error))

  router.push({name: 'posts'})
}
</script>

<style scoped>
.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}

.form-signin .form-control:focus {
  z-index: 2;
}

.form-signin input {
  margin-bottom: 5px;
}

.form-signin input[type="password"] {
  margin-bottom: 15px;
}
</style>