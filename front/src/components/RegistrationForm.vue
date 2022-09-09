<template>
  <div class="container mx-auto">
    <div class="form-signin">
      <form @submit.prevent="registration">
        <h1 class="h3 mb-3 fw-normal">Registration</h1>

        <input v-model="data.username" class="form-control" placeholder="Username" required>

        <input v-model="data.email" type="email" class="form-control" placeholder="Email" required>

        <input v-model="data.password" type="password" class="form-control" placeholder="Password" required>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Sign Up</button>
      </form>
    </div>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
const store = useStore();

let data = ref({
  username: '',
  email: '',
  password: ''
})

const router = useRouter();

const registration = () => {
  console.log('registration')
  store.dispatch("auth/registration", data.value)
      .then(() => router.push({name: 'login'}))
      .catch((error) => console.log(error))
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