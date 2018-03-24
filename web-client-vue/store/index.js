export const strict = false
export const state = () => ({
  sidebar: false,
  userList: [
    {
      userInfo: {
        firstName: 'John',
        lastName: 'Doe',
        emial: 'john.doe@example.com'
      },
      userId: 2124124,
      avatar: '/man.png'
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
  }
}
export const mutations = {
  toggleSidebar (state) {
    state.sidebar = !state.sidebar
  },
  newMessage (state, message) {
    state.messages.push(message)
  }
}
