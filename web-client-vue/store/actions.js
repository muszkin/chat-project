import axios from 'axios'

export default {
  getUserList ({commit, state}) {
    // let users = [
    //   {
    //     userInfo: {
    //       firstName: 'Daniel',
    //       lastName: 'Skoczny',
    //       emial: 'john.doe@example.com'
    //     },
    //     userId: 'jf5804a6',
    //     avatar: '',
    //     isActive: true,
    //     isConnected: true
    //   },
    //   {
    //     userInfo: {
    //       firstName: 'Jacek',
    //       lastName: 'Placek',
    //       emial: 'john.doe@example.com'
    //     },
    //     userId: '22222',
    //     avatar: '',
    //     isActive: false,
    //     isConnected: true
    //   },
    //   {
    //     userInfo: {
    //       firstName: 'Super',
    //       lastName: 'Agent',
    //       emial: 'john.doe@example.com'
    //     },
    //     userId: '333',
    //     avatar: '',
    //     isActive: false,
    //     isConnected: true
    //   }
    // ]
    const data = axios.get('http://54.154.209.2:8080/userMessages/all')
      .then(res => res)
      .then(json => new Promise((resolve, reject) => {
        resolve(json.data)
      })).catch((err) => {
        console.log(err)
        return {}
      })
    console.log('test')
    data.then(users => {
      console.log(users)
      // users = users.map((user, index) => {
      //   user.avatar = `https://placebear.com/30${index}/30${index}`
      //   return user
      // })
      // commit('setUserList', {users})
    })
  },
  getUserById ({commit, state}, userId) {
    const messages = [
      {
        content: 'Welcome to our chat',
        origin: 'server'
      },
      {
        content: 'No elo janusx',
        origin: 'self'
      }
    ]
    commit('setMessages', { messages })
  }
}
