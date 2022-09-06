<template>
  <div class="container mx-auto">
    <div class="col-lg-6">
      <div class="card card-container text-center">
        <img
            id="profile-img"
            src="../assets/img/profile_pic.jpg"
            class="img-thumbnail"
            alt="profile picture"/>
        <form @submit.prevent="registration">
          <div>
            <div class="form-group">
              <label for="username">Username</label>
              <input name="username" type="text" class="form-control"/>
            </div>
            <div class="form-group">
              <label for="email">Email</label>
              <input name="email" type="email" class="form-control"/>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input name="password" type="password" class="form-control"/>
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-block">
                Sign Up
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import UserService from "@/services/UserService";
import {ref} from "vue";

let username = ref(null);
let email = ref(null);
let password = ref(null);

const registration = () => {
  let data = {
    username: username.value,
    email: email.value,
    password: password.value
  }
  UserService.registration(data)
}
</script>

<style scoped>
img {
  width: 150px;
  height: 150px;
}
</style>