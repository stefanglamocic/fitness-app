import { User } from "./user.interface";

export interface CommentInterface {
    publishedBy: User;
    published: string;
    content: string;
    childComments: Array<CommentInterface>;
}