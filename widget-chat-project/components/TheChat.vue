<template>
  <section class="avenue-messenger" v-if="getChatOpen">
    <div class="menu">
      <div class="items"><span>
        <a href="#" title="Minimize">&mdash;</a><br>
        <a href="#" title="End Chat">&#10005;</a>
        </span>
      </div>
      <!-- <div class="button">...</div> -->
    </div>
    <div class="agent-face">
      <div class="half">
      <img class="agent circle" src="https://placebear.com/300/300" alt="Jesse Tino"></div>
    </div>
    <div class="chat">
      <div class="chat-title">
        <h1>Teddy</h1>
        <h2>Bear</h2>
      </div>
      <div class="messages">
        <div class="messages-content">
          <div class="messages-body">
              <Message 
                v-for="(message,index) in getMessages"
                :key="index"
                :index = "index"
                :content = "message.content"
                :origin = "message.origin"
              />
          </div>
        </div>
      </div>
      <div class="message-box">
        <textarea @keyup.enter="sendMessage" v-model="selfMessageContent" type="text" class="message-input" placeholder="Type message..."></textarea>
        <button @click="sendMessage" type="submit" class="message-submit">Send</button>
      </div>
    </div>  
  </section>
</template>


<script>
  import Message from './Message.vue'
  import SockJS from 'sockjs-client'
  import Stomp from '@stomp/stompjs'
  import uniqid from 'uniqid'
  import { mapGetters } from 'vuex'
  export default {
    data () {
      return {
        messages: this.getMessages,
        newMessage: {},
        selfMessageContent: '',
        socket: null,
        stompClient: null
      }
    },
    components: {
      Message
    },
    computed: {
      ...mapGetters([
        'getMessages',
        'getChatOpen'
      ])
    },
    watch: {
      newMessage (val) {
        this.$store.commit('newMessage', val)
        this.updateScroll()
      }
    },
    methods: {
      sendMessage () {
        console.log('wysyłąnko:' + this.selfMessageContent)
        this.stompClient.send('/app/chat', {}, JSON.stringify({'content': this.selfMessageContent}))
        const msg = {
          content: this.selfMessageContent,
          origin: 'self'
        }
        this.$store.commit('newMessage', msg)
        this.updateScroll()
        this.selfMessageContent = ''
      },
      updateScroll () {
        const msgBox = document.querySelector('.messages-body')
        const bigInt = 9999999999
        setTimeout(() => {
          msgBox.scrollTop = msgBox.scrollHeight + bigInt
        }, 100)
      },
      getUserId () {
        const userId = window.localStorage.getItem('_userIdChatProject');
        if(!userId){
          const userId = uniqid();
          window.localStorage.setItem('_userIdChatProject', userId);
          return userId;
        }
        return userId
      },
      getBrowserLang () {
        return window.navigator.userLanguage || window.navigator.language;
      }
    },
    mounted () {
      const userId = this.getUserId();
      const browserLang = this.getBrowserLang()
      this.socket = new SockJS('http://54.154.209.2:8080/register')
      this.stompClient = Stomp.over(this.socket)
      this.stompClient.connect({
        'user-id' : userId,
        'browser-lang' : browserLang
      }, (frame) => {
        const msg = {
          content: 'Connected to server',
          origin: 'server'
        }
        this.$store.commit('newMessage', msg)
        var sessionId = this.socket._transport.url.split('/')[5]
        this.stompClient.subscribe('/topic/private/' + userId, (resp) => {
          let jsonResp = JSON.parse(resp.body)
          this.newMessage = {
            content: jsonResp.content,
            origin: 'server'
          }
        })
      })
    }
  }
</script>



<style>
/*--------------------
Chat
--------------------*/
.chat {
  position: relative;
  width: 100%;
  height: calc(100% - 15px);
  max-height: 500px;
  z-index: 10;
  overflow: hidden;
  background: transparent;
  border-radius: 20px;
  -webkit-border-radius: 20px;
  -moz-border-radius: 20px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: justify;
      -ms-flex-pack: justify;
          justify-content: space-between;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
      -ms-flex-direction: column;
          flex-direction: column;
}

/*--------------------
Chat Title
--------------------*/
.chat-title {
  -webkit-box-flex: 0;
      -ms-flex: 0 1 45px;
          flex: 0 1 45px;
  position: relative;
  z-index: 2;
  width: 100%;
  border-bottom: 1px solid #ccc;
  color: #777;
  padding-top: 50px;
  padding-bottom: 5px;
  background-color: #fff;
  text-transform: uppercase;
  text-align: center;
}
.chat-title h1,
.chat-title h2 {
  font-weight: normal;
  font-size: 14px;
  margin: 0;
  padding: 0;
}
.chat-title h2 {
  /* color: rgba(255, 255, 255, .8);*/
  font-size: 11px;
  letter-spacing: 1px;
}
.chat-title .avatar {
  position: absolute;
  z-index: 1;
  top: 8px;
  left: 9px;
  border-radius: 30px;
  width: 60px;
  height: 60px;
  overflow: hidden;
  margin: 0;
  padding: 0;
  border: 1px solid #fff;
}
.chat-title .avatar img {
  width: 100%;
  height: auto;
}

