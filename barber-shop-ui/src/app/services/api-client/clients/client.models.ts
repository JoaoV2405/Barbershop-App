export interface SaveClientRequest{
    name:string
    email:string
    phone:string
}
export interface SaveClientResponse{
    id:number
    name:string
    email:string
    phone:string
}
export interface UpdateClientRequest{
    name:string
    email:string
    phone:string
}
export interface UpdateClientResponse{
    id:number
    name:string
    email:string
    phone:string
}
export interface ListClientResponse{
    id:number
    name:string
    email:string
    phone:string
}
export interface DetailClientResponse{
    id:number
    name:string
    email:string
    phone:string
}

export interface LoginResponse{
    id:number
    login:string
    token:string
}
export interface LoginRequest{
    login:string
    password:string
}
export interface RegisterResponse{
    id:number
    name:string
    email:string
    phone:string
    password:string
    token:string
}
export interface RegisterRequest{
    name:string
    password:string
}
