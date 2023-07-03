export interface IUser{
   id:bigint;
   login:string;
   password:string;
   email:string;
   isActive:boolean;
   name:string;
   surname:string;
   birthday:Date;
   created_at: Date;
   updated_at: Date;
   roles:Array<string>;

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
   //passwordConfirm: string;
}

export interface ILoginResponse {
   status: string;
   access_token: string;
}

export interface ISignUpResponse {
   status: string;
   message: string;
}

export interface IUserResponse {
   status: string;
   data: {
      user: IUser;
   };
}