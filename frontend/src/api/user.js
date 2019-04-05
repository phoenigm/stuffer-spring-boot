import {AXIOS} from "./http-common";

export class UserService {
    register(registrationForm) {
        return AXIOS.post('/api/auth/registration', registrationForm);
    }

    login(loginForm) {
        AXIOS.post('/api/auth/login', loginForm)
    }
}
