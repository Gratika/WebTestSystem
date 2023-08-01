import {defineStore} from "pinia";
import {sendRequest,showErrorMessage} from "@/api/authApi";
import type {ITest } from '@/api/type'


export type TestStoreState ={
  testList :Array<ITest>;
}
export const useTestStore=defineStore('testStore',{
  state:():TestStoreState=>({
    testList:[],
  }),
  getters: {
    //тут будуть функції, які повертатимуть змінені state, наприклад, відфільтровані

  },
  actions:{ //тут функції, які певним чином змінюють state


    saveTest(newTest:ITest){
      sendRequest<ITest>('POST', 'test/create',newTest)
        .then(res =>{
            //триманий тест додаємо до нашого списку тестів
           this.testList.push(res);
           console.log(res);
           },
          (error)=>{
            console.log(error);
            showErrorMessage(error);
          })
    },


  }
})