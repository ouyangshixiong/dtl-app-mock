import { Moment } from 'moment';

export interface IUserLevel {
    id?: number;
    name?: string;
    integralRule?: number;
    userIdentity?: number;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class UserLevel implements IUserLevel {
    constructor(
        public id?: number,
        public name?: string,
        public integralRule?: number,
        public userIdentity?: number,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
