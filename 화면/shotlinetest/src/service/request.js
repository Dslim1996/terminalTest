import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
//   (process.env.VUE_APP_API_URI || 'http://localhost:9990') +
//   (process.env.VUE_APP_HOST === 'iot' ? '/v1/mms' : '/api/v1/mms');
// axios.defaults.timeout = 5 * 1000;
// axios.defaults.headers['Cache-Control'] =
//   'no-cache,no-store,must-revalidate,max-age=-1,private';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';

const service = axios.create();

service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    Promise.reject(error);
  },
);
service.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    return Promise.reject(error.response);
  },
);

export default service;
