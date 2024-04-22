export interface MessageInterface {
    sender: {username: string};
    receiver: {username: string};
    timeSent?: string;
    content?: string;
    opened?: boolean;
}