import { defineStore } from 'pinia';

import router from "@/router";
import MyLocalStorage from "@/services/myLocalStorage";
import type {ILoginInput, IUser} from "@/api/type";
import {loginUserFn} from "@/api/authApi";

export type AuthStoreState ={
    authUser:IUser|null;
    token:string;
    isLogin:boolean;
}

export const useAuthStore = defineStore({
    id: 'auth',
    state: () => ({
        // initialize state from local storage to enable user to stay logged in
        authUser: JSON.parse(MyLocalStorage.getItem('user')),
        token: MyLocalStorage.getItem('token'),
        isLogin:MyLocalStorage.getItem('isLogin')
    }),
    actions: {
        onLogin(user:ILoginInput){
            loginUserFn(user).then(
                res=>{
                    this.token = res.access_token;
                    this.isLogin =true;
                    MyLocalStorage.setItem('token',this.token);
                    MyLocalStorage.setItem('isLogin',this.isLogin);
                }
            )
        }
    }
});
