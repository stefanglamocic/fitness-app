import { Category } from "./category.interface";
import { CommentInterface } from "./comment.interface";
import { Instructor } from "./instructor.interface";
import { User } from "./user.interface";

export interface FitnessProgram {
    id: number;
    name: string;
    price: number;
    hidden: boolean;
    description?: string;
    images?: Array<{ path: string }>;
    difficultyLevel?: string;
    duration?: number;
    location?: string;
    category?: Category;
    instructor?: Instructor;
    comments?: Array<CommentInterface>;
    createdBy?: User;
}