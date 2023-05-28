import {defineStore} from "pinia";
import axios from "axios";
export const useMenuStore=defineStore('menuStore',{
    state:()=>({
        categoryDtoList:[]
    }),
    getters:{
        //тут будуть функції, які повертатимуть змінені state, наприклад, відфільтровані
    },
    actions:{ //тут функції, які певним чином змінюють state

        //отримати список категорій з БД
        getCatrgoryDtoList(){
           axios.get("http://localhost:8080/category/categoryDTO")
               .then((res) =>{
                   this.categoryDtoList=res.data;
               },(error)=>{
                   console.log(error);
               });
        }
    }
})