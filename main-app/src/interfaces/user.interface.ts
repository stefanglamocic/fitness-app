export interface User {
    username: string;
    userType: string;
    name: string;
    surname: string;
    city: string;
    mail: string;
    activated: boolean;
    password?: string;
}