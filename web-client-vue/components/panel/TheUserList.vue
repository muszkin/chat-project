<template>
    <v-flex xs3 order-xs1 class="scrollable the-user-list">
      <TheUserSearchBar />
      <UserCard 
      v-for="(user,index) in getUserList"
      :key="index"
      :userName="user.userName"
      :userSurname="user.userSurname"
      :messagesCount="user.messagesCount"
      :lastMessage="user.lastMessage"
      :imageSrc="user.imageSrc"
      />
    </v-flex>
</template>

<style scoped>
 .the-user-list{
   background: red;
   height: 90vh;
   overflow-y: auto
 }
</style>

<script>
import UserCard from './user-list/UserCard'
import TheUserSearchBar from './user-list/TheUserSearchBar'
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
  data () {
    return {
      userList: [
        {
          userName: 'Adam',
          userSurname: 'Małecki',
          messagesCount: 10,
          lastMessage: 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Recusandae inventore veniam illo veritatis quia.',
          imageSrc: '/man.png'
        }
      ]
    }
  },
  components: {
    UserCard,
    TheUserSearchBar
  }
}
</script>


