import axios from 'axios'
export default {
    getUserById ({commit, state}, userId) {
        const data = axios.get('http://54.154.209.2:8080/userMessages/single?userId=' + userId)
          .then(res => res)
          .then(json => new Promise((resolve, reject) => {
            resolve(json.data)
          })).catch((err) => {
            console.log(err)
            return {}
          })
        data.then(data => {
          let messagesList = []
          messagesList = data.messages.map((msg, index) => {
            console.log(msg.content)
            let message = {}
            message.content = msg.content
            if (msg.userId === 'admin') {
              message.origin = 'server'
            } else {
              message.origin = 'self'
            }
            return message
          })
          commit('setMessages', { messagesList })
        })
      }
}