/*--------------------
Messages
--------------------*/
.messages {
  -webkit-box-flex: 1;
      -ms-flex: 1 1 auto;
          flex: 1 1 auto;
  /*  color: rgba(255, 255, 255, .5);
  color: #fff;*/
  overflow: hidden;
  position: relative;
  width: 100%;
}
.messages .messages-content {
  position: absolute;
  top: 0;
  left: 0;
  height: 101%;
  width: 100%;
}
.messages .message {
  clear: both;
  float: left;
  padding: 6px 10px 7px;
  border-radius: 20px 20px 20px 0;
  background: #eee;
  /*rgba(0, 0, 0, 0.1);*/
  margin: 8px 0;
  font-size: 14px;
  line-height: 1.4;
  margin-left: 35px;
  position: relative;
  border: 1px solid #ccc;
  /*text-shadow: 0 1px 1px rgba(0, 0, 0, .2);*/
}
.messages .message .timestamp {
  position: absolute;
  bottom: -15px;
  font-size: 10px;
  color: #555;
  right: 30px;
  /* color: rgba(255, 255, 255, .7);*/
}
.messages .message .checkmark-sent-delivered {
  position: absolute;
  bottom: -15px;
  right: 10px;
  font-size: 12px;
  color: #999;
}
.messages .message .checkmark-read {
  color: blue;
  position: absolute;
  bottom: -15px;
  right: 16px;
  font-size: 12px;
}
.messages .message .avatar {
  position: absolute;
  z-index: 1;
  bottom: -15px;
  left: -35px;
  border-radius: 30px;
  width: 30px;
  height: 30px;
  overflow: hidden;
  margin: 0;
  padding: 0;
  border: 2px solid rgba(255, 255, 255, 0.5);
}
.messages .message .avatar img {
  width: 100%;
  height: auto;
}
.messages .message.message-personal {
  float: right;
  text-align: right;
  background: #fff;
  border: 1px solid #ccc;
  border-radius: 20px 20px 0 20px;
}

.messages .message:last-child {
  margin-bottom: 30px;
}
.messages .message.new {
  transform: scale(0);
  transform-origin: 0 0;
  animation: bounce 500ms linear both;
}
.messages .message.loading::before {
  position: relative;
  content: '';
  display: block;
  width: 5px;
  height: 5px;
  border-radius: 50%;
  background: #888;
  z-index: 2;
  margin-top: 4px;
  animation: ball 0.45s cubic-bezier(0, 0, 0.15, 1) alternate infinite;
  border: none;
  animation-delay: .15s;
}
.messages .message.loading span {
  display: block;
  font-size: 0;
  width: 20px;
  height: 10px;
  position: relative;
}
.messages .message.loading span::before {
  position: relative;
  content: '';
  display: block;
  width: 5px;
  height: 5px;
  border-radius: 50%;
  background: #888;
  z-index: 2;
  margin-top: 4px;
  animation: ball 0.45s cubic-bezier(0, 0, 0.15, 1) alternate infinite;
  margin-left: -7px;
}
.messages .message.loading span::after {
  position: relative;
  content: '';
  display: block;
  width: 5px;
  height: 5px;
  border-radius: 50%;
  background: #888;
  z-index: 2;
  margin-top: 4px;
  animation: ball 0.45s cubic-bezier(0, 0, 0.15, 1) alternate infinite;
  margin-left: 7px;
  animation-delay: .3s;
}

/*--------------------
Message Box
--------------------*/
.message-box {
  flex: 0 1 42px;
  width: 90%;
  background: #fff;
  margin: 2px auto;
  padding: 10px;
  position: relative;
  border-radius: 20px;
  height: 14px;
  border: 1px solid #ccc;
}
.message-box .message-input {
  background: none;
  border: none;
  outline: none !important;
  resize: none;
  font-size: 15px;
  height: 24px;
  margin: 0;
  padding-right: 20px;
  width: 265px;
  color: #444;
}
.message-box textarea:focus:-webkit-placeholder {
  color: transparent;
}
.message-box .message-submit {
  position: absolute;
  z-index: 1;
  top: 9px;
  right: 10px;
  color: #4A90E2;
  border: none;
  background: #fff;
  font-size: 12px;
  text-transform: uppercase;
  line-height: 1;
  padding: 6px 10px;
  border-radius: 5px;
  outline: none !important;
  -webkit-transition: background .2s ease;
  transition: background .2s ease;
  cursor: pointer;
}
.message-box .message-submit:hover {
  background: #fff;
  color: #333;
}

