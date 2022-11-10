import { Navigate } from "react-router-dom";


export const ProtectedRoute = ({ children, AuthUserSession }) => {
    const { user } = AuthUserSession()

    console.log(user);

    if (Object.keys(user).length !== 0 && user.displayName !== '') {
        return children;
    }

    return <Navigate to="/" />
}