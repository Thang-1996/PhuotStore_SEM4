export default function ({ $auth, route, redirect }) {
  if (!$auth.$storage.getUniversal('token') && route.name !== 'login') {
    redirect('/login')
  }
}
