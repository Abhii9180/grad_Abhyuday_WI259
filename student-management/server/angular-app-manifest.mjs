
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: [
  {
    "renderMode": 2,
    "route": "/"
  },
  {
    "renderMode": 2,
    "route": "/login"
  },
  {
    "renderMode": 2,
    "route": "/students"
  },
  {
    "renderMode": 2,
    "route": "/add-student"
  },
  {
    "renderMode": 2,
    "route": "/update-student"
  },
  {
    "renderMode": 2,
    "route": "/failure"
  },
  {
    "renderMode": 2,
    "route": "/**"
  }
],
  entryPointToBrowserMapping: undefined,
  assets: {
    'index.csr.html': {size: 442, hash: '40ff4dfd0b3167760c9e77a38ac832c722f6850ce8839c5f08870e85753de11a', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 955, hash: 'e9319f8a2c0646cc1cb798e1580d3a374f2c7f3ba929c9e2b26d3ef1ac14232c', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'index.html': {size: 3559, hash: '3faeb82542e7bc45f6ab08232f0b27b978a78d68f1795c5cbfa53f05d7fef76a', text: () => import('./assets-chunks/index_html.mjs').then(m => m.default)},
    'failure/index.html': {size: 2176, hash: 'b91d0bb1b8ddcedf9e7312a5ce5c94dc4db76a3cb1ad01eb88741c4399dc5442', text: () => import('./assets-chunks/failure_index_html.mjs').then(m => m.default)},
    'login/index.html': {size: 4861, hash: '7ccffe1dbd30bace51555166ced582ef0f8a63d4f34d4183efbe722346a0ba99', text: () => import('./assets-chunks/login_index_html.mjs').then(m => m.default)},
    'styles-5INURTSO.css': {size: 0, hash: 'menYUTfbRu8', text: () => import('./assets-chunks/styles-5INURTSO_css.mjs').then(m => m.default)}
  },
};
