<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-card class="user-card">
        <v-container fluid grid-list-lg>
            <v-layout row>
              <v-flex xs3>
                <v-card-media
                class="user-pic"
                :src="imageSrc"
                height="50px"
                contain
                ></v-card-media>
              </v-flex>
              <v-flex xs9>
                <div class="user-details">
                  <h5>{{userName}} {{userSurname}}</h5>
                  <h6>{{ lastMessage }}</h6>
                  <span v-if="!!messagesCount" class="msg-count">{{ messagesCount }}</span>
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
    props: ['userName', 'userSurname', 'messagesCount', 'lastMessage', 'imageSrc']
  }
</script>

<style scoped>
.active {
  color: #ffffff;
  background: #607D8B;
}
.user-card{
  color: #0e0e0e;
  background-color: #CFD8DC;
  border: 1px solid #eeeeee;
}

.user-card:hover{
  background:#B0BEC5;
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

.user-details span.msg-count {
  position: absolute;
  top: 0;
  font-size: 12px;
  right: 0;
  border-radius: 20px;
  padding: 0px 5px;
  background: #90A4AE
}


.user-pic {
  width: 50px;
  height: auto;
  border-radius: 50px;
  display: block;
}
</style>


