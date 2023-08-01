export interface IRole{
   id:bigint;
   name:string;
}
export interface IUser{
   id:bigint;
   login:string;
   password:string;
   email:string;
   isActive:boolean;
   name:string;
   surname:string;
   birthday:Date;
   created: Date;
   updated: Date;
   roles:Array<IRole>;

}
export interface GenericResponse {
   status: string;
   message: string;
}

export interface ILoginInput {
   login: string;
   password: string;
}

export interface ISignUpInput {
   login: string;
   email: string;
   password: string;
   passwordConfirm: string;
}

export interface ILoginResponse {
   login:string;
   token: string;
}

export interface ISignUpResponse {
   status: string;
   message: string;
}

export interface IUserResponse {
   user: IUser|null;

}

export interface ISubCategoryDto{
   id:bigint|null;
   name:string;
   categoryId: bigint|null;
}
export interface ICategoryDto{
   id:bigint|null;
   name:string;
   subcategoryDtoList:Array<ISubCategoryDto>|null;
}
export interface ITest{
   id:bigint|null;
   name: String ;
   isPublic: Boolean ;
   description: String ;
   owner:IOwner|null;
   subcategoryId:bigint|null;
}
export interface IOwner{
   id:number|null;
   login:string;
   email:string;
}