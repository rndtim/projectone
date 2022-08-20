<template>
  <div>
    <UserComponent/>
  </div>
</template>

<script>
// @ is an alias to /src
import UserComponent from '@/components/UserComponent.vue'

export default {
  name: 'HomeView',
  components: {
    UserComponent
  }
}
</script>
