import axios from 'axios'

export default {
  getUserList ({commit, state}) {
    const data = axios.get('http://54.154.209.2:8080/userMessages/all')
      .then(res => res)
      .then(json => new Promise((resolve, reject) => {
        resolve(json.data)
      })).catch((err) => {
        console.log(err)
        return {}
      })
    data.then(users => {
      const userList = users.map((data, index) => {
        let user = {}
        user.avatar = `https://placebear.com/30${index}/30${index}`
        user.userInfo = {}
        user.userId = data.userId
        if (index === 0) {
          user.isActive = true
        }
        user.unreadMessages = 0
        return user
      })
      commit('setUserList', {userList})
    })
  },
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
      let userId
      messagesList = data.messages.map((msg, index) => {
        console.log(msg.content)
        let message = {}
        message.content = msg.content
        if (msg.userId === 'admin') {
          message.origin = 'server'
        } else {
          message.origin = 'self'
          userId = msg.userId
        }
        return message
      })
      commit('setMessages', { messagesList, userId })
    })
  }
}
