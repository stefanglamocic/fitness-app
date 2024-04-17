import { Category } from "./category.interface";
import { Instructor } from "./instructor.interface";
import { User } from "./user.interface";

export interface FitnessProgram {
    id: number;
    name: string;
    price: number;
    images?: Array<{ path: string }>;
    difficultyLevel?: string;
    duration?: number;
    location?: string;
    category?: Category;
    instructor?: Instructor;
    comments?: Array<Comment>;
    createdBy?: User;
}