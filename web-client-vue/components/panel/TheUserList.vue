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