/*--------------------
Custom Srollbar
--------------------*/
.mCSB_scrollTools {
  margin: 1px -3px 1px 0;
  opacity: 0;
}

.mCSB_inside > .mCSB_container {
  margin-right: 0px;
  padding: 0 10px;
}

.mCSB_scrollTools .mCSB_dragger .mCSB_dragger_bar {
  background-color: rgba(0, 0, 0, 0.5) !important;
}

/*--------------------
Bounce
--------------------*/
@-webkit-keyframes bounce {
  0% {
    -webkit-transform: matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  4.7% {
    -webkit-transform: matrix3d(0.45, 0, 0, 0, 0, 0.45, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.45, 0, 0, 0, 0, 0.45, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  9.41% {
    -webkit-transform: matrix3d(0.883, 0, 0, 0, 0, 0.883, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.883, 0, 0, 0, 0, 0.883, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  14.11% {
    -webkit-transform: matrix3d(1.141, 0, 0, 0, 0, 1.141, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.141, 0, 0, 0, 0, 1.141, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  18.72% {
    -webkit-transform: matrix3d(1.212, 0, 0, 0, 0, 1.212, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.212, 0, 0, 0, 0, 1.212, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  24.32% {
    -webkit-transform: matrix3d(1.151, 0, 0, 0, 0, 1.151, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.151, 0, 0, 0, 0, 1.151, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  29.93% {
    -webkit-transform: matrix3d(1.048, 0, 0, 0, 0, 1.048, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.048, 0, 0, 0, 0, 1.048, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  35.54% {
    -webkit-transform: matrix3d(0.979, 0, 0, 0, 0, 0.979, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.979, 0, 0, 0, 0, 0.979, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  41.04% {
    -webkit-transform: matrix3d(0.961, 0, 0, 0, 0, 0.961, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.961, 0, 0, 0, 0, 0.961, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  52.15% {
    -webkit-transform: matrix3d(0.991, 0, 0, 0, 0, 0.991, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.991, 0, 0, 0, 0, 0.991, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  63.26% {
    -webkit-transform: matrix3d(1.007, 0, 0, 0, 0, 1.007, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.007, 0, 0, 0, 0, 1.007, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  85.49% {
    -webkit-transform: matrix3d(0.999, 0, 0, 0, 0, 0.999, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.999, 0, 0, 0, 0, 0.999, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  100% {
    -webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
}
@keyframes bounce {
  0% {
    -webkit-transform: matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  4.7% {
    -webkit-transform: matrix3d(0.45, 0, 0, 0, 0, 0.45, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.45, 0, 0, 0, 0, 0.45, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  9.41% {
    -webkit-transform: matrix3d(0.883, 0, 0, 0, 0, 0.883, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.883, 0, 0, 0, 0, 0.883, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  14.11% {
    -webkit-transform: matrix3d(1.141, 0, 0, 0, 0, 1.141, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.141, 0, 0, 0, 0, 1.141, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  18.72% {
    -webkit-transform: matrix3d(1.212, 0, 0, 0, 0, 1.212, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.212, 0, 0, 0, 0, 1.212, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  24.32% {
    -webkit-transform: matrix3d(1.151, 0, 0, 0, 0, 1.151, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.151, 0, 0, 0, 0, 1.151, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  29.93% {
    -webkit-transform: matrix3d(1.048, 0, 0, 0, 0, 1.048, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.048, 0, 0, 0, 0, 1.048, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  35.54% {
    -webkit-transform: matrix3d(0.979, 0, 0, 0, 0, 0.979, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.979, 0, 0, 0, 0, 0.979, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  41.04% {
    -webkit-transform: matrix3d(0.961, 0, 0, 0, 0, 0.961, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.961, 0, 0, 0, 0, 0.961, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  52.15% {
    -webkit-transform: matrix3d(0.991, 0, 0, 0, 0, 0.991, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.991, 0, 0, 0, 0, 0.991, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  63.26% {
    -webkit-transform: matrix3d(1.007, 0, 0, 0, 0, 1.007, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1.007, 0, 0, 0, 0, 1.007, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  85.49% {
    -webkit-transform: matrix3d(0.999, 0, 0, 0, 0, 0.999, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(0.999, 0, 0, 0, 0, 0.999, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
  100% {
    -webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
            transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  }
}
@-webkit-keyframes ball {
  from {
    -webkit-transform: translateY(0) scaleY(0.8);
            transform: translateY(0) scaleY(0.8);
  }
  to {
    -webkit-transform: translateY(-10px);
            transform: translateY(-10px);
  }
}
@keyframes ball {
  from {
    -webkit-transform: translateY(0) scaleY(0.8);
            transform: translateY(0) scaleY(0.8);
  }
  to {
    -webkit-transform: translateY(-10px);
            transform: translateY(-10px);
  }
}
.avenue-messenger {
  opacity: 1;
  border-radius: 10px;
  height: calc(100% - 60px) !important;
  max-height: 460px !important;
  min-height: 220px !important;
  width: 320px;
  background: rgba(255, 255, 255, 0.9);
  position: fixed;
  right: 20px;
  bottom: 80px;
  margin: auto;
  z-index: 10;
  transition: 0.3s all ease-out 0.1s, transform 0.2s ease-in;
  box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
}

.avenue-messenger div.agent-face {
  position: absolute;
  left: 0;
  top: -50px;
  right: 0;
  margin: auto;
  width: 101px;
  height: 50px;
  background: transparent;
  z-index: 12;
}

.avenue-messenger div {
  font-size: 14px;
  color: #232323;
}

.close {
  display: block;
  width: 100px;
  height: 100px;
  margin: 1em auto;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  border-radius: 99em;
  opacity: 0.5;
}

.close:hover {
  opacity: 0.9;
}

.circle {
  display: block;
  width: 80px;
  height: 80px;
  margin: 1em auto;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  border-radius: 99em;
  border: 2px solid #fff;
}

.contact-icon .circle:hover {
  box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
  -moz-box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
  transition: 0.2s all ease-out 0.2s;
  -webkit-transition: 0.2s all ease-out 0.2s;
  -moz-transition: 0.2s all ease-out 0.2s;
}

.arrow_box:after {
  border-color: rgba(255, 255, 255, 0);
  border-left-color: #fff;
  border-width: 5px;
  margin-top: -5px;
}

.arrow_box {
  position: relative;
  background: #fff;
  border: 1px solid #4A90E2;
}

.arrow_box:after, .arrow_box:before {
  left: 100%;
  top: 50%;
  border: solid transparent;
  content: " ";
  height: 0;
  width: 0;
  position: absolute;
  pointer-events: none;
}


.menu .items span {
  color: #111;
  z-index: 12;
  font-size: 14px;
  text-align: center;
  position: absolute;
  right: 0;
  top: 40px;
  height: 86px;
  line-height: 40px;
  background: #fff;
  border-left: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  border-right: 1px solid #ccc;
  width: 48px;
  opacity: 0;
  border-bottom-left-radius: 20px;
  border-bottom-right-radius: 20px;
  transition: .3s all ease-in-out;
  -webkit-transition: .3s all ease-in-out;
  -moz-transition: .3s all ease-in-out;
}

.menu .button {
  font-size: 30px;
  z-index: 12;
  text-align: right;
  color: #333;
  content: "...";
  display: block;
  width: 48px;
  line-height: 25px;
  height: 40px;
  border-top-right-radius: 20px;
  position: absolute;
  right: 0;
  padding-right: 10px;
  cursor: pointer;
  transition: .3s all ease-in-out;
  -webkit-transition: .3s all ease-in-out;
  -moz-transition: .3s all ease-in-out;
}

.menu .button.active {
  background: #ccc;
}
.menu .items span.active {
  opacity: 1;
}

.menu .items a {
  color: #111;
  text-decoration: none;
}

.menu .items a:hover {
  color: #777;
}

.messages-body {
  padding: 20px 10px;
  overflow: auto;
  max-height: 100%;
}

@media only screen and (max-device-width: 667px), screen and (max-width: 450px) {
  .avenue-messenger {
    z-index: 2147483001 !important;
    width: calc(100% - 15px) !important;
    height: calc(100% - 100px) !important;
    max-height: none !important;
    margin: 10px;
    top: 0 !important;
    left: 0 !important;
    right: 0 !important;
    bottom: 150px !important;
    background: #fff;
    box-shadow: 0 3px 7px rgba(0, 0, 0, 0.3);
  }

  .avenue-messenger div.agent-face {
    top: -10px !important;
  }

  .chat {
    border-radius: 0 !important;
    max-height: initial !important;
  }

  .chat-title {
    padding: 20px 20px 15px 10px !important;
    text-align: left;
  }

  .circle {
    width: 80px;
    height: 80px;
    border: 1px solid #fff;
  }
  .menu .button {
    border-top-right-radius: 0;
  }
}
@media only screen and (min-device-width: 667px) {
  div.half {
    margin: auto;
    width: 80px;
    height: 40px;
    background-color: #fff;
    border-top-left-radius: 60px;
    border-top-right-radius: 60px;
    border-bottom: 0;
    box-shadow: 1px 4px 20px rgba(22, 20, 19, 0.6);
  }
}

</style>
