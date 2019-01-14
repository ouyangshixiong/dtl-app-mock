import { Moment } from 'moment';

export interface IStaff {
    id?: number;
    staffName?: string;
    password?: string;
    realName?: string;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class Staff implements IStaff {
    constructor(
        public id?: number,
        public staffName?: string,
        public password?: string,
        public realName?: string,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
