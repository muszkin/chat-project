<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card class="user-card" v-bind:class="{ active: isActive }">
        <v-container fluid grid-list-lg v-on:click="onUserSelect">
            <v-layout row >
              <v-flex xs2 class="pic-block">
                <v-card-media
                class="user-pic"
                :src="avatar"
                height="50px"
                contain
                ></v-card-media>
                <!-- <span v-if="!!messagesCount" class="msg-count">{{ messagesCount }}</span> -->
              </v-flex>
              <v-flex xs10>
                <div class="user-details">
                  <h5>{{ userId }} <sup v-if="unreadMessages !== 0">{{ unreadMessages }}</sup></h5>
                  <h6>Last Seen: {{ new Date(lastSeenDate) }}</h6>
                  <!-- przygotować -->
                  <!-- <h6>{{ lastMessage }}</h6> -->
                  <!-- dopisać metode do wybierania informacji o last seen na postawie daty -->
                  <!-- <span class="last-seen">{{lastSeen}}</span> --> 
                </div>
              </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    props: {
      userId: {
        type: String,
        required: true
      },
      userInfo: {
        type: Object
      },
      avatar: {
        type: String
      },
      isActive: {
        type: Boolean
      },
      unreadMessages: {
        type: Number
      },
      lastSeenDate: {
        type: Number
      }
    },
    methods: {
      updateScroll () {
        const msgBox = document.querySelector('.messages')
        const bigInt = 9999999999
        setTimeout(() => {
          msgBox.scrollTop = msgBox.scrollHeight + bigInt
        }, 200)
      },
      onUserSelect () {
        this.$store.commit('changeActiveUser', this.userId)
        this.$store.commit('clearMessageList')
        this.$store.dispatch('getUserById', this.userId)
        setTimeout(() => {
          this.updateScroll()
        }, 200)
      }
    }
  }
</script>

<style scoped>
.active {
  border-bottom: 5px solid #ffeb3b!important;
}
sup {
    background: #d02c2c;
    padding: 5px 8px;
    border-radius: 100%;
}
.user-card{
  color: #ffffff;
  background-color: #2b646b;
  border-bottom: 5px solid #2b646b;
  margin: 0px 1px;
  margin-bottom: 5px;
  transition-duration: .7s;
}

.user-card:hover{
  background:#969696;
  border-bottom: 5px solid #969696;
  transition-duration: .7s;
  cursor: pointer;
}

.user-details {
  position: relative;
  margin-left: 20px;
}
.user-details h5 {
  font-weight: 500;
  font-size: 15px
}
.user-details h6 {
  font-weight: 300;
  font-size: 12px;
  width: 150px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

span.msg-count {
  position: absolute;
  top: 0;
  font-size: 13px;
  right: 0;
  border-radius: 40px;
  padding: 2px 6px;
  background: #ff5a5f
}


.user-pic {
  width: 50px;
  height: auto;
  border-radius: 50px;
  display: block;
}

.pic-block {
  position: relative;
}

.last-seen {
  position: absolute;
  top: 0;
  right: 0;
  padding: 2px 10px;
  border-radius: 20px;
  background: #737373
}
</style>


