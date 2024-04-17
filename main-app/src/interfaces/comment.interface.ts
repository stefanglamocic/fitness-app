export interface Comment {
    published: string;
    content: string;
    childComments: Array<Comment>;
}