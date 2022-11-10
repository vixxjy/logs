import { useState } from "react"
import { Routes, Route } from "react-router-dom";
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Login from "./pages/login/Login";
import Dashboard from './pages/dashboard/index';
import { UserAuthContext, AuthUserSession } from './utilies/AuthContext'
import { ProtectedRoute } from './utilies/ProtectedRoute'

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

const AUTH_USER = {}

function App() {
  const [user, setUser] = useState(AUTH_USER)

  const login = name => {
    setUser({__guest: false, displayName: name})
  }

  const logout = () => {
    localStorage.removeItem("user");
    setUser(AUTH_USER)
  }
 
  return (
    <UserAuthContext.Provider value={{ user, login, logout }}>
       <ThemeProvider theme={darkTheme}>
          <CssBaseline />
          <main>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/home" 
                       element={ 
                      <ProtectedRoute AuthUserSession={AuthUserSession}> 
                          <Dashboard />
                      </ProtectedRoute>} />
            </Routes>
          </main>
        </ThemeProvider>
    </UserAuthContext.Provider>
  );
}

export default App;
