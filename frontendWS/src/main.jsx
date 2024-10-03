import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import App from './App.jsx'
import { SignUp } from './pages/SignUp/index.jsx'
import "./styles.scss"
import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import { signUp } from './pages/SignUp/api.js';

i18n
  .use(initReactI18next) 
  .init({
    resources: {
      en: {
        translation: {
          signUp:'Sign Up',
          username:'Username',
          email:'E-mail',
          password:'Password',
          passwordRepeat:'Password Repeat',
          passwordMismatch: 'Password mismatch',
          genericError:'Unexpected error occured. Please try again.'
        }
      },
      tr: {
        translation:{
          signUp:'Kayit ol',
          username:'Kullanici adi',
          email:'E-Posta',
          password:'Sifre',
          passwordRepeat:'Sifre Tekrari',
          passwordMismatch:'Sifreniz eslesmiyor',
          genericError:'Beklenmedik hata olustu. Lutfen tekrar deneyin.'
        }
      }
    },
    fallbackLng: "tr",
    interpolation: {
      escapeValue: false 
    }
  });

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <SignUp />
  </StrictMode>,
)
