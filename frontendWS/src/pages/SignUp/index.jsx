import axios from "axios";
import { useState } from "react";

export function SignUp() {
  const [username, setUsername] = useState();
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [passwordRepeat, setpasswordRepeat] = useState();

  const onSubmit = (event) => {
    event.preventDefault();
    axios.post('/api/v1/users',{
      username,
      email,
      password
    })
  }
  return (
    <div className="container">
      <div className="col-lg-6 offset-lg-3 col-sm-8 offset-sm-2">
        <form className="card" onSubmit={onSubmit}>

          <div className="text-center card-header">
          <h1>Sign Up</h1>
          </div>
          <div className="card-body">
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                className="form-control"
                id="username"
                onChange={(event) => setUsername(event.target.value)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input id="email" 
                className="form-control"
                onChange={(event) => setEmail(event.target.value)} />
            </div>

            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                id="password"
                type="password"
                className="form-control"
                onChange={(event) => setPassword(event.target.value)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="passwordRepeat" className="form-label">Password Repeat</label>
              <input
                id="passwordRepeat"
                type="password"
                className="form-control"
                onChange={(event) => setpasswordRepeat(event.target.value)}
              />
            </div>

            <div className="text-center">
            <button 
              className="btn btn-primary"
              disabled={!password || password !== passwordRepeat}
              >Sign Up</button>
            </div>
          </div>

        </form>
      </div>
    </div>
  );
}
