export interface ClientModelForm{
    id?:number
    name:string
    email:string
    phone:string
}
export interface ClientModelRegisterForm{
    id?:number
    name:string
    email:string
    phone:string
    password:string
}
export interface LoginModelForm{
    id?:number
    login:string
    password:string
}

export interface ClientModelTable {
    id: number
    name: string
    email: string
    phone: string
}