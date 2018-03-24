export default {
  getUserList ({commit, state}) {
    let users = [
      {
        userInfo: {
          firstName: 'Daniel',
          lastName: 'Skoczny',
          emial: 'john.doe@example.com'
        },
        userId: 'jf5804a6',
        avatar: '',
        isActive: true
      },
      {
        userInfo: {
          firstName: 'Jacek',
          lastName: 'Placek',
          emial: 'john.doe@example.com'
        },
        userId: '22222',
        avatar: '',
        isActive: false
      },
      {
        userInfo: {
          firstName: 'Super',
          lastName: 'Agent',
          emial: 'john.doe@example.com'
        },
        userId: '333',
        avatar: '',
        isActive: false
      }
    ]
    users = users.map((user, index) => {
      user.avatar = `https://placebear.com/30${index}/30${index}`
      return user
    })
    commit('setUserList', {users})
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
