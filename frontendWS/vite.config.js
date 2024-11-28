import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import {fileURLToPath,URL} from "node:url"



// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],

  //added proxy. and now we don't need a @CrossOrigin annotation in backend 
  server:{
    proxy:{
      '/api': 'http://localhost:8080'
    }
  },
  resolve:{
    alias:{
      "@":fileURLToPath(new URL("./src",import.meta.url))
    }
  }
})
