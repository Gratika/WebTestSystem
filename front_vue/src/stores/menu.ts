import {defineStore} from "pinia";
import {sendRequest,showErrorMessage} from "@/api/authApi";
import type { ICategoryDto, ISubCategoryDto } from '@/api/type'


export type MenuStoreState ={
  categoryDtoList :Array<ICategoryDto>;
  categoryId: bigint|null;
  subcategoryId:bigint|null;
}
export const useMenuStore=defineStore('menuStore',{
    state:():MenuStoreState=>({
        categoryDtoList:[],
        categoryId:null,
        subcategoryId:null,
    }),
    getters: {
      //тут будуть функції, які повертатимуть змінені state, наприклад, відфільтровані
      getSubcategoryListByCategoryIdDelete: (state) => (categoryId: bigint): ISubCategoryDto[] | null => {
        // Находим категорию по categoryId
        const category = state.categoryDtoList.find(category => category.id === categoryId);
        // Если категория найдена, возвращаем список подкатегорий, иначе возвращаем null
        return category?.subcategoryDtoList || null;
      },
      getSubcategoryListByCategoryId():Array<ISubCategoryDto>|null{
        const category= this.categoryDtoList.find(category =>category.id === this.categoryId);
        return category?.subcategoryDtoList || null;

      }
    },
    actions:{ //тут функції, які певним чином змінюють state

        //отримати список категорій з БД
        getCategoryDtoList(){
            sendRequest<Array<ICategoryDto>>('GET', 'category/categoryDTO')
               .then((res) =>{
                   this.categoryDtoList=res;
                   console.log("categoryDtoList", res);
                   this.categoryId = this.categoryDtoList[0].id;
               },(error)=>{
                   console.log(error);
                   showErrorMessage(error)
               });
        },
       saveCategory(newCategory:ICategoryDto){
         sendRequest<ICategoryDto>('POST', 'category/create',newCategory)
         .then(res =>{
           this.categoryDtoList.push(res);},
           (error)=>{
             console.log(error);
             showErrorMessage(error);
         })
       },
      saveSubCategory(newSubCategory:ISubCategoryDto){
        sendRequest<ISubCategoryDto>('POST', 'category/createSub',newSubCategory)
          .then(res =>{
            //У списку категорій знаходимо потрібну (ту, до якої додали нову підкатегорію)
            const editCategory =this.categoryDtoList.filter(category => 
              category.id===res.categoryId);
            //до списку підкатегорій знайденої категорії додаємо нову підкатегрію
            editCategory.map(category =>{
              if (category.subcategoryDtoList) {
                category.subcategoryDtoList.push(res)
              }else{
                category.subcategoryDtoList =[res];
              }
            })},
            (error)=>{
              console.log(error);
              showErrorMessage(error);
            })
      },
      setCateoryId(newCategoryId:bigint){
          this.categoryId = newCategoryId;
          console.log("this.categoryId",this.categoryId);
      },
      setSubcateoryId(newSubcategoryId:bigint){
        this.subcategoryId = newSubcategoryId;
        console.log("this.subcategoryId",this.subcategoryId)
      }

    }
})