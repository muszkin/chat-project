export const strict = false
export const state = () => ({
  sidebar: false,
  userList: [
    {
      userInfo: {
        firstName: 'Daniel',
        lastName: 'Skoczny',
        emial: 'john.doe@example.com'
      },
      userId: 'jf5804a6',
      avatar: '/man.png',
      isActive: true
    },
    {
      userInfo: {
        firstName: 'Jacek',
        lastName: 'Placek',
        emial: 'john.doe@example.com'
      },
      userId: '22222',
      avatar: '/man.png',
      isActive: false
    },
    {
      userInfo: {
        firstName: 'Super',
        lastName: 'Agent',
        emial: 'john.doe@example.com'
      },
      userId: '333',
      avatar: '/man.png',
      isActive: false
    }
  ],
  messages: [
    {
      content: 'Welcome to our chat',
      origin: 'server'
    },
    {
      content: 'No elo',
      origin: 'self'
    }
  ]
})
export const getters = {
  getUserList (state) {
    return state.userList
  },
  getMessages (state) {
    return state.messages
  },
  getMessagesLength (state) {
    return state.messages.length
  },
  getActiveUserId (state) {
    return state.userList.find(user => user.isActive).userId
  }
}
export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  newMessage (state, message) {
    state.messages.push(message)
  },
  changeActiveUser (state, userId) {
    state.userList = state.userList.map(user => {
      user.userId === userId ? user.isActive = true : user.isActive = false
      return user
    })
  },
  clearMessageList (state) {
    state.messages = []
  }
}
