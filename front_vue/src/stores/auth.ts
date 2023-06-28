import { defineStore } from 'pinia';


import MyLocalStorage from "@/services/myLocalStorage";
import type {ILoginInput, IUser, ISignUpInput} from "@/api/type";
import {getUserFn, loginUserFn, logoutUserFn, signUpUserFn} from "@/api/authApi";



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
        onRegistration(user:ISignUpInput){

            signUpUserFn(user).then(
                res=>{
                    console.log("result function SinglUp:")//delete
                    console.log(res);
                }
            )
        },
        onLogin(user:ILoginInput){
            loginUserFn(user).then(
                res=>{
                    this.token = res.access_token;
                    this.isLogin =true;
                    MyLocalStorage.setItem('token',this.token);
                    MyLocalStorage.setItem('isLogin',this.isLogin);
                }
            )
        },
        onLogout(){
            logoutUserFn().then(
                res=>{
                    console.log(res.message);
                    this.token = '';
                    this.isLogin =false;
                    MyLocalStorage.setItem('token',this.token);
                    MyLocalStorage.setItem('isLogin',this.isLogin);
                }
            )

        },
        getAuthUser(){
            getUserFn().then(
                res=>{
                    this.authUser=res.data;
                    MyLocalStorage.setItem('user',this.authUser.toString());
                }
            )
        }
    }
});
