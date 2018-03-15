<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card class="user-card">
        <v-container fluid grid-list-lg>
            <v-layout row>
              <v-flex xs2 class="pic-block">
                <v-card-media
                class="user-pic"
                :src="imageSrc"
                height="50px"
                contain
                ></v-card-media>
                <span v-if="!!messagesCount" class="msg-count">{{ messagesCount }}</span>
              </v-flex>
              <v-flex xs10>
                <div class="user-details">
                  <h5>{{userName}} {{userSurname}}</h5>
                  <h6>{{ lastMessage }}</h6>
                  <span class="last-seen">{{lastSeen}}</span>
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
    props: ['userName', 'userSurname', 'messagesCount', 'lastMessage', 'imageSrc', 'lastSeen']
  }
</script>

<style scoped>
.active {
  color: #ffffff;
  background: #607D8B;
}
.user-card{
  color: #ffffff;
  background-color: #424242;
  border-top: 1px solid rgb(133, 133, 133);
  border-radius: 5px;
  margin: 0px 1px;
}

.user-card:hover{
  background:#4f4f4f;
  /* color: #0e0e0e; */
  cursor: pointer;
}

.user-details {
  position: relative;
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


