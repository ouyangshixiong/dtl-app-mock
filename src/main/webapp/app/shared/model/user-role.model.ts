import { Moment } from 'moment';

export interface IUserRole {
    id?: number;
    name?: string;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class UserRole implements IUserRole {
    constructor(
        public id?: number,
        public name?: string,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
