import { Moment } from 'moment';

export interface IRealnameAuthRecord {
    id?: number;
    txnId?: string;
    userId?: number;
    realName?: string;
    idCardNum?: string;
    idCardImgA?: number;
    idCardImgB?: number;
    authStatus?: number;
    auditOpinion?: string;
    auditStaffName?: string;
    createTime?: Moment;
    lastModifyTime?: Moment;
}

export class RealnameAuthRecord implements IRealnameAuthRecord {
    constructor(
        public id?: number,
        public txnId?: string,
        public userId?: number,
        public realName?: string,
        public idCardNum?: string,
        public idCardImgA?: number,
        public idCardImgB?: number,
        public authStatus?: number,
        public auditOpinion?: string,
        public auditStaffName?: string,
        public createTime?: Moment,
        public lastModifyTime?: Moment
    ) {}
}
