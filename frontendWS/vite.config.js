import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],

  //added proxy. and now we don't need a @CrossOrigin anotation in backend 
  server:{
    proxy:{
      '/api': 'http://localhost:8080'
    }
  }
})
