<template>
    <v-flex xs3 order-xs1 class="scrollable the-user-list">
      <!-- <TheNavigation /> -->
      <!-- <TheUserSearchBar /> -->
      <UserCard 
      v-for="(user) in getUserList"
      :key="user.userId"
      :userId="user.userId"
      :userInfo="user.userInfo"
      :avatar="user.avatar"
      :isActive="user.isActive"
      :unreadMessages="user.unreadMessages"
      :lastSeenDate="user.lastSeenDate"
      />
    </v-flex>
</template>

<style scoped>
 .the-user-list{
   height: 80vh;
   overflow-y: auto;
   border: solid 1px #eeeeee0d;
   border-radius: 0;
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
  },
  components: {
    UserCard,
    TheUserSearchBar,
    TheNavigation
  }
}
</script>


