import { Moment } from 'moment';

export interface IBankcardAuthRecord {
    id?: number;
    txnId?: string;
    userId?: number;
    bankName?: string;
    bankAccountName?: string;
    bankCardNum?: string;
    bankCardImgId?: number;
    authStatus?: number;
    auditOpinion?: string;
    auditStaffName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class BankcardAuthRecord implements IBankcardAuthRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public userId?: number,
        public bankName?: string,
        public bankAccountName?: string,
        public bankCardNum?: string,
        public bankCardImgId?: number,
        public authStatus?: number,
        public auditOpinion?: string,
        public auditStaffName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
