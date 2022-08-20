<template>
  <section>
    <div class="container mx-auto">
      <h1> Information from back</h1>
      <!--      <ul class="">-->
      <!--        <li v-for="user in users" :key="user.id">-->
      <!--          {{ user.name }} - {{ user.email }}-->
      <!--          <ol class="">-->
      <!--            <li v-for="post in user.posts" :key="post.id">-->
      <!--              {{ post.title }} - {{ post.description }}-->
      <!--            </li>-->
      <!--          </ol>-->
      <!--        </li>-->
      <!--      </ul>-->
      <h4>Enter ID of user to see posts</h4>
      <div class="input-group row">
        <div class="col-md-3">
          <input class="form-control" type="text" size="30" placeholder="User id" v-model="userId">
          <button class="btn btn-outline-primary mt-1" @click="getOneUserById">Show one user</button>
        </div>
        <div class="row mt-2">
          <ul class="list-group col-md-4" v-if="oneUser">
            <li class="list-group-item">{{ oneUser.name }} - {{ oneUser.email }}</li>
            <ul class="list-group list-group-numbered">
              <li class="list-group-item" v-for="post in oneUser.posts" :key="post.id">
                {{ post.title }}
                <ul class="list-group">
                  <li class="list-group-item">
                    {{ post.description }}
                  </li>
                </ul>
              </li>

            </ul>
          </ul>
          <p v-else>No user information to show</p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'UserComponent',
  data() {
    return {
      userId: null,
      oneUser: null,
      users: []
    }
  },
  methods: {
    async getOneUserById() {
      await fetch(`api/users/${this.userId}`)
          .then(res => res.json())
          .then(data => this.oneUser = data)
    },
    async makePost() {
      await fetch('api/posts')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
