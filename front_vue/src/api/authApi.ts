import axios from 'axios';
import type {
    GenericResponse,
    ILoginInput,
    ILoginResponse,
    ISignUpInput,
    IUserResponse,
} from './type';

const BASE_URL = `${import.meta.env.VITE_API_URL}`;

//Створюємо новий екземпляр axios, задавши у ньому потрібну нам конфігурацію (див https://axios-http.com/docs/req_config)
//базовий URL та withCredentials: true - для відправки файлів cookie разом з запитами
const authApi = axios.create({
    baseURL: BASE_URL,
    headers: {'X-Requested-With': 'XMLHttpRequest',
              'Access-Control-Allow-Credentials': true,
              'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': "*"},
    withCredentials: true,
});
//authApi.defaults.headers.common['Content-Type'] = 'application/json';
//authApi.defaults.headers.common['Access-Control-Allow-Credentials'] = 'true';
//authApi.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

//використовуючи методи створеного екземпляру axios опишемо ряд функцій

//функція для оновлення токену, у випадку якщо його термін сплив
export const refreshAccessTokenFn = async () => {
    const response = await authApi.get<ILoginResponse>('auth/refresh');
    return response.data;
};
//перехоплювач (interceptors) відповідей чи запитів axios (до того як вони будуть опрацьована then або catch)
//автоматично виконується до запитів та після отримання відповіді
authApi.interceptors.response.use(
    //якщо отримали відповідь, то повертаємо її
    (response) => {
        return response;
    },
    //якщо сервер відповів помилкою
    async (error) => {
        console.log(error);
        /*const originalRequest = error.config;
        const errMessage = error.response.data.message as string;
        if (errMessage.includes('not logged in') && !originalRequest._retry) { //яка містить 'not logged in'
            originalRequest._retry = true;
            await refreshAccessTokenFn(); //викликаємо функцію оновлення токена
            return authApi(originalRequest);
        }*/
        return Promise.reject(error);
    }
);

//створюємо(реєструємо нового користувача)
export const signUpUserFn = async (user: ISignUpInput) => {
    console.log("function_signUp");//delete
    const response = await authApi.post<GenericResponse>('/auth/singup', user);
    return response.data;
};

//авторизація, вхід в систему
export const loginUserFn = async (user: ILoginInput) => {
    console.log(user);
    const response = await authApi.post<ILoginResponse>('auth/login', user);
    return response.data;
};


export const verifyEmailFn = async (verificationCode: string) => {
    const response = await authApi.get<GenericResponse>(
        `auth/verifyemail/${verificationCode}`
    );
    return response.data;
};

//вихід з системи
export const logoutUserFn = async () => {
    const response = await authApi.get<GenericResponse>('auth/logout');
    return response.data;
};

//інформація про користувача(профіль)
export const getUserFn = async () => {
    const response = await authApi.get<IUserResponse>('users/me');
    return response.data;
};