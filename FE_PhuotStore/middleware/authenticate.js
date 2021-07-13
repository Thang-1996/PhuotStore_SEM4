export default function ({ $auth, route, redirect }) {
  if (!$auth.$storage.getUniversal('token') && route.name !== 'login') {
    redirect('/register')
  }
  if ($auth.$storage.getUniversal('token') && route.name === 'login') {
    redirect('/')
  }
}
