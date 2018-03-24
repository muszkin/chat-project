<template>
    <v-flex xs3 order-xs1 class="scrollable the-user-list">
      <TheNavigation />
      <TheUserSearchBar />
      <UserCard 
      v-for="(user) in getUserList"
      :key="user.userId"
      :userId="user.userId"
      :userInfo="user.userInfo"
      :avatar="user.avatar"
      />
    </v-flex>
</template>

<style scoped>
 .the-user-list{
   background: #424242;
   height: 80vh;
   overflow-y: auto;
   border: solid 1px #eeeeee0d;
   border-radius: 5px;
   box-shadow: 1px 1px 3px 0px rgb(133, 133, 133);
 }
</style>

<script>
import UserCard from './user-list/UserCard'
import TheUserSearchBar from './user-list/TheUserSearchBar'
import TheNavigation from './user-list/TheNavigation'
import { mapGetters } from 'vuex'
export default {
  computed: {
    ...mapGetters([
      'getUserList'
    ])
  },
  mounted () {
    console.log(this.getUserList)
  },
  components: {
    UserCard,
    TheUserSearchBar,
    TheNavigation
  }
}
</script>


