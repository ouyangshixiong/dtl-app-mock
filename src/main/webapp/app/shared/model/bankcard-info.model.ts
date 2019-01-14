import { Moment } from 'moment';

export interface IBankcardInfo {
    id?: number;
    userId?: number;
    bankName?: string;
    bankAccountName?: string;
    bankCardNum?: string;
    bankCardImgId?: number;
    status?: number;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class BankcardInfo implements IBankcardInfo {
    constructor(
        public id?: number,
        public userId?: number,
        public bankName?: string,
        public bankAccountName?: string,
        public bankCardNum?: string,
        public bankCardImgId?: number,
        public status?: number,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
