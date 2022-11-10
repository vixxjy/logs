import { createContext, useContext } from "react";

export const UserAuthContext = createContext({
    user: null,
    login: () => {},
    logout: () => {}
})

export const AuthUserSession = () => {
    const {user, login, logout } = useContext(UserAuthContext)
    return {user, login, logout}
}



