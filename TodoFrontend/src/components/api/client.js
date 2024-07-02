import axios from 'axios'
export default  axios.create({
    // baseURL:"http://192.168.198.163:5000",
    baseURL:"http://localhost:8080",

}
)

// const instance = axios.create({
//     baseURL: "http://localhost:8080",
    
//   });

//   instance.interceptors.request.use(
//     (config) => {
//       let token = localStorage.getItem("token") || "";
//       config.headers = {
        
//         Authorization:`Bearer ${token}`
//       };
  
//       return config;
//     },
//     (error) => Promise.reject(error)
//   );

//   export const client = instance;
//   export default client;