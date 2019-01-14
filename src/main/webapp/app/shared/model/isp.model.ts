import { Moment } from 'moment';

export interface IIsp {
    id?: number;
    txnId?: string;
    mobile?: string;
    template?: string;
    status?: number;
    createTime?: Moment;
}

export class Isp implements IIsp {
    constructor(
        public id?: number,
        public txnId?: string,
        public mobile?: string,
        public template?: string,
        public status?: number,
        public createTime?: Moment
    ) {}
}